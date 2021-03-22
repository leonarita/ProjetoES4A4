package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Autoria;
import br.com.ifsp.es4a4.projeto.repository.AutoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutoriaService {
	
	private final AutoriaRepository autoriaRepository;
	
	public List<Autoria> findAll() {
		return this.autoriaRepository.findAll();
	}
	
	public List<Autoria> findByIdItem(Long idItem) {
		return this.autoriaRepository.findByIdItem(idItem);
	}
	
	public Autoria create(Autoria autoria) {
		return this.autoriaRepository.save(autoria);
	}

}
