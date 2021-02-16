package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Acervo;
import br.com.ifsp.es4a4.projeto.repository.AcervoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcervoService {
	
	private final AcervoRepository acervoRepository;
	
	public List<Acervo> findAll() {
		return this.acervoRepository.findAll();
	}
	
	public Acervo findById(Long id) {
		return this.acervoRepository.findById(id).orElse(null);
	}
	
	public Acervo create(Acervo acervo) {
		return this.acervoRepository.save(acervo);
	}

	public void deleteById(Long id) {
		this.acervoRepository.deleteById(id);
	}

}
