package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, EmprestimoId>, JpaSpecificationExecutor<Emprestimo> {

	List<Emprestimo> findAll();

}
