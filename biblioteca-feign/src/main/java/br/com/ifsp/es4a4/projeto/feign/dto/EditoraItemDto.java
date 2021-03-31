package br.com.ifsp.es4a4.projeto.feign.dto;

import br.com.ifsp.es4a4.projeto.feign.dto.abstracts.ItemAcervoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EditoraItemDto {
	
	protected Long idItemAcervo;
	
	protected Long idInstituicaoEditora;
	
	private ItemAcervoDto item;
	
	private InstituicaoEditoraDto instituicaoEditora;

}
