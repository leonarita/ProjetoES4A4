package br.com.ifsp.es4a4.projeto.models.entities;

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
public class Autoria {
	
	private boolean eEditor;
	private Autor autor;
	private ItemAcervo itemAcervo;

}
