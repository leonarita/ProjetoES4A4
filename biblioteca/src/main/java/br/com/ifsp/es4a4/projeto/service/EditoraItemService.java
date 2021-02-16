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
	
	public EditoraItem findById(Long id) {
		return this.editoraItemRepository.findById(id).orElse(null);
	}
	
	public EditoraItem create(EditoraItem editoraItem) {
		return this.editoraItemRepository.save(editoraItem);
	}

	public void deleteById(Long id) {
		this.editoraItemRepository.deleteById(id);
	}

}
