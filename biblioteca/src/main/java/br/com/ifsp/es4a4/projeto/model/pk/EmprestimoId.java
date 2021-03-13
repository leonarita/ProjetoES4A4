package br.com.ifsp.es4a4.projeto.model.pk;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
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

	private Calendar dataRetirada;
	
	protected Long idItemAcervo;
	
	protected Long idUsuarioComum;
}
