package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmprestimoController {

	private final EmprestimoService emprestimoService;

	@GetMapping
	public List<Emprestimo> findAll() {
		return this.emprestimoService.findAll();
	}
	
	@PostMapping
	public Emprestimo create(@RequestBody(required = false) Emprestimo emprestimo) {
		return this.emprestimoService.create(emprestimo);
	}
	
}
