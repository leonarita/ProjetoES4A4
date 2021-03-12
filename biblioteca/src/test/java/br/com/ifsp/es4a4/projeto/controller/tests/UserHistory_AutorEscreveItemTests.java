package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.controller.crud.RevistaController;
import br.com.ifsp.es4a4.projeto.controller.crud.TrabalhoAcademicoController;

@SpringBootTest
@DisplayName("User History: Autores devem escrever um item do acervo")
public class UserHistory_AutorEscreveItemTests {
	
	@Autowired
	private LivroController livroController;
	
	@Autowired
	private TrabalhoAcademicoController trabalhoAcademicoController;
	
	@Autowired
	private RevistaController revistaController;
	
	@Test
	@DisplayName("Buscar autores de todos os livros")
	public void encontrarAutoresDosLivros() {
		this.livroController.findAll().stream().forEach(livro -> assertTrue(livro.getAutorias() != null));
	}
	
	@Test
	@DisplayName("Buscar autores de todas as revistas")
	public void encontrarAutoresDasRevistas() {
		this.revistaController.findAll().stream().forEach(revista -> assertTrue(revista.getAutorias() != null));
	}
	
	@Test
	@DisplayName("Buscar autores de todos os trabalhos acadêmicos")
	public void encontrarAutoresDosTrabalhosAcademicos() {
		this.trabalhoAcademicoController.findAll().stream().forEach(trabalhoAcademico -> assertTrue(trabalhoAcademico.getAutorias() != null));
	}

}
