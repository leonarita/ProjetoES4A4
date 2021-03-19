package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@AllowAnnonymous
public class LivroController {
	
	private final LivroService livroService;
	private final LivroSpecRepository livroSpecRepository;

	@GetMapping
	public List<Livro> findAll() {
		return this.livroService.findAll();
	}

	@GetMapping("/{id}")
	public Livro findAById(@PathVariable Long id) {
		return this.livroService.findById(id);
	}
	
	@GetMapping("/parametros")
	public List<Livro> findByParameters(@RequestParam(required = false) String title) {
		return this.livroSpecRepository.findBooks(
				Objects.isNull(title) ? ItemFiltroDto.builder().build() : ItemFiltroDto.builder().titulo(title).build(), 
				new ArrayList<>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL, Situacao.EMPRESTADO, Situacao.RESERVADO))
		);
	}
	
	@PostMapping
	public Livro create(@RequestBody(required = false) Livro livro) {
		
		if(Objects.isNull(livro.getSituacaoItem())) {
			livro.setSituacaoItem(Situacao.DISPONIVEL);
		}
		
		return this.livroService.create(livro);
	}
	
	@PutMapping("/{id}")
	public Livro update(@PathVariable("id") Long id, @RequestBody(required = false) Livro livro) {
		livro.setIdAcervo(id);
		return this.livroService.create(livro);
	}
	
	@DeleteMapping("/{id}")
	public Livro deleteById(@PathVariable("id") Long id) {
		this.livroService.deleteById(id);
		return new Livro();
	}

}
