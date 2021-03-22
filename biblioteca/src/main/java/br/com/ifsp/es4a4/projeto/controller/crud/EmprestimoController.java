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

import br.com.ifsp.es4a4.projeto.controller.dto.EmprestimoDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.EmprestimoMapper;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmprestimoController {

	private final EmprestimoService emprestimoService;

	@GetMapping
	public List<EmprestimoDto> findAll() {
		return this.emprestimoService.findAll().stream().map(EmprestimoMapper::entityToDto).collect(Collectors.toList());
	}

	@PostMapping
	public EmprestimoDto create(@RequestBody(required = false) EmprestimoDto emprestimo) {
		return EmprestimoMapper.entityToDto(this.emprestimoService.create(EmprestimoMapper.dtoToEntity(emprestimo)));
	}
	
	@DeleteMapping("/body")
	public void delete(@RequestBody(required = false) EmprestimoDto emprestimo) {
		this.emprestimoService.delete(EmprestimoMapper.dtoToEntity(emprestimo));
	}
	
	@DeleteMapping
	public Integer deleteById(@RequestBody(required = false) EmprestimoId emprestimoId) {
		return this.emprestimoService.deleteById(emprestimoId);
	}
	
	
	@PostMapping("/data-devolucao")
	public List<EmprestimoDto> findAllByDevolutionDate(@RequestBody(required = false) Date date) {
		return this.emprestimoService.findAllByDevolutionDate(date).stream().map(EmprestimoMapper::entityToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/update/{idUsuario}/{idItem}")
	public Integer updateByIds(@PathVariable("idUsuario") Long idUsuarioComum, @PathVariable("idItem") Long idItemAcervo, @RequestBody(required = false) Calendar dataRetirada) {
		return this.emprestimoService.updateByIds(idUsuarioComum, idItemAcervo, dataRetirada);
	}
	
	@GetMapping("/{idUsuario}/{idItem}")
	public EmprestimoDto findByIdsAndWasntReturned(@PathVariable("idUsuario") Long idUsuarioComum, @PathVariable("idItem") Long idItemAcervo) {
		return EmprestimoMapper.entityToDto(this.emprestimoService.findByIdsAndWasntReturned(idUsuarioComum, idItemAcervo));
	}
	
}
