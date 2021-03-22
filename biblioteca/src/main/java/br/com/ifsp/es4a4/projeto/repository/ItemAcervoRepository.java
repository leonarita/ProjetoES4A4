package br.com.ifsp.es4a4.projeto.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;

public interface ItemAcervoRepository extends CrudRepository<ItemAcervo, Long>, JpaSpecificationExecutor<ItemAcervo> {
	
}
