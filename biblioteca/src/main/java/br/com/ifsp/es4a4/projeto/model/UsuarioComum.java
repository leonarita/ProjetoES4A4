package br.com.ifsp.es4a4.projeto.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifsp.es4a4.projeto.model.abstracts.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(name = "tb_usuario_comum")
public class UsuarioComum extends Pessoa {
	
	@Column(name = "dt_data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_comum")
	private List<Emprestimo> emprestimos;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_comum")
	private List<Reserva> reservas;

}
