package br.com.ifsp.es4a4.projeto.utils.routes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ValidationType {
	
	ANNONYMOUS ("Online");
	
	String value;

}
