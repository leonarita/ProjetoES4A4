package br.com.ifsp.es4a4.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import br.com.ifsp.es4a4.projeto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return this.usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		return this.usuarioRepository.findById(id).orElse(null);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}
	
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public void deleteById(Long id) {
		this.usuarioRepository.deleteById(id);
	}

}
