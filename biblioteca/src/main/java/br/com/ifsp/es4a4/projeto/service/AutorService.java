package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Autor;
import br.com.ifsp.es4a4.projeto.repository.AutorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {
	
	private final AutorRepository autorRepository;
	
	public List<Autor> findAll() {
		return this.autorRepository.findAll();
	}
	
	public Autor findById(Long id) {
		return this.autorRepository.findById(id).orElse(null);
	}
	
	public Autor create(Autor autor) {
		return this.autorRepository.save(autor);
	}

	public void deleteById(Long id) {
		this.autorRepository.deleteById(id);
	}

}
