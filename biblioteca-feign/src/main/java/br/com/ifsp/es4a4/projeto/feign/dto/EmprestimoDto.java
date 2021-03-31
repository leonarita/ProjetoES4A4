package br.com.ifsp.es4a4.projeto.feign.dto;

import java.util.Calendar;
import java.util.Date;

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
public class EmprestimoDto {
	
	private Calendar dataRetirada;
	
	protected Long idItemAcervo;
	
	protected Long idUsuarioComum;
	
	private Date dataDevolucaoEfetiva;
	
	private ItemAcervoDto item;
	
	private UsuarioComumDto usuarioComum;
	
	private Boolean foiDevolvido;

}
