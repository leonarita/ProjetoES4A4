package br.com.ifsp.es4a4.projeto.feign.dto;

import br.com.ifsp.es4a4.projeto.feign.dto.abstracts.ItemAcervoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RevistaDto extends ItemAcervoDto {
	
	private Integer issn;
	
	private Short numero;
	
	private Short volume;

}
