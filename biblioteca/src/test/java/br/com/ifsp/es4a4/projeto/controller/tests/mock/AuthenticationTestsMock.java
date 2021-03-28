package br.com.ifsp.es4a4.projeto.controller.tests.mock;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Realizar testes mockados nos endpoints de autenticação")
public class AuthenticationTestsMock {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void validarLogin() throws Exception {
		
		URI uri = new URI("/auth/login");
		
		String json = "{" + 
				"	\"email\": \"leo@gmail.com\"," + 
				"	\"senha\": \"123\"" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(202))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
	}

}
