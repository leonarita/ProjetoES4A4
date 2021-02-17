package br.com.ifsp.es4a4.projeto.utils.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {
	
	private final HttpStatus status = HttpStatus.PRECONDITION_FAILED;
	
	public InvalidTokenException() {
		super("O token fornecido está inválido", null, false, false);
	}

}
