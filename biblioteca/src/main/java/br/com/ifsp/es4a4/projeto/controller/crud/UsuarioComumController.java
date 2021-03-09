package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.UsuarioComum;
import br.com.ifsp.es4a4.projeto.service.UsuarioComumService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario-comum")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioComumController {
	
	private final UsuarioComumService usuarioComumService;

	@GetMapping
	public List<UsuarioComum> findAll() {
		return this.usuarioComumService.findAll();
	}

	@GetMapping("/{id}")
	public UsuarioComum findAById(@PathVariable Long id) {
		return this.usuarioComumService.findById(id);
	}
	
	@PostMapping
	public UsuarioComum create(@RequestBody(required = false) UsuarioComum usuarioComum) {
		return this.usuarioComumService.create(usuarioComum);
	}
	
	@PutMapping("/{id}")
	public UsuarioComum update(@PathVariable Long id, @RequestBody(required = false) UsuarioComum usuarioComum) {
		usuarioComum.setIdPessoa(id);
		return this.usuarioComumService.create(usuarioComum);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.usuarioComumService.deleteById(id);
	}

}
