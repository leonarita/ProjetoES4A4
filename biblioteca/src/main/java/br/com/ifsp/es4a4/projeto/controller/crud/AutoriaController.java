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

import br.com.ifsp.es4a4.projeto.model.Autoria;
import br.com.ifsp.es4a4.projeto.service.AutoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autoria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutoriaController {
	
	private final AutoriaService autoriaService;
	
	@GetMapping
	public List<Autoria> findAll() {
		return this.autoriaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Autoria findAById(@PathVariable Long id) {
		return this.autoriaService.findById(id);
	}
	
	@PostMapping
	public Autoria create(@RequestBody(required = false) Autoria autoria) {
		return this.autoriaService.create(autoria);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.autoriaService.deleteById(id);
	}

}
