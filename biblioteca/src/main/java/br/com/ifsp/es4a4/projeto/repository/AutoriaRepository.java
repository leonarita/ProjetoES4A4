package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Autoria;
import br.com.ifsp.es4a4.projeto.model.pk.AutoriaId;

public interface AutoriaRepository extends CrudRepository<Autoria, AutoriaId>, JpaSpecificationExecutor<Autoria> {

	List<Autoria> findAll();

}
