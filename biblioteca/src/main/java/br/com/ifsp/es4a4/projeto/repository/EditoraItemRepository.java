package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.EditoraItem;

public interface EditoraItemRepository extends CrudRepository<EditoraItem, Long>, JpaSpecificationExecutor<EditoraItem> {

	List<EditoraItem> findAll();

}
