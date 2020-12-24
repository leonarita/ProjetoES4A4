package br.com.ifsp.es4a4.projeto.dtos.abstractclasses;

import java.util.Date;
import java.util.List;

import br.com.ifsp.es4a4.projeto.enumeration.Situacao;
import br.com.ifsp.es4a4.projeto.models.entities.Acervo;
import br.com.ifsp.es4a4.projeto.models.entities.Autoria;
import br.com.ifsp.es4a4.projeto.models.entities.Emprestimo;
import br.com.ifsp.es4a4.projeto.models.entities.InstituicaoEditora;
import br.com.ifsp.es4a4.projeto.models.entities.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class ItemAcervoDto {
	
	protected String titulo;
	protected String subtitulo;
	protected String areaConhecimento;
	protected String codigoCatalogacao;
	protected Date dataPublicacao;
	protected Situacao situacaoItem;
	protected Acervo acervo;
	protected List<Autoria> autorias;
	protected List<InstituicaoEditora> editoras;
	protected List<Emprestimo> emprestimos;
	protected List<Reserva> reservas;


}
