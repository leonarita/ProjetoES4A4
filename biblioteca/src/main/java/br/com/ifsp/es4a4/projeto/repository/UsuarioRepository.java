package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
	
	List<Usuario> findAll();
	Optional<Usuario> findByEmail(String email);

}
