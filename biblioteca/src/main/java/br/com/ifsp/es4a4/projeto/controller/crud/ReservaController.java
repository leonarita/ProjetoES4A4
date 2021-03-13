package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.service.ReservaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservaController {
	
	private final ReservaService reservaService;

	@GetMapping
	public List<Reserva> findAll() {
		return this.reservaService.findAll();
	}
	
	@PostMapping
	public Reserva create(@RequestBody(required = false) Reserva reserva) {
		return this.reservaService.create(reserva);
	}
	
	@PostMapping("/data-expiracao")
	public List<Reserva> findAllByExpirationDate(@RequestBody(required = false) Date date) {
		return this.reservaService.findAllByExpirationDate(date);
	}
	
}
