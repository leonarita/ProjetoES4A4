package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.controller.dto.AutoriaDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.AutoriaMapper;
import br.com.ifsp.es4a4.projeto.service.AutoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autoria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutoriaController {
	
	private final AutoriaService autoriaService;
	
	@GetMapping
	public List<AutoriaDto> findAll() {
		return this.autoriaService.findAll().stream().map(AutoriaMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/{idItem}")
	public List<AutoriaDto> findByIdItem(@PathVariable Long idItem) {
		return this.autoriaService.findByIdItem(idItem).stream().map(AutoriaMapper::entityToDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public AutoriaDto create(@RequestBody(required = false) AutoriaDto autoria) {
		return AutoriaMapper.entityToDto(this.autoriaService.create(AutoriaMapper.dtoToEntity(autoria)));
	}
}
