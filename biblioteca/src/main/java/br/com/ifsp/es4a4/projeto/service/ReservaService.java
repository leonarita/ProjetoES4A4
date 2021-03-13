package br.com.ifsp.es4a4.projeto.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
	
	private final ReservaRepository reservaRepository;
	
	public List<Reserva> findAll() {
		return this.reservaRepository.findAll();
	}
	
	public Reserva create(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}
	
	public List<Reserva> findAllByExpirationDate(Date date) {
		return this.reservaRepository.findAllByExpirationDate(date);
	}
	
	public Integer updateByIds(Long idItemAcervo, Long idUsuarioComum, Calendar dataReserva) {
		return this.reservaRepository.updateByIds(idItemAcervo, idUsuarioComum, dataReserva);
	}
	
	public Reserva findByIdUserAndNameItem(Long idUsuarioComum, String nameItem) {
		return this.reservaRepository.findByIdUserAndNameItem(idUsuarioComum, nameItem);
	}

}
