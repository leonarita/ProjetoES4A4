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
public class AutoriaDto {

	protected Long idItemAcervo;
	
	protected Long idAutor;

	private Boolean eEditor;
	
	private AutorDto autor;
	
	private ItemAcervoDto item;
}
