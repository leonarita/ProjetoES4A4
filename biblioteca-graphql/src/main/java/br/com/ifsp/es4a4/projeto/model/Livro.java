package br.com.ifsp.es4a4.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
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
@Table(name = "tb_livro")
public class Livro extends ItemAcervo {
	
	@Column(name = "nr_edicao")
	private Short edicao;
	
	@Column(name = "st_isbn", nullable = false)
	private Long isbn;

}
