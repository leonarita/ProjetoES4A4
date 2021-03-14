package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.controller.SistemaController;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@DisplayName("User History: Realizar operações de emprestimo e devolução de itens")
public class UserHistory_OperacoesBiblioteca_v2 {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private SistemaController sistemaController;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	private String token;

	@BeforeEach
	public void authenticate() {
		token = authenticationController.autenticar(new DadosLogin("leo@gmail.com", "123")).getBody().getToken();
	}
	
	@AfterEach
	public void flush() {
		token = null;
	}
	
	@Test
	@DisplayName("Devolução de livro corretamente")
	public void devolverLivroEmprestado() {
		ItemAcervo item = sistemaController.devolverItemEmprestado(token, "livro", "ZECH25KE");
		assertThat(item.getSituacaoItem()).isEqualByComparingTo(Situacao.DISPONIVEL);
	}
	
	@Test
	@DisplayName("Empréstimo de livro reservado corretamente")
	public void pegarEmprestadoItemReservado() {
		Emprestimo emprestimo = sistemaController.pegarEmprestadoItemReservado(token, "trabalho-academico", "Tecnologia no Ambiente de Trabalho");
		
		assertAll(
				() -> assertThat(emprestimo.getDataDevolucaoEfetiva()).isAfter(new Date())
//				() -> assertFalse(emprestimo.getFoiDevolvido())
		);
	}

}
