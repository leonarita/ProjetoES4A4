package br.com.ifsp.es4a4.projeto.controller.dto.abstracts;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.controller.dto.AcervoDto;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAcervoDto {

	protected Long idItemAcervo;
	
	protected String titulo;
	
	protected String subtitulo;
	
	protected String areaConhecimento;
	
	protected String codigoCatalogacao;
	
	protected Date dataPublicacao;
	
	protected Long idAcervo;
	
	protected Situacao situacaoItem;
	
	protected TipoItemAcervo tipoItem;
	
	private AcervoDto acervo;
	
}
