package br.com.ifsp.es4a4.projeto.feign.enumerations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoItemAcervo {
	
	LIVRO(1),
	REVISTA(2),
	TRABALHO_ACADEMICO(3);
	
	private int id;

}
