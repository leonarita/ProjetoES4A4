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
@EqualsAndHashCode
public class AutoriaDto {
	
	private boolean eEditor;
	private AutorDto autor;
	private ItemAcervo itemAcervo;

}
