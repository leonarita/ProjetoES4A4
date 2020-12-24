package br.com.ifsp.es4a4.projeto.models.entities;

import java.util.Date;
import java.util.List;
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
public class UsuarioComum extends Pessoa {
	
	private Date dataCadastro;
	private int idUsuario;
	private List<Emprestimo> emprestimos;
	private List<Reserva> reservas;

}
