package br.com.ifsp.es4a4.projeto.controller.tests.mock;

import java.net.URI;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User History Mock: Buscar items disponíveis para consulta local")
public class UserHistoryMock_ItemsDisponiveisTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Test
	@DisplayName("Busca de livros com base na massa de dados do Flyway")
	void testarLivrosDisponiveis() throws Exception {
		
		URI uri = new URI("/sistema/item/livros/disponiveis");
		
		String token = this.authenticationController.autenticar(DadosLogin.builder().email("leo@gmail.com").senha("123").build()).getBody().getToken();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", token);

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content("{}").contentType(MediaType.APPLICATION_JSON).headers(httpHeaders))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(3)))
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

}
