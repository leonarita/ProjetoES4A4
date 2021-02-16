package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.repository.RevistaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevistaService {
	
	private final RevistaRepository revistaRepository;
	
	public List<Revista> findAll() {
		return this.revistaRepository.findAll();
	}
	
	public Revista findById(Long id) {
		return this.revistaRepository.findById(id).orElse(null);
	}
	
	public Revista create(Revista revista) {
		return this.revistaRepository.save(revista);
	}

	public void deleteById(Long id) {
		this.revistaRepository.deleteById(id);
	}

}
