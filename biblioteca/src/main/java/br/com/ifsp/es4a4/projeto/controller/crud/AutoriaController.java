package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping
	public Autoria create(@RequestBody(required = false) Autoria autoria) {
		return this.autoriaService.create(autoria);
	}
}
