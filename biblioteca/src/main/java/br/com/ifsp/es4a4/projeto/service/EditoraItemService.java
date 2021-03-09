package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.EditoraItem;
import br.com.ifsp.es4a4.projeto.repository.EditoraItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditoraItemService {
	
	private final EditoraItemRepository editoraItemRepository;
	
	public List<EditoraItem> findAll() {
		return this.editoraItemRepository.findAll();
	}
	
	public EditoraItem create(EditoraItem editoraItem) {
		return this.editoraItemRepository.save(editoraItem);
	}

}
