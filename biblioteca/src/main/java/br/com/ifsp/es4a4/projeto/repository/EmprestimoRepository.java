package br.com.ifsp.es4a4.projeto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, EmprestimoId>, JpaSpecificationExecutor<Emprestimo> {

	List<Emprestimo> findAll();
	
	@Query("select e from Emprestimo e where e.dataDevolucaoEfetiva = ?1")
	List<Emprestimo> findAllByDevolutionDate(Date date);
	
	@Query("select e from Emprestimo e where e.idUsuarioComum = ?1 and e.idItemAcervo = ?2 and e.foiDevolvido = ?3")
	Emprestimo findByIdsAndWasntReturned(Long idUsuarioComum, Long idItemAcervo, boolean foiDevolvido);

}
