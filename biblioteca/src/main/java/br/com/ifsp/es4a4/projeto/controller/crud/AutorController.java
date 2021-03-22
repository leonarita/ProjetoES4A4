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

import br.com.ifsp.es4a4.projeto.controller.dto.AutorDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.AutorMapper;
import br.com.ifsp.es4a4.projeto.service.AutorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutorController {
	
	private final AutorService autorService;
	
	@GetMapping
	public List<AutorDto> findAll() {
		return this.autorService.findAll().stream().map(AutorMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public AutorDto findById(@PathVariable Long id) {
		return AutorMapper.entityToDto(this.autorService.findById(id));
	}
	
	@PostMapping
	public AutorDto create(@RequestBody(required = false) AutorDto autor) {
		return AutorMapper.entityToDto(this.autorService.create(AutorMapper.dtoToEntity(autor)));
	}
	
	@PutMapping("/{id}")
	public AutorDto update(@PathVariable Long id, @RequestBody(required = false) AutorDto autor) {
		autor.setIdPessoa(id);
		return AutorMapper.entityToDto(this.autorService.create(AutorMapper.dtoToEntity(autor)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.autorService.deleteById(id);
	}

}
