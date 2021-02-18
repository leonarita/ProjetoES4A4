package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.InstituicaoEditora;
import br.com.ifsp.es4a4.projeto.service.InstituicaoEditoraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instituicao-editora")
@RequiredArgsConstructor
public class InstituicaoEditoraController {
	
	private final InstituicaoEditoraService instituicaoEditoraService;

	@GetMapping
	public List<InstituicaoEditora> findAll() {
		return this.instituicaoEditoraService.findAll();
	}

	@GetMapping("/{id}")
	public InstituicaoEditora findAById(@PathVariable Long id) {
		return this.instituicaoEditoraService.findById(id);
	}
	
	@PostMapping
	public InstituicaoEditora create(@RequestBody(required = false) InstituicaoEditora instituicaoEditora) {
		return this.instituicaoEditoraService.create(instituicaoEditora);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.instituicaoEditoraService.deleteById(id);
	}

}
