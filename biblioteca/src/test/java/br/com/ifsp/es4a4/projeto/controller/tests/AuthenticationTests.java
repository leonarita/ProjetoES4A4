package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.utils.exceptions.ExistingEmailException;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserAutheticatedDTO;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserRegistrationDTO;

@SpringBootTest
@DisplayName("Realizar testes nos endpoints de autenticação")
public class AuthenticationTests {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Test
	@RepeatedTest(2)
	void authenticate() {
		
		//RepetitionInfo repetitionInfo
		
		assertThat(authenticationController.registrate(
			UserRegistrationDTO.builder()
				.id((long)0)
				.nome("Testes")
				.email("test@gmail.com")
				.senha("123")
				.build()
		).getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	
	@Test
	@RepeatedTest(value = 2, name = "Login {currentRepetition}/{totalRepetitions}")
	@DisplayName("O login, ao ser dado as credenciais corretamente, deve retornar uma resposta 202")
	void login(TestInfo testInfo) {
		
		DadosLogin login = new DadosLogin("leo@gmail.com", "123");
		ResponseEntity<UserAutheticatedDTO> credentials = authenticationController.autenticar(login);
		
		assertAll(
				() -> assertThat(credentials.getStatusCodeValue()).isEqualTo(202),
				() -> assertThat(credentials.getBody().getToken().replace("Bearer ", "")).isNotNull()
		);
	}
	
	@Test()
	@DisplayName("O login, ao ser dado as credenciais erroneamente, deve retornar uma exceção informando que não existe credenciais")
	void loginError() {
		
		DadosLogin login = new DadosLogin("leos@gmail.com", "123");
		
		RuntimeException runtimeException = assertThrows(ExistingEmailException.class, () -> authenticationController.autenticar(login));
		assertThat(runtimeException.getMessage()).isNullOrEmpty();
	}

}
