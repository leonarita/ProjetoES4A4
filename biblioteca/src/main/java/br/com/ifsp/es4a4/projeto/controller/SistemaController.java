package br.com.ifsp.es4a4.projeto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.facade.SistemaFacade;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sistema")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SistemaController {
	
	private final SistemaFacade sistemaFacade;
	
	@GetMapping("/emprestar/{item}")
	public void emprestarItem(@RequestHeader String Authorization, @PathVariable("item") String tipoItem, @RequestBody(required = false) ItemFiltroDto filtro) {

	}
	
	@GetMapping("/reservar/{item}")
	public void reservarItem(@RequestHeader String Authorization, @PathVariable("item") String tipoItem, @RequestBody(required = false) ItemFiltroDto filtro) {

	}
	
	@GetMapping("/devolver/{item}/{codigoCatalogacao}")
	public void devolverItemEmprestado(@RequestHeader(name = "Authorization") String authorization, @PathVariable("item") String tipoItem, @PathVariable("codigoCatalogacao") String codigoCatalogacao) {
		this.sistemaFacade.devolverItemEmprestado(authorization, tipoItem, codigoCatalogacao);
	}
	
	@PostMapping("/item/livros/disponiveis")
	public List<Livro> pegarLivrosDisponiveis(@RequestBody(required = false) ItemFiltroDto filtro) {
		return this.sistemaFacade.pegarLivrosDisponiveis(filtro);
	}
	
	@PostMapping("/item/revistas/disponiveis")
	public List<Revista> pegarRevistasDisponiveis(@RequestBody(required = false) ItemFiltroDto filtro) {
		return this.sistemaFacade.pegarRevistasDisponiveis(filtro);
	}
	
	@PostMapping("/item/trabalhos-academicos/disponiveis")
	public List<TrabalhoAcademico> pegarTrabalhosAcademicosDisponiveis(@RequestBody(required = false) ItemFiltroDto filtro) {
		return this.sistemaFacade.pegarTrabalhosAcademicoDisponiveis(filtro);
	}

}
