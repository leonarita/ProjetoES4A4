package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.InstituicaoEditora;
import br.com.ifsp.es4a4.projeto.repository.InstituicaoEditoraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstituicaoEditoraService {
	
	private final InstituicaoEditoraRepository instituicaoEditoraRepository;
	
	public List<InstituicaoEditora> findAll() {
		return this.instituicaoEditoraRepository.findAll();
	}
	
	public InstituicaoEditora findById(Long id) {
		return this.instituicaoEditoraRepository.findById(id).orElse(null);
	}
	
	public InstituicaoEditora create(InstituicaoEditora instituicaoEditora) {
		return this.instituicaoEditoraRepository.save(instituicaoEditora);
	}

	public void deleteById(Long id) {
		this.instituicaoEditoraRepository.deleteById(id);
	}

}
