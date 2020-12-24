package br.com.ifsp.es4a4.projeto.models.abstractclasses;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@Entity
@EqualsAndHashCode
//@Table(name = "tb_pessoa")
public abstract class Pessoa {
	
	protected long cpf;
	protected String nome;
	protected String sobrenome;
	protected String login;
	protected String email;
	protected String senha;
	protected Date nascimento;

}
