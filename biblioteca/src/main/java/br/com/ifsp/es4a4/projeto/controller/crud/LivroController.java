package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LivroController {
	
	private final LivroService livroService;

	@GetMapping
	@AllowAnnonymous
	public List<Livro> findAll() {
		return this.livroService.findAll();
	}

	@GetMapping("/{id}")
	public Livro findAById(@PathVariable Long id) {
		return this.livroService.findById(id);
	}
	
	@PostMapping
	@AllowAnnonymous
	public Livro create(@RequestBody(required = false) Livro livro) {
		
		if(Objects.isNull(livro.getSituacaoItem())) {
			livro.setSituacaoItem(Situacao.DISPONIVEL);
		}
		
		return this.livroService.create(livro);
	}
	
	@DeleteMapping("/{id}")
	@AllowAnnonymous
	public Livro deleteById(@PathVariable("id") Long id) {
		this.livroService.deleteById(id);
		return new Livro();
	}

}
