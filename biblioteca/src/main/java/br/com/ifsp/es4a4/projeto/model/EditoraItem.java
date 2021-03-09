package br.com.ifsp.es4a4.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.pk.EditoraItemId;
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
@Table(name = "tb_editoras_items")
@IdClass(EditoraItemId.class)
public class EditoraItem {
	
	@Id
	@Column(name = "id_item_acervo", nullable = false, updatable = false)
	protected Long idItemAcervo;
	
	@Id
	@Column(name = "id_instituicao_editora", nullable = false, updatable = false)
	protected Long idInstituicaoEditora;
	
	@ManyToOne
	@JoinColumn(name = "id_item_acervo", insertable = false, updatable = false)
	private ItemAcervo item;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao_editora", insertable = false, updatable = false)
	private InstituicaoEditora instituicaoEditora;

}
