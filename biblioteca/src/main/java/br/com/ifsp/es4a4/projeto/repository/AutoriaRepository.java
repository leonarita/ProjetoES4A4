package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Autoria;

public interface AutoriaRepository extends CrudRepository<Autoria, Long>, JpaSpecificationExecutor<Autoria> {

	List<Autoria> findAll();

}
