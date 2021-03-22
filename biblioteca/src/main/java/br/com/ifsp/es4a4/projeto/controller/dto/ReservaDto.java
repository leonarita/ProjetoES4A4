package br.com.ifsp.es4a4.projeto.controller.dto;

import java.util.Calendar;
import java.util.Date;

import br.com.ifsp.es4a4.projeto.controller.dto.abstracts.ItemAcervoDto;
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
public class ReservaDto {
	
	protected Long idItemAcervo;

	protected Long idUsuarioComum;
	
	private Calendar dataReserva;

	private Date dataExpiracao;
	
	private ItemAcervoDto item;
	
	private UsuarioComumDto usuarioComum;
	
	private Boolean foiRetirado;

}
