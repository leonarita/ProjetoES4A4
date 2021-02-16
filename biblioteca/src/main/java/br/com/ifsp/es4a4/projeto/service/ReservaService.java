package br.com.ifsp.es4a4.projeto.service;

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
	
	public Reserva findById(Long id) {
		return this.reservaRepository.findById(id).orElse(null);
	}
	
	public Reserva create(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}

	public void deleteById(Long id) {
		this.reservaRepository.deleteById(id);
	}

}
