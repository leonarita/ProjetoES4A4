package br.com.ifsp.es4a4.projeto.controller.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.controller.SistemaController;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@DisplayName("User History: Realizar operações de reserva e empréstimo de itens")
public class UserHistory_OperacoesBiblioteca_v1 {
	
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
	@Disabled("Not implemented yet")
	public void emprestarItem() {
		
	}
	
	@Test
	@Disabled("Not implemented yet")
	public void reservarItem() {
		
	}

}
