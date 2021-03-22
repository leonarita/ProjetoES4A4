package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.controller.dto.ReservaDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.ReservaMapper;
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
	public List<ReservaDto> findAll() {
		return this.reservaService.findAll().stream().map(ReservaMapper::entityToDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public ReservaDto create(@RequestBody(required = false) ReservaDto reserva) {
		return ReservaMapper.entityToDto(this.reservaService.create(ReservaMapper.dtoToEntity(reserva)));
	}
	
	@DeleteMapping("/body")
	public void delete(@RequestBody(required = false) ReservaDto reserva) {
		this.reservaService.create(ReservaMapper.dtoToEntity(reserva));
	}
	
	@DeleteMapping
	public Integer deleteById(@RequestBody(required = false) ReservaId reservaId) {
		return this.reservaService.deleteById(reservaId);
	}
	
	
	@PostMapping("/data-expiracao")
	public List<ReservaDto> findAllByExpirationDate(@RequestBody(required = false) Date date) {
		return this.reservaService.findAllByExpirationDate(date).stream().map(ReservaMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/update/{idUsuario}/{idItem}")
	public Integer updateByIds(@PathVariable("idUsuario") Long idUsuarioComum, @PathVariable("idItem") Long idItemAcervo, @RequestBody(required = false) Calendar dataReserva) {
		return this.reservaService.updateByIds(idUsuarioComum, idItemAcervo, dataReserva);
	}
	
	@GetMapping("/encontrar")
	public ReservaDto findByIdUserAndNameItem(@PathVariable("idUsuario") Long idUsuarioComum, @RequestBody(required = false) String nameItem) {
		return ReservaMapper.entityToDto(this.reservaService.findByIdUserAndNameItem(idUsuarioComum, nameItem));
	}
	
}
