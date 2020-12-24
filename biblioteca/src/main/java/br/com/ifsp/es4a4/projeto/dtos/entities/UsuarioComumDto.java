package br.com.ifsp.es4a4.projeto.dtos.entities;

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
public class UsuarioComumDto extends Pessoa {
	
	private Date dataCadastro;
	private int idUsuario;
	private List<EmprestimoDto> emprestimos;
	private List<ReservaDto> reservas;

}
