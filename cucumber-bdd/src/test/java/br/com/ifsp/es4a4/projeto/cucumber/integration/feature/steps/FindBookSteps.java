package br.com.ifsp.es4a4.projeto.cucumber.integration.feature.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifsp.es4a4.projeto.model.Book;
import br.com.ifsp.es4a4.projeto.model.Situacao;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class FindBookSteps {
	
	List<Book> books;
	Book bookToFind;
	
	@Dado("^que foram cadastrados os seguintes livros$")
	public void que_foram_cadastrados_os_seguintes_livros(List<Book> livros) throws Throwable {
		for(Book book : livros) {
			book.setSituacaoItem(Situacao.DISPONIVEL);
		}
		
		this.books = livros;
	}

	@Dado("^que foi buscado o livro \"(.*?)\"$")
	public void que_foi_buscado_o_livro(String name) throws Throwable {
		bookToFind = books.stream().filter(b -> b.getTitulo().equals(name)).collect(Collectors.toList()).get(0);
		
		assertEquals(books.stream().filter(b -> b.getTitulo().equals(name)).collect(Collectors.toList()).size(), 1);
	}

	@E("^estah com status (\\d+)$")
	public void estah_com_status(int status) throws Throwable {
		assertEquals(bookToFind.getSituacaoItem(), Situacao.findSituacaoById(status));
	}

	@Quando("^for encontrado$")
	public void for_encontrado() throws Throwable {
		assertNotEquals(bookToFind, null);
	}

	@Entao("^deverah mudar o status para (\\d+)$")
	public void deverah_mudar_o_status_para(int status) throws Throwable {
		bookToFind.setSituacaoItem(Situacao.findSituacaoById(status));
		assertEquals(bookToFind.getSituacaoItem(), Situacao.findSituacaoById(status));
	}


}
