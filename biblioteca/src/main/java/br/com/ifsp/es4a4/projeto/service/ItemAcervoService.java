package br.com.ifsp.es4a4.projeto.service;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.repository.ItemAcervoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemAcervoService {
	
	private final ItemAcervoRepository itemAcervoRepository;
	
	public ItemAcervo findById(Long id) {
		return this.itemAcervoRepository.findById(id).orElse(null);
	}

}
