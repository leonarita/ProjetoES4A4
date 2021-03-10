package br.com.ifsp.es4a4.projeto.facade;

import java.util.Date;
import java.util.List;

import br.com.ifsp.es4a4.projeto.model.enumerations.TipoTrabalho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemFiltroDto {
	
	// ITEMS
	protected String titulo;
	protected String subtitulo;
	protected List<String> areaConhecimento;
	protected Date dataPublicacao;
	protected Long idAcervo;
	
	// LIVRO
	private Short edicao;
	private Long isbn;
	
	// REVISTA
	private Integer issn;
	private Short numero;
	private Short volume;
	
	// TRABALHO ACADÊMICO
	private Date dataDefesa;
	private String nomeCurso;
	private TipoTrabalho tipoTrabalho;

}
