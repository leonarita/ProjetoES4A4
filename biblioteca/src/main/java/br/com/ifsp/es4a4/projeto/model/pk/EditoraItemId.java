package br.com.ifsp.es4a4.projeto.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
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
public class EditoraItemId implements Serializable {
	
	@Column(name = "id_item_acervo", nullable = false, updatable = false)
	protected Long idItemAcervo;
	
	@Column(name = "id_instituicao_editora", nullable = false, updatable = false)
	protected Long idInstituicaoEditora;

}
