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

import br.com.ifsp.es4a4.projeto.controller.dto.InstituicaoEditoraDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.InstituicaoEditoraMapper;
import br.com.ifsp.es4a4.projeto.service.InstituicaoEditoraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instituicao-editora")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InstituicaoEditoraController {
	
	private final InstituicaoEditoraService instituicaoEditoraService;

	@GetMapping
	public List<InstituicaoEditoraDto> findAll() {
		return this.instituicaoEditoraService.findAll().stream().map(InstituicaoEditoraMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public InstituicaoEditoraDto findById(@PathVariable Long id) {
		return InstituicaoEditoraMapper.entityToDto(this.instituicaoEditoraService.findById(id));
	}
	
	@PostMapping
	public InstituicaoEditoraDto create(@RequestBody(required = false) InstituicaoEditoraDto instituicaoEditora) {
		return InstituicaoEditoraMapper.entityToDto(this.instituicaoEditoraService.create(InstituicaoEditoraMapper.dtoToEntity(instituicaoEditora)));
	}
	
	@PutMapping("/{id}")
	public InstituicaoEditoraDto update(@PathVariable Long id, @RequestBody(required = false) InstituicaoEditoraDto instituicaoEditora) {
		instituicaoEditora.setId(id);
		return InstituicaoEditoraMapper.entityToDto(this.instituicaoEditoraService.create(InstituicaoEditoraMapper.dtoToEntity(instituicaoEditora)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.instituicaoEditoraService.deleteById(id);
	}

}
