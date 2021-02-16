package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.repository.LivroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {
	
	private final LivroRepository livroRepository;
	
	public List<Livro> findAll() {
		return this.livroRepository.findAll();
	}
	
	public Livro findById(Long id) {
		return this.livroRepository.findById(id).orElse(null);
	}
	
	public Livro create(Livro livro) {
		return this.livroRepository.save(livro);
	}

	public void deleteById(Long id) {
		this.livroRepository.deleteById(id);
	}

}
