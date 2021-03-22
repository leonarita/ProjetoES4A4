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

import br.com.ifsp.es4a4.projeto.controller.dto.UsuarioComumDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.UsuarioComumMapper;
import br.com.ifsp.es4a4.projeto.service.UsuarioComumService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario-comum")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioComumController {
	
	private final UsuarioComumService usuarioComumService;

	@GetMapping
	public List<UsuarioComumDto> findAll() {
		return this.usuarioComumService.findAll().stream().map(UsuarioComumMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public UsuarioComumDto findById(@PathVariable Long id) {
		return UsuarioComumMapper.entityToDto(this.usuarioComumService.findById(id));
	}
	
	@PostMapping
	public UsuarioComumDto create(@RequestBody(required = false) UsuarioComumDto usuarioComum) {
		return UsuarioComumMapper.entityToDto(this.usuarioComumService.create(UsuarioComumMapper.dtoToEntity(usuarioComum)));
	}
	
	@PutMapping("/{id}")
	public UsuarioComumDto update(@PathVariable Long id, @RequestBody(required = false) UsuarioComumDto usuarioComum) {
		usuarioComum.setIdPessoa(id);
		return UsuarioComumMapper.entityToDto(this.usuarioComumService.create(UsuarioComumMapper.dtoToEntity(usuarioComum)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.usuarioComumService.deleteById(id);
	}

}
