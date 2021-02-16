package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.UsuarioComum;
import br.com.ifsp.es4a4.projeto.repository.UsuarioComumRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioComumService {
	
	private final UsuarioComumRepository usuarioComumRepository;
	
	public List<UsuarioComum> findAll() {
		return this.usuarioComumRepository.findAll();
	}
	
	public UsuarioComum findById(Long id) {
		return this.usuarioComumRepository.findById(id).orElse(null);
	}
	
	public UsuarioComum create(UsuarioComum usuarioComum) {
		return this.usuarioComumRepository.save(usuarioComum);
	}

	public void deleteById(Long id) {
		this.usuarioComumRepository.deleteById(id);
	}

}
