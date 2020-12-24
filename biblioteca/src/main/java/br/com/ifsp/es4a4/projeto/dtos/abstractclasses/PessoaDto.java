package br.com.ifsp.es4a4.projeto.dtos.abstractclasses;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class PessoaDto {
	
	protected long cpf;
	protected String nome;
	protected String sobrenome;
	protected String login;
	protected String email;
	protected String senha;
	protected Date nascimento;

}
