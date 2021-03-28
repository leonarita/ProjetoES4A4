package br.com.ifsp.es4a4.projeto.controller.tests.mock.crud;

import java.net.URI;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.controller.dto.LivroDto;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Livro Controller Mock")
public class LivroControllerTestsMock {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LivroController livroController;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	Long id;
	private String token;
	
	@BeforeAll
	public void authenticate() {
		token = authenticationController.autenticar(new DadosLogin("leo@gmail.com", "123")).getBody().getToken();
		
		id = livroController.create(
				LivroDto.builder()
					.titulo("Homem-Aranha")
					.areaConhecimento("Ação")
					.codigoCatalogacao("EH63HS1I")
					.dataPublicacao(new Date())
					.situacaoItem(Situacao.EMPRESTADO)
					.edicao((short)1)
					.isbn((long)555)
					.idAcervo((long)1)
					.tipoItem(TipoItemAcervo.LIVRO)
					.build()
				).getIdItemAcervo();
	}
	
	@AfterAll
	public void clearCache() {
		this.livroController.deleteById(id + 1);
	}
	
	@Test
	@DisplayName("Recuperar livros")
	public void getAll() throws Exception {
		
		URI uri = new URI("/livro");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri).header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Recuperar livro por id")
	public void getById() throws Exception {
		
		URI uri = new URI("/livro/" + id);
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri).header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Salvar livro")
	public void save() throws Exception {
		
		Thread.sleep(1000);
		
		URI uri = new URI("/livro");
		
		String json = "{" + 
				"  \"areaConhecimento\": \"string\"," + 
				"  \"codigoCatalogacao\": \"string\"," + 
				"  \"dataPublicacao\": \"2021-03-28T21:25:07.520Z\"," + 
				"  \"edicao\": 0," + 
				"  \"idAcervo\": 1," + 
				"  \"idItemAcervo\": 0," + 
				"  \"isbn\": 0," + 
				"  \"situacaoItem\": \"CONSULTA_LOCAL\"," + 
				"  \"subtitulo\": \"string\"," + 
				"  \"tipoItem\": \"LIVRO\"," + 
				"  \"titulo\": \"string\"" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Atualizar livro")
	public void update() throws Exception {
		
		Thread.sleep(2000);
		
		URI uri = new URI("/livro/" + id);
		
		String json = "{" + 
				"  \"areaConhecimento\": \"string\"," + 
				"  \"codigoCatalogacao\": \"string\"," + 
				"  \"dataPublicacao\": \"2021-03-28T21:25:07.520Z\"," + 
				"  \"edicao\": 0," + 
				"  \"idAcervo\": 1," + 
				"  \"idItemAcervo\": 0," + 
				"  \"isbn\": 1," + 
				"  \"situacaoItem\": \"CONSULTA_LOCAL\"," + 
				"  \"subtitulo\": \"string\"," + 
				"  \"tipoItem\": \"LIVRO\"," + 
				"  \"titulo\": \"string\"" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(405));
	}
	
	@Test
	@DisplayName("Deletar livro por id")
	public void deleteById() throws Exception {
		
		Thread.sleep(3000);
		
		URI uri = new URI("/livro/" + id);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(uri).header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
