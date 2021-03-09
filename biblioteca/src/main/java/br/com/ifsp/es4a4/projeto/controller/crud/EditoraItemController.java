package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.EditoraItem;
import br.com.ifsp.es4a4.projeto.service.EditoraItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/editora-item")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditoraItemController {
	
	private final EditoraItemService editoraItemService;
	
	@GetMapping
	public List<EditoraItem> findAll() {
		return this.editoraItemService.findAll();
	}
	
	@PostMapping
	public EditoraItem create(@RequestBody(required = false) EditoraItem editoraItem) {
		return this.editoraItemService.create(editoraItem);
	}
	
}
