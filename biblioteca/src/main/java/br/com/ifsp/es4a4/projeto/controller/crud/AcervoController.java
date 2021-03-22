package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<AcervoDto> findAll() {
		return this.acervoService.findAll().stream().map(AcervoMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public AcervoDto findById(@PathVariable Long id) {
		return AcervoMapper.entityToDto(this.acervoService.findById(id));
	}
	
	@PostMapping
	public AcervoDto create(@RequestBody(required = false) AcervoDto acervo) {
		return AcervoMapper.entityToDto(this.acervoService.create(AcervoMapper.dtoToEntity(acervo)));
	}
	
	@PutMapping("/{id}")
	public AcervoDto update(@PathVariable Long id, @RequestBody(required = false) AcervoDto acervo) {
		acervo.setId(id);
		return AcervoMapper.entityToDto(this.acervoService.create(AcervoMapper.dtoToEntity(acervo)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.acervoService.deleteById(id);
	}

}
