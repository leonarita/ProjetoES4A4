package br.com.ifsp.es4a4.projeto.api.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.feign.biblioteca.LivroFeign;

@SpringBootTest
public class IntegrationApiTests {
		
	@Autowired
	private LivroFeign livroFeign;
	
	@Test
	void assertIntegration() {
		
		assertThat(livroFeign.findAll()).hasSizeGreaterThan(1);
		assertThat(livroFeign.findAll()).hasSizeLessThan(1000000);
	}
}
