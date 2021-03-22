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

import br.com.ifsp.es4a4.projeto.controller.dto.TrabalhoAcademicoDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.TrabalhoAcademicoMapper;
import br.com.ifsp.es4a4.projeto.service.TrabalhoAcademicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trabalho-academico")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrabalhoAcademicoController {
	
	private final TrabalhoAcademicoService trabalhoAcademicoService;

	@GetMapping
	public List<TrabalhoAcademicoDto> findAll() {
		return this.trabalhoAcademicoService.findAll().stream().map(TrabalhoAcademicoMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public TrabalhoAcademicoDto findById(@PathVariable Long id) {
		return TrabalhoAcademicoMapper.entityToDto(this.trabalhoAcademicoService.findById(id));
	}
	
	@PostMapping
	public TrabalhoAcademicoDto create(@RequestBody(required = false) TrabalhoAcademicoDto trabalhoAcademico) {
		return TrabalhoAcademicoMapper.entityToDto(this.trabalhoAcademicoService.create(TrabalhoAcademicoMapper.dtoToEntity(trabalhoAcademico)));
	}
	
	@PutMapping("/{id}")
	public TrabalhoAcademicoDto update(@PathVariable Long id, @RequestBody(required = false) TrabalhoAcademicoDto trabalhoAcademico) {
		trabalhoAcademico.setIdAcervo(id);
		return TrabalhoAcademicoMapper.entityToDto(this.trabalhoAcademicoService.create(TrabalhoAcademicoMapper.dtoToEntity(trabalhoAcademico)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.trabalhoAcademicoService.deleteById(id);
	}

}
