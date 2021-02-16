package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long>, JpaSpecificationExecutor<Emprestimo> {

	List<Emprestimo> findAll();

}
