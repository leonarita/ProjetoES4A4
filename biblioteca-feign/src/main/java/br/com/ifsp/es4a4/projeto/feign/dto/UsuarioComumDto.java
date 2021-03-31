package br.com.ifsp.es4a4.projeto.feign.dto;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.feign.dto.abstracts.PessoaDto;
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
public class UsuarioComumDto extends PessoaDto {
	
	private Date dataCadastro;
	
	private Integer idUsuario;
	

}
