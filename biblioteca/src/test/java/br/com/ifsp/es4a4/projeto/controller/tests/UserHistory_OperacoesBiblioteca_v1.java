package br.com.ifsp.es4a4.projeto.controller.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.controller.SistemaController;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserAutheticatedDTO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@DisplayName("User History: Realizar operações de reserva e empréstimo de itens")
public class UserHistory_OperacoesBiblioteca {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private SistemaController sistemaController;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	private String token;
	
	@BeforeAll
	public void authenticate() {
		token = authenticationController.autenticar(new DadosLogin("leo@gmail.com", "123")).getBody().getToken();
	}
	
	@Test
	public void emprestarItem() {
		
	}
	
	@Test
	public void reservarItem() {
		
	}
	
	@Test
	public void devolverItemEmprestado() {
		
	}

}
