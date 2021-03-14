package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.controller.crud.ReservaController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroFactory implements BaseFactory {
	
	private final EmprestimoController emprestimoController;
	private final ReservaController reservaController;
	private final LivroController livroController;
	private final LivroSpecRepository livroSpecRepository;

	@Override
	public ItemAcervo devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<Livro> livros = livroSpecRepository.findBooks(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(livros) && livros.size() == 1) {
			
			Emprestimo emprestimo = emprestimoController.findByIdsAndWasntReturned(
					idUser, 
					livros.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoController.create(emprestimo);
				
				livros.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				return livroController.create(livros.get(0));
			}
			
			throw new NotFoundException("Empréstimo não encontrado com parâmetros informados");
		}

		throw new NotFoundException("Livro não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		Livro livro = livroController.findAById(id);
		livro.setSituacaoItem(Situacao.DISPONIVEL);
		livroController.create(livro);
	}
	
	@Override
	public Emprestimo emprestarItemReservado(Long idUser, String name) {
		
		Reserva reserva = reservaController.findByIdUserAndNameItem(idUser, name);
		
		if(Objects.nonNull(reserva)) {
			reserva.setFoiRetirado(true);
			reservaController.create(reserva);
			
			Livro livro = livroController.findAById(reserva.getIdItemAcervo());
			livro.setSituacaoItem(Situacao.EMPRESTADO);
			livroController.create(livro);
			
			return emprestimoController.create(
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

}
