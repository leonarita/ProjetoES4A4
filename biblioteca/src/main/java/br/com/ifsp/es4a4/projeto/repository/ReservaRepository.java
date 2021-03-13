package br.com.ifsp.es4a4.projeto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.pk.ReservaId;

public interface ReservaRepository extends CrudRepository<Reserva, ReservaId>, JpaSpecificationExecutor<Reserva> {

	List<Reserva> findAll();
	
	@Query("select r from Reserva r where r.dataExpiracao = ?1")
	List<Reserva> findAllByExpirationDate(Date date);

}
