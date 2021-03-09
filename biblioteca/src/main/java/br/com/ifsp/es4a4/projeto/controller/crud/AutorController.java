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

import br.com.ifsp.es4a4.projeto.model.Autor;
import br.com.ifsp.es4a4.projeto.service.AutorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutorController {
	
	private final AutorService autorService;
	
	@GetMapping
	public List<Autor> findAll() {
		return this.autorService.findAll();
	}
	
	@GetMapping("/{id}")
	public Autor findAById(@PathVariable Long id) {
		return this.autorService.findById(id);
	}
	
	@PostMapping
	public Autor create(@RequestBody(required = false) Autor autor) {
		return this.autorService.create(autor);
	}
	
	@PutMapping("/{id}")
	public Autor update(@PathVariable Long id, @RequestBody(required = false) Autor autor) {
		autor.setIdPessoa(id);
		return this.autorService.create(autor);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.autorService.deleteById(id);
	}

}
