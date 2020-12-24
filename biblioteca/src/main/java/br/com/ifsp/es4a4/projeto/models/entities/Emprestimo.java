package br.com.ifsp.es4a4.projeto.models.entities;

import java.time.LocalDateTime;
import java.util.Date;

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
public class Emprestimo {
	
	private LocalDateTime dataRetirada;
	private LocalDateTime dataDevolucaoEfetiva;
	private Date dataDevolucaoPrevista;
	private ItemAcervo itemAcervo;
	private UsuarioComum usuarioComum;

}
