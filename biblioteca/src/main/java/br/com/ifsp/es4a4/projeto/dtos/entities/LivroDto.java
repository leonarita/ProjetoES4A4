package br.com.ifsp.es4a4.projeto.dtos.entities;

import br.com.ifsp.es4a4.projeto.models.abstractclasses.ItemAcervo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LivroDto extends ItemAcervo {
	
	private short edicao;
	private long isbn;

}
