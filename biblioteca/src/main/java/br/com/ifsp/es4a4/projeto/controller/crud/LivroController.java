package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {
	
	private final LivroService livroService;

	@GetMapping
	public List<Livro> findAll() {
		return this.livroService.findAll();
	}

	@GetMapping("/{id}")
	public Livro findAById(@PathVariable Long id) {
		return this.livroService.findById(id);
	}
	
	@PostMapping
	public Livro create(@RequestBody(required = false) Livro livro) {
		return this.livroService.create(livro);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.livroService.deleteById(id);
	}

}
