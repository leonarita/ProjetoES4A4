package br.com.ifsp.es4a4.projeto.model.abstracts;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifsp.es4a4.projeto.model.Acervo;
import br.com.ifsp.es4a4.projeto.model.Autoria;
import br.com.ifsp.es4a4.projeto.model.EditoraItem;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ItemAcervo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_item_acervo")
	@SequenceGenerator(name = "sq_item_acervo", sequenceName = "sq_item_acervo", allocationSize = 1)
	@Column(name = "id_item_acervo")
	protected Long idItemAcervo;
	
	@Column(name = "st_titulo", nullable = false)
	protected String titulo;
	
	@Column(name = "st_subtitulo")
	protected String subtitulo;
	
	@Column(name = "st_area_conhecimento", nullable = false)
	protected String areaConhecimento;
	
	@Column(name = "st_codigo_catalogacao", nullable = false)
	protected String codigoCatalogacao;
	
	@Column(name = "dt_data_publicacao")
	@Temporal(TemporalType.DATE)
	protected Date dataPublicacao;
	
	@Column(name = "id_acervo")
	protected Long idAcervo;
	
	@Convert(converter = Situacao.Converter.class)
	@Column(name = "nr_situacao_item", nullable = false)
	protected Situacao situacaoItem;
	
	@ManyToOne
	@JoinColumn(name = "id_acervo", insertable = false, updatable = false)
	private Acervo acervo;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	private List<Autoria> autorias;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<Emprestimo> emprestimos;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item") 
	private List<Reserva> reservas;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<EditoraItem> editoras;
	
}
