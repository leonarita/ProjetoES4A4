package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.utils.exceptions.ExistingEmailException;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserAutheticatedDTO;

@SpringBootTest
public class AuthenticationTests {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Test
	void login() {
		
		DadosLogin login = new DadosLogin("leo@gmail.com", "123");
		ResponseEntity<UserAutheticatedDTO> credentials = authenticationController.autenticar(login);
		
		assertThat(credentials.getStatusCodeValue()).isEqualTo(202);
	}
	
	@Test()
	void loginError() {
		
		DadosLogin login = new DadosLogin("leos@gmail.com", "123");
		
		RuntimeException runtimeException = assertThrows(ExistingEmailException.class, () -> authenticationController.autenticar(login));
		assertThat(runtimeException.getMessage()).isNullOrEmpty();
	}

}
