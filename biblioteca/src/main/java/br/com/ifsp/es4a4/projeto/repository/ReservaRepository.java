package br.com.ifsp.es4a4.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.ifsp.es4a4.projeto.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {

	List<Reserva> findAll();

}
