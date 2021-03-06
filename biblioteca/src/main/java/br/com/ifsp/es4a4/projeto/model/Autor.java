package br.com.ifsp.es4a4.projeto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_autor")
public class Autor extends Pessoa {

	@Column(name = "st_nome_citacoes", nullable = false)
	private String nomeCitacoes;
	
	@Column(name = "st_orcid", nullable = false)
	private String orcid;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private List<Autoria> autorias;
	
}
