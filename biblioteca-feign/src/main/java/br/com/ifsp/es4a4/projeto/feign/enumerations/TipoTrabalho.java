package br.com.ifsp.es4a4.projeto.feign.enumerations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoTrabalho {
	
	DISSERTACAO(1),
	MONOGRAFIA(2),
	TESE(3);
	
	private int id;

}
