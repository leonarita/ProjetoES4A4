package br.com.ifsp.es4a4.projeto.dtos.entities;

import java.util.List;
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
public class AcervoDto {

	private String assunto;
	private BibliotecarioDto gestor;
	private List<ItemAcervo> itemsAcervo;
	
}
