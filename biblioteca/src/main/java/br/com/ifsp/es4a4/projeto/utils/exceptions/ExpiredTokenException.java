package br.com.ifsp.es4a4.projeto.utils.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class ExpiredTokenException extends RuntimeException {

	private final HttpStatus status = HttpStatus.PRECONDITION_FAILED;
	
	public ExpiredTokenException() {
		super("O token fornecido está expirado", null, false, false);
	}
}
