package br.com.ifsp.es4a4.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.ifsp.es4a4.projeto.model.pk.AutoriaId;
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
@Table(name = "tb_autoria")
@IdClass(AutoriaId.class)
public class Autoria {

	@Id
	@Column(name = "id_item_acervo", nullable = false, updatable = false)
	protected Long idItemAcervo;
	
	@Id
	@Column(name = "id_autor", nullable = false, updatable = false, insertable = false)
	protected Long idAutor;

	@Column(name = "bl_editor")
	private Boolean eEditor;
}
