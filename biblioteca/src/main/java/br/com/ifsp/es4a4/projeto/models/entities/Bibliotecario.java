package br.com.ifsp.es4a4.projeto.models.entities;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.models.abstractclasses.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Bibliotecario extends Pessoa {
	
	private Date dataAdmissao;
	private int idFuncionario;

}
