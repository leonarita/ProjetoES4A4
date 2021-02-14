package br.com.ifsp.es4a4.projeto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoTrabalho;
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
@Table(name = "tb_trabalho_academico")
public class TrabalhoAcademico extends ItemAcervo {
	
	@Column(name = "dt_data_defesa")
	@Temporal(TemporalType.DATE)
	private Date dataDefesa;
	
	@Column(name = "st_nome_curso", nullable = false)
	private String nomeCurso;
	
	@Convert(converter = TipoTrabalho.Converter.class)
	@Column(name = "nr_tipo_trabalho", nullable = false)
	private TipoTrabalho tipoTrabalho;
	
}
