package br.com.ifsp.es4a4.projeto.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.pk.ReservaId;

public interface ReservaRepository extends CrudRepository<Reserva, ReservaId>, JpaSpecificationExecutor<Reserva> {

	List<Reserva> findAll();
	
	@Query("select r from Reserva r where r.dataExpiracao = ?1")
	List<Reserva> findAllByExpirationDate(Date date);
	
	@Query("update Reserva r set r.foiRetirado = true where r.idItemAcervo = :idItemAcervo and r.idUsuarioComum = :idUsuarioComum and r.dataReserva = :dataReserva")
	Integer updateByIds(@Param("idItemAcervo") Long idItemAcervo, @Param("idUsuarioComum") Long idUsuarioComum, @Param("dataReserva") Calendar dataReserva);
	
	@Query("select r from Reserva r join fetch r.item where r.item.titulo like %:nameItem% and r.idUsuarioComum = :idUsuarioComum and r.foiRetirado = false and CURRENT_DATE <= r.dataExpiracao")
	Reserva findByIdUserAndNameItem(@Param("idUsuarioComum") Long idUsuarioComum, @Param("nameItem") String nameItem);

}
