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

import br.com.ifsp.es4a4.projeto.controller.dto.UsuarioDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.UsuarioMapper;
import br.com.ifsp.es4a4.projeto.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	@GetMapping
	public List<UsuarioDto> findAll() {
		return this.usuarioService.findAll().stream().map(UsuarioMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public UsuarioDto findById(@PathVariable Long id) {
		return UsuarioMapper.entityToDto(this.usuarioService.findById(id));
	}
	
	@PostMapping
	public UsuarioDto create(@RequestBody(required = false) UsuarioDto usuario) {
		return UsuarioMapper.entityToDto(this.usuarioService.save(UsuarioMapper.dtoToEntity(usuario)));
	}
	
	@PutMapping("/{idUsuario}")
	public UsuarioDto update(@PathVariable Long idUsuario, @RequestBody(required = false) UsuarioDto usuario) {
		usuario.setIdUsuario(idUsuario);
		return UsuarioMapper.entityToDto(this.usuarioService.save(UsuarioMapper.dtoToEntity(usuario)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.usuarioService.deleteById(id);
	}

}
