package br.com.ifsp.es4a4.projeto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_emprestimo")
@IdClass(EmprestimoId.class)
public class Emprestimo {
	
	@Id
	@Column(name = "dt_data_retirada")
	@Temporal(TemporalType.DATE)
	private Date dataRetirada;
	
	@Id
	@Column(name = "id_item_acervo", nullable = false, updatable = false)
	protected Long idItemAcervo;
	
	@Id
	@Column(name = "id_usuario_comum", nullable = false, updatable = false)
	protected Long idUsuarioComum;
	
	@Column(name = "dt_data_devoluticao_efetiva")
	@Temporal(TemporalType.DATE)
	private Date dataDevolucaoEfetiva;
	
	@ManyToOne
	@JoinColumn(name = "id_item_acervo", insertable = false, updatable = false)
	private ItemAcervo item;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_comum", insertable = false, updatable = false)
	private UsuarioComum usuarioComum;
	

}
