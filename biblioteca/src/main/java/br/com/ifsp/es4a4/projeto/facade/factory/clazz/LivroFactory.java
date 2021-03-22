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
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import br.com.ifsp.es4a4.projeto.service.ReservaService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroFactory implements BaseFactory {
	
	private final EmprestimoService emprestimoService;
	private final ReservaService reservaService;
	private final LivroService livroService;
	private final LivroSpecRepository livroSpecRepository;

	@Override
	public ItemAcervo devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<Livro> livros = livroSpecRepository.findBooks(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(livros) && livros.size() == 1) {
			
			Emprestimo emprestimo = emprestimoService.findByIdsAndWasntReturned(
					idUser, 
					livros.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoService.create(emprestimo);
				
				livros.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				return livroService.create(livros.get(0));
			}
			
			throw new NotFoundException("Empréstimo não encontrado com parâmetros informados");
		}

		throw new NotFoundException("Livro não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		Livro livro = livroService.findById(id);
		livro.setSituacaoItem(Situacao.DISPONIVEL);
		livroService.create(livro);
	}
	
	@Override
	public Emprestimo emprestarItemReservado(Long idUser, String name) {
		
		Reserva reserva = reservaService.findByIdUserAndNameItem(idUser, name);
		
		if(Objects.nonNull(reserva)) {
			reserva.setFoiRetirado(true);
			reservaService.create(reserva);
			
			Livro livro = livroService.findById(reserva.getIdItemAcervo());
			livro.setSituacaoItem(Situacao.EMPRESTADO);
			livroService.create(livro);
			
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
		
		List<Livro> livros = livroSpecRepository.findBooks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(livros) && livros.size() > 0) {
			
			Livro livro = livros.get(0);
			livro.setSituacaoItem(Situacao.EMPRESTADO);
			livroService.create(livro);
			
			return emprestimoService.create(
					Emprestimo.builder()
						.idItemAcervo(livro.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataRetirada(Calendar.getInstance())
						.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
						.item(livro)
						.foiDevolvido(false)
						.build()
			);
		}
		
		throw new NotFoundException("Livro não encontrado com parâmetros informados");
	}
	
	@Override
	public Reserva reservarItem(Long idUser, ItemFiltroDto filtro) {
		
		List<Livro> livros = livroSpecRepository.findBooks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL)));
		
		if(Objects.nonNull(livros) && livros.size() > 0) {
			
			Livro livro = livros.get(0);
			livro.setSituacaoItem(Situacao.RESERVADO);
			livroService.create(livro);
			
			return reservaService.create(
					Reserva.builder()
						.idItemAcervo(livro.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataReserva(Calendar.getInstance())
						.dataExpiracao(DateFormat.addDays(new Date(), 7))
						.item(livro)
						.foiRetirado(false)
						.build()
			);
		}
		
		throw new NotFoundException("Livro não encontrado com parâmetros informados");
	}

}
