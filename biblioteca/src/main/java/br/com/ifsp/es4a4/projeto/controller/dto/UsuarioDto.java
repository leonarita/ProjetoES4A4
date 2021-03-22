package br.com.ifsp.es4a4.projeto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioDto {
	
	protected String login;
	
	protected String email;
	
	protected String senha;
	
	protected Long idUsuario;
	
	private String token;

}
