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

import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.service.TrabalhoAcademicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trabalho-academico")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrabalhoAcademicoController {
	
	private final TrabalhoAcademicoService trabalhoAcademicoService;

	@GetMapping
	public List<TrabalhoAcademico> findAll() {
		return this.trabalhoAcademicoService.findAll();
	}

	@GetMapping("/{id}")
	public TrabalhoAcademico findAById(@PathVariable Long id) {
		return this.trabalhoAcademicoService.findById(id);
	}
	
	@PostMapping
	public TrabalhoAcademico create(@RequestBody(required = false) TrabalhoAcademico trabalhoAcademico) {
		return this.trabalhoAcademicoService.create(trabalhoAcademico);
	}
	
	@PutMapping("/{id}")
	public TrabalhoAcademico update(@PathVariable Long id, @RequestBody(required = false) TrabalhoAcademico trabalhoAcademico) {
		trabalhoAcademico.setIdAcervo(id);
		return this.trabalhoAcademicoService.create(trabalhoAcademico);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.trabalhoAcademicoService.deleteById(id);
	}

}
