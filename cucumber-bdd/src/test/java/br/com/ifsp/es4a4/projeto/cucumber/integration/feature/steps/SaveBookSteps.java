package br.com.ifsp.es4a4.projeto.cucumber.integration.feature.steps;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import br.com.ifsp.es4a4.projeto.model.Book;
import br.com.ifsp.es4a4.projeto.model.Situacao;
import cucumber.api.java.gl.Dado;
import cucumber.api.java.pt.Entao;

public class SaveBookSteps {
	
	Book book = null;
	List<Book> books = new ArrayList<>();

	@Dado("^que o livro \"(.*?)\", cujo isbn eh (\\d+), estah com situacao (\\d+)$")
	public void que_o_livro_cujo_isbn_est_com_situacao(String name, int isbn, int situacao) {
		book = new Book();
		
		book.setTitulo(name);
		book.setIsbn((long)isbn);
		book.setSituacaoItem(Situacao.findSituacaoById(situacao));
	}

	@Entao("^devera salvar o livro$")
	public void devera_salvar_o_livro() {
		books.add(book);
	    assertEquals(books.size(), 1);
	}
}
