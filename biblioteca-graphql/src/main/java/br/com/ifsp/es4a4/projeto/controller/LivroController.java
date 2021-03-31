package br.com.ifsp.es4a4.projeto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.feign.biblioteca.LivroFeign;
import br.com.ifsp.es4a4.projeto.feign.dto.LivroDto;

@RestController
@RequestMapping("/livro-feign")
public class LivroController {
	
	private LivroFeign livroFeign;
	
	public LivroController(LivroFeign livroFeign) {
		this.livroFeign = livroFeign;
	}
	
	@GetMapping
	public List<LivroDto> findAll() {
		return this.livroFeign.findAll();
	}

}
