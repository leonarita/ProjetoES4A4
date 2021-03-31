package br.com.ifsp.es4a4.projeto.feign.biblioteca;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ifsp.es4a4.projeto.feign.dto.LivroDto;

@FeignClient(url = "${biblioteca.url}", name = "livro", contextId = "livro-client", path = "/livro")
public interface LivroFeign {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<LivroDto> findAll();

}
