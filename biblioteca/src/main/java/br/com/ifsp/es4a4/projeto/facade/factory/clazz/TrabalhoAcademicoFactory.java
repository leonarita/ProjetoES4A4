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
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.TrabalhoAcademicoSpecRepository;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import br.com.ifsp.es4a4.projeto.service.ReservaService;
import br.com.ifsp.es4a4.projeto.service.TrabalhoAcademicoService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabalhoAcademicoFactory implements BaseFactory {
	
	private final EmprestimoService emprestimoService;
	private final ReservaService reservaService;
	private final TrabalhoAcademicoService trabalhoAcademicoService;
	private final TrabalhoAcademicoSpecRepository trabalhoAcademicoSpecRepository;
	
	@Override
	public ItemAcervo devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<TrabalhoAcademico> trabalhosAcademicos = trabalhoAcademicoSpecRepository.findWorks(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(trabalhosAcademicos) && trabalhosAcademicos.size() == 1) {
			
			Emprestimo emprestimo = emprestimoService.findByIdsAndWasntReturned(
					idUser, 
					trabalhosAcademicos.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoService.create(emprestimo);
				
				trabalhosAcademicos.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				return trabalhoAcademicoService.create(trabalhosAcademicos.get(0));
			}
			
			throw new NotFoundException("Empréstimo não encontrado com parâmetros informados");
		}

		throw new NotFoundException("Trabalho Acadêmico não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		TrabalhoAcademico trabalhoAcademico = trabalhoAcademicoService.findById(id);
		trabalhoAcademico.setSituacaoItem(Situacao.DISPONIVEL);
		trabalhoAcademicoService.create(trabalhoAcademico);
	}
	
	@Override
	public Emprestimo emprestarItemReservado(Long idUser, String name) {
		
		Reserva reserva = reservaService.findByIdUserAndNameItem(idUser, name);
		
		if(Objects.nonNull(reserva)) {
			reserva.setFoiRetirado(true);
			reservaService.create(reserva);
			
			TrabalhoAcademico trabalhoAcademico = trabalhoAcademicoService.findById(reserva.getIdItemAcervo());
			trabalhoAcademico.setSituacaoItem(Situacao.EMPRESTADO);
			trabalhoAcademicoService.create(trabalhoAcademico);
			
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
		
		List<TrabalhoAcademico> trabalhos = trabalhoAcademicoSpecRepository.findWorks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(trabalhos) && trabalhos.size() > 0) {
			
			TrabalhoAcademico trabalho = trabalhos.get(0);
			trabalho.setSituacaoItem(Situacao.EMPRESTADO);
			trabalhoAcademicoService.create(trabalho);
			
			return emprestimoService.create(
					Emprestimo.builder()
						.idItemAcervo(trabalho.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataRetirada(Calendar.getInstance())
						.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
						.item(trabalho)
						.foiDevolvido(false)
						.build()
			);
		}
		
		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}
	
	@Override
	public Reserva reservarItem(Long idUser, ItemFiltroDto filtro) {
		
		List<TrabalhoAcademico> trabalhos = trabalhoAcademicoSpecRepository.findWorks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(trabalhos) && trabalhos.size() > 0) {
			
			TrabalhoAcademico trabalho = trabalhos.get(0);
			trabalho.setSituacaoItem(Situacao.RESERVADO);
			trabalhoAcademicoService.create(trabalho);
			
			return reservaService.create(
					Reserva.builder()
						.idItemAcervo(trabalho.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataReserva(Calendar.getInstance())
						.dataExpiracao(DateFormat.addDays(new Date(), 7))
						.item(trabalho)
						.foiRetirado(false)
						.build()
			);
		}
		
		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}

}
