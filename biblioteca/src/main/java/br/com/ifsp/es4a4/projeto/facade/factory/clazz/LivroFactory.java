package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroFactory implements BaseFactory {
	
	private final EmprestimoController emprestimoController;
	private final LivroController livroController;
	private final LivroSpecRepository livroSpecRepository;

	@Override
	public void devolverItem(Long idUser, String codigoCatalogacao) {
		
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
				livroController.create(livros.get(0));
				
				return;
			}
			
		}

		throw new NotFoundException("Livro não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		Livro livro = livroController.findAById(id);
		livro.setSituacaoItem(Situacao.DISPONIVEL);
		livroController.create(livro);
	}

}
