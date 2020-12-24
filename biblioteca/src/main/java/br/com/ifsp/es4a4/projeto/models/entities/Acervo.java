package br.com.ifsp.es4a4.projeto.models.entities;

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
public class Acervo {

	private String assunto;
	private Bibliotecario gestor;
	private List<ItemAcervo> itemsAcervo;
	
}
