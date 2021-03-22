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

import br.com.ifsp.es4a4.projeto.controller.dto.RevistaDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.RevistaMapper;
import br.com.ifsp.es4a4.projeto.service.RevistaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/revista")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RevistaController {
	
	private final RevistaService revistaService;

	@GetMapping
	public List<RevistaDto> findAll() {
		return this.revistaService.findAll().stream().map(RevistaMapper::entityToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public RevistaDto findById(@PathVariable Long id) {
		return RevistaMapper.entityToDto(this.revistaService.findById(id));
	}
	
	@PostMapping
	public RevistaDto create(@RequestBody(required = false) RevistaDto revista) {
		return RevistaMapper.entityToDto(this.revistaService.create(RevistaMapper.dtoToEntity(revista)));
	}
	
	@PutMapping
	public RevistaDto update(@PathVariable Long id, @RequestBody(required = false) RevistaDto revista) {
		revista.setIdAcervo(id);
		return RevistaMapper.entityToDto(this.revistaService.create(RevistaMapper.dtoToEntity(revista)));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.revistaService.deleteById(id);
	}

}
