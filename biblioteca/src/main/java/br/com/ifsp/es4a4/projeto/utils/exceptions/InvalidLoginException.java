package br.com.ifsp.es4a4.projeto.utils.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class InvalidLoginException extends RuntimeException {

	private final HttpStatus status = HttpStatus.FORBIDDEN;
	
	public InvalidLoginException() {
		super("As credenciais de login não foram encontradas no sistema", null, false, false);
	}
}
