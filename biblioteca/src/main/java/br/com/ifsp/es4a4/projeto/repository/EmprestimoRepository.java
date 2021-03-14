package br.com.ifsp.es4a4.projeto.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, EmprestimoId>, JpaSpecificationExecutor<Emprestimo> {

	List<Emprestimo> findAll();
	
	@Query("select e from Emprestimo e where e.dataDevolucaoEfetiva = ?1")
	List<Emprestimo> findAllByDevolutionDate(Date date);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("delete from Emprestimo e where e.idItemAcervo = :idItemAcervo and e.idUsuarioComum = :idUsuarioComum and e.dataRetirada = :dataRetirada")
	Integer deleteById(@Param("idItemAcervo") Long idItemAcervo, @Param("idUsuarioComum") Long idUsuarioComum, @Param("dataRetirada") Calendar dataRetirada);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Emprestimo e set e.foiDevolvido = true where e.idItemAcervo = :idItemAcervo and e.idUsuarioComum = :idUsuarioComum and e.dataRetirada = :dataRetirada")
	Integer updateByIds(@Param("idItemAcervo") Long idItemAcervo, @Param("idUsuarioComum") Long idUsuarioComum, @Param("dataRetirada") Calendar dataRetirada);
	
	@Query("select e from Emprestimo e where e.idUsuarioComum = ?1 and e.idItemAcervo = ?2 and e.foiDevolvido = ?3")
	Emprestimo findByIdsAndWasntReturned(Long idUsuarioComum, Long idItemAcervo, boolean foiDevolvido);

}
