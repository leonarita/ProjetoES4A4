package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.RevistaSpecRepository;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import br.com.ifsp.es4a4.projeto.service.ReservaService;
import br.com.ifsp.es4a4.projeto.service.RevistaService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevistaFactory implements BaseFactory {
	
	private final EmprestimoService emprestimoService;
	private final ReservaService reservaService;
	private final RevistaService revistaService;
	private final RevistaSpecRepository revistaSpecRepository;

	@Override
	public ItemAcervo devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<Revista> revistas = revistaSpecRepository.findMaganizes(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(revistas) && revistas.size() == 1) {
			
			Emprestimo emprestimo = emprestimoService.findByIdsAndWasntReturned(
					idUser, 
					revistas.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoService.create(emprestimo);
				
				revistas.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				return revistaService.create(revistas.get(0));
			}
			
			throw new NotFoundException("Empréstimo não encontrado com parâmetros informados");
		}

		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		Revista revista = revistaService.findById(id);
		revista.setSituacaoItem(Situacao.DISPONIVEL);
		revistaService.create(revista);
	}

	@Override
	public Emprestimo emprestarItemReservado(Long idUser, String name) {
		
		Reserva reserva = reservaService.findByIdUserAndNameItem(idUser, name);
		
		if(Objects.nonNull(reserva)) {
			reserva.setFoiRetirado(true);
			reservaService.create(reserva);
			
			Revista revista = revistaService.findById(reserva.getIdItemAcervo());
			revista.setSituacaoItem(Situacao.EMPRESTADO);
			revistaService.create(revista);
			
			return emprestimoService.create(
					Emprestimo.builder()
						.idItemAcervo(reserva.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataRetirada(Calendar.getInstance())
						.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
						.foiDevolvido(false)
						.build()
			);
		}
		
		throw new NotFoundException("Reserva não encontrada com parâmetros informados");
	}
	
	@Override
	public Emprestimo emprestarItem(Long idUser, ItemFiltroDto filtro) {
		
		List<Revista> revistas = revistaSpecRepository.findMaganizes(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(revistas) && revistas.size() > 0) {
			
			Revista revista = revistas.get(0);
			revista.setSituacaoItem(Situacao.EMPRESTADO);
			revistaService.create(revista);
			
			return emprestimoService.create(
					Emprestimo.builder()
						.idItemAcervo(revista.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataRetirada(Calendar.getInstance())
						.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
						.item(revista)
						.foiDevolvido(false)
						.build()
			);
		}
		
		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}
	
	@Override
	public Reserva reservarItem(Long idUser, ItemFiltroDto filtro) {
		
		List<Revista> revistas = revistaSpecRepository.findMaganizes(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(revistas) && revistas.size() > 0) {
			
			Revista revista = revistas.get(0);
			revista.setSituacaoItem(Situacao.RESERVADO);
			revistaService.create(revista);
			
			return reservaService.create(
					Reserva.builder()
						.idItemAcervo(revista.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataReserva(Calendar.getInstance())
						.dataExpiracao(DateFormat.addDays(new Date(), 7))
						.item(revista)
						.foiRetirado(false)
						.build()
			);
		}
		
		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}

}
