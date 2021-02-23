package br.com.ifsp.es4a4.projeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.facade.SistemaFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sistema")
@RequiredArgsConstructor
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

}
