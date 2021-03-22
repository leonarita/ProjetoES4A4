package br.com.ifsp.es4a4.projeto.controller.dto.abstracts;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.controller.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PessoaDto {
	
	protected Long idPessoa;
	
	protected Long cpf;
	
	protected String nome;
	
	protected String sobrenome;
	
	protected Date nascimento;
	
	protected UsuarioDto usuario;


}
