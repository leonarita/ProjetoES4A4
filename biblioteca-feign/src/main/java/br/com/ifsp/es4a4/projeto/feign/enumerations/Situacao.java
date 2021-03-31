package br.com.ifsp.es4a4.projeto.feign.enumerations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Situacao {
	
	CONSULTA_LOCAL(1),
	DISPONIVEL(2),
	EMPRESTADO(3),
	RESERVADO(4);
	
	private int id;

}
