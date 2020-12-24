package br.com.ifsp.es4a4.projeto.models.abstractclasses;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifsp.es4a4.projeto.enumeration.Situacao;
import br.com.ifsp.es4a4.projeto.models.entities.Acervo;
import br.com.ifsp.es4a4.projeto.models.entities.Autoria;
import br.com.ifsp.es4a4.projeto.models.entities.Emprestimo;
import br.com.ifsp.es4a4.projeto.models.entities.InstituicaoEditora;
import br.com.ifsp.es4a4.projeto.models.entities.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@Entity
//@EqualsAndHashCode
//@Table(name = "tb_item_acervo")
public abstract class ItemAcervo {
	
//	@Column(name = "st_titulo", nullable = false)
	protected String titulo;
	
//	@Column(name = "st_subtitulo")
	protected String subtitulo;
	
//	@Column(name = "st_area_conhecimento")
	protected String areaConhecimento;
	
//	@Column(name = "st_codigo_catalogacao")
	protected String codigoCatalogacao;
	
//	@Column(name = "dt_data_publicacao")
//	@Temporal(TemporalType.DATE)
	protected Date dataPublicacao;
	
//	@EqualsAndHashCode.Exclude
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_situacao_item", insertable = false, updatable = false)
	protected Situacao situacaoItem;
	
//	@EqualsAndHashCode.Exclude
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_acervo", insertable = false, updatable = false)
	protected Acervo acervo;
	
//	@EqualsAndHashCode.Exclude
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_autoria", insertable = false, updatable = false)
	protected List<Autoria> autorias;
	
//	@EqualsAndHashCode.Exclude
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_editora", insertable = false, updatable = false)
	protected List<InstituicaoEditora> editoras;
	
//	@EqualsAndHashCode.Exclude
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_emprestimo", insertable = false, updatable = false)
	protected List<Emprestimo> emprestimos;
	
//	@EqualsAndHashCode.Exclude
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_reserva", insertable = false, updatable = false)
	protected List<Reserva> reservas;

}
