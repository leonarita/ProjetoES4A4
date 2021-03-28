package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.controller.dto.AcervoDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.AcervoMapper;
import br.com.ifsp.es4a4.projeto.service.AcervoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/acervo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AcervoController {
	
	private final AcervoService acervoService;
	
	@GetMapping
	@Cacheable("todosAcervos")
	public List<AcervoDto> findAll() {
		System.out.println("Gerando cache de acervos");
		return this.acervoService.findAll().stream().map(AcervoMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@Cacheable(cacheNames = "acervos", key = "#id")
	public AcervoDto findById(@PathVariable Long id) {
		return AcervoMapper.entityToDto(this.acervoService.findById(id));
	}
	
	@PostMapping
	@Caching(evict = { 
			@CacheEvict(cacheNames = "todosAcervos", allEntries = true),
			@CacheEvict(cacheNames = "acervos", allEntries = true)
	})
	public AcervoDto create(@RequestBody(required = false) AcervoDto acervo) {
		return AcervoMapper.entityToDto(this.acervoService.create(AcervoMapper.dtoToEntity(acervo)));
	}
	
	@PutMapping("/{id}")
	@CachePut(cacheNames = "acervos", key = "#id")
	public AcervoDto update(@PathVariable Long id, @RequestBody(required = false) AcervoDto acervo) {
		acervo.setId(id);
		return AcervoMapper.entityToDto(this.acervoService.create(AcervoMapper.dtoToEntity(acervo)));
	}
	
	@DeleteMapping("/{id}")
	@Caching(evict = { 
			@CacheEvict(cacheNames = "todosAcervos", allEntries = true),
			@CacheEvict(cacheNames = "acervos", key = "#id")
	})
	public void deleteById(@PathVariable Long id) {
		this.acervoService.deleteById(id);
	}

}
