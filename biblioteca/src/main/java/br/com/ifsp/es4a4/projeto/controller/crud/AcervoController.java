package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Acervo;
import br.com.ifsp.es4a4.projeto.service.AcervoService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/acervo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AcervoController {
	
	private final AcervoService acervoService;
	
	@GetMapping
	public List<Acervo> findAll() {
		return this.acervoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Acervo findAById(@PathVariable Long id) {
		return this.acervoService.findById(id);
	}
	
	@PostMapping
	public Acervo create(@RequestBody(required = false) Acervo acervo) {
		return this.acervoService.create(acervo);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.acervoService.deleteById(id);
	}

}
