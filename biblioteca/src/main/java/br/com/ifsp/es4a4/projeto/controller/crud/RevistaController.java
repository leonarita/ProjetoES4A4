package br.com.ifsp.es4a4.projeto.controller.crud;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.service.RevistaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/revista")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RevistaController {
	
	private final RevistaService revistaService;

	@GetMapping
	public List<Revista> findAll() {
		return this.revistaService.findAll();
	}

	@GetMapping("/{id}")
	public Revista findAById(@PathVariable Long id) {
		return this.revistaService.findById(id);
	}
	
	@PostMapping
	public Revista create(@RequestBody(required = false) Revista revista) {
		return this.revistaService.create(revista);
	}
	
	@PutMapping
	public Revista update(@PathVariable Long id, @RequestBody(required = false) Revista revista) {
		revista.setIdAcervo(id);
		return this.revistaService.create(revista);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.revistaService.deleteById(id);
	}

}
