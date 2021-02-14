package br.com.ifsp.es4a4.projeto.model.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class EmprestimoId implements Serializable {

	@Column(name = "dt_data_retirada")
	@Temporal(TemporalType.DATE)
	private Date dataRetirada;
	
	@Column(name = "id_item_acervo", nullable = false, updatable = false)
	protected Long idItemAcervo;
	
	@Column(name = "id_usuario_comum", nullable = false, updatable = false)
	protected Long idUsuarioComum;
}
