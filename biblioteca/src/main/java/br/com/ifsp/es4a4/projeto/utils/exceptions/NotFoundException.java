package br.com.ifsp.es4a4.projeto.utils.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	
	private final HttpStatus status = HttpStatus.NOT_FOUND;
	
	public NotFoundException(String message) {
		super(message);
	}

}
