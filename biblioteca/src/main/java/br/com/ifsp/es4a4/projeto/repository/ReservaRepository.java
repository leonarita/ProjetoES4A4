package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.pk.ReservaId;

public interface ReservaRepository extends CrudRepository<Reserva, ReservaId>, JpaSpecificationExecutor<Reserva> {

	List<Reserva> findAll();

}
