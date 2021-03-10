package br.com.ifsp.es4a4.projeto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	public void emprestarItem(@RequestHeader String Authorization) {

	}
	
	public void devolverItemEmprestado() {
		
	}
	
	public void reservarItem() {
		
	}
	
	public void validarItemReservadoNaoBuscado() {
		
	}
	
	public void pegarItemParaConsultaLocal() {
		
	}
	
	public void pegarItemPorFiltros() {
		
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
