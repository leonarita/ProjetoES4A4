package br.com.ifsp.es4a4.projeto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
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
@Table(name = "tb_acervo")
public class Acervo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_acervo")
	@SequenceGenerator(name = "sq_acervo", sequenceName = "sq_acervo", allocationSize = 1)
	@Column(name = "id_acervo")
	private Long id;
	
	@Column(name = "st_assunto", nullable = false)
	private String assunto;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acervo")
	private List<ItemAcervo> itemsAcervo;

}
