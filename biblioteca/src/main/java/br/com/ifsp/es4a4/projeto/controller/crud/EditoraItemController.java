package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.controller.dto.EditoraItemDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.EditoraItemMapper;
import br.com.ifsp.es4a4.projeto.service.EditoraItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/editora-item")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditoraItemController {
	
	private final EditoraItemService editoraItemService;
	
	@GetMapping
	public List<EditoraItemDto> findAll() {
		return this.editoraItemService.findAll().stream().map(EditoraItemMapper::entityToDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public EditoraItemDto create(@RequestBody(required = false) EditoraItemDto editoraItem) {
		return EditoraItemMapper.entityToDto(this.editoraItemService.create(EditoraItemMapper.dtoToEntity(editoraItem)));
	}
	
}
