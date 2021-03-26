package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

import br.com.ifsp.es4a4.projeto.controller.dto.LivroDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.LivroMapper;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
@Api(value = "Livros")
@CrossOrigin(origins = "*")
@AllowAnnonymous
public class LivroController {
	
	private final LivroService livroService;
	private final LivroSpecRepository livroSpecRepository;

	@ApiOperation(value = "Encontrar todos os livros")
	@GetMapping
	public List<LivroDto> findAll() {
		return this.livroService.findAll().stream().map(LivroMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public LivroDto findById(@PathVariable Long id) {
		return LivroMapper.entityToDto(this.livroService.findById(id));
	}
	
	@GetMapping("/titulo")
	public List<LivroDto> findByTituloIgnoreCase(@RequestParam(required = false) String titulo) {
		return this.livroService.findByTituloIgnoreCase(titulo).stream().map(LivroMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/parametros")
	public List<LivroDto> findByParameters(@RequestParam(required = false) String title) {
		return this.livroSpecRepository.findBooks(
				Objects.isNull(title) ? ItemFiltroDto.builder().build() : ItemFiltroDto.builder().titulo(title).build(), 
				new ArrayList<>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL, Situacao.EMPRESTADO, Situacao.RESERVADO))
		).stream().map(LivroMapper::entityToDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public LivroDto create(@RequestBody(required = false) LivroDto livro) {
		return LivroMapper.entityToDto(this.livroService.create(LivroMapper.dtoToEntity(livro)));
	}
	
	@PutMapping("/{id}")
	public LivroDto update(@PathVariable("id") Long id, @RequestBody(required = false) LivroDto livro) {
		livro.setIdAcervo(id);
		return LivroMapper.entityToDto(this.livroService.create(LivroMapper.dtoToEntity(livro)));
	}
	
	@DeleteMapping("/{id}")
	public LivroDto deleteById(@PathVariable("id") Long id) {
		this.livroService.deleteById(id);
		return new LivroDto();
	}

}
