package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import br.com.ifsp.es4a4.projeto.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> findAll() {
		return this.usuarioService.findAll();
	}

	@GetMapping("/{id}")
	public Usuario findAById(@PathVariable Long id) {
		return this.usuarioService.findById(id);
	}
	
	@PostMapping
	public Usuario create(@RequestBody(required = false) Usuario usuario) {
		return this.usuarioService.save(usuario);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.usuarioService.deleteById(id);
	}

}
