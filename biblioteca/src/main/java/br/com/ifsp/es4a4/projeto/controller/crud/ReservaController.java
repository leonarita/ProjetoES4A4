package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.pk.ReservaId;
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
	
	@DeleteMapping("/body")
	public void delete(@RequestBody(required = false) Reserva reserva) {
		this.reservaService.create(reserva);
	}
	
	@DeleteMapping
	public Integer deleteById(@RequestBody(required = false) ReservaId reservaId) {
		return this.reservaService.deleteById(reservaId);
	}
	
	@PostMapping("/data-expiracao")
	public List<Reserva> findAllByExpirationDate(@RequestBody(required = false) Date date) {
		return this.reservaService.findAllByExpirationDate(date);
	}
	
	@GetMapping("/update/{idUsuario}/{idItem}")
	public Integer updateByIds(@PathVariable("idUsuario") Long idUsuarioComum, @PathVariable("idItem") Long idItemAcervo, @RequestBody(required = false) Calendar dataReserva) {
		return this.reservaService.updateByIds(idUsuarioComum, idItemAcervo, dataReserva);
	}
	
	@GetMapping("/encontrar")
	public Reserva findByIdUserAndNameItem(@PathVariable("idUsuario") Long idUsuarioComum, @RequestBody(required = false) String nameItem) {
		return this.reservaService.findByIdUserAndNameItem(idUsuarioComum, nameItem);
	}
	
}
