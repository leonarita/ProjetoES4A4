package br.com.ifsp.es4a4.projeto.feign.dto;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.feign.dto.abstracts.ItemAcervoDto;
import br.com.ifsp.es4a4.projeto.feign.enumerations.TipoTrabalho;
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
public class TrabalhoAcademicoDto extends ItemAcervoDto {
	
	private Date dataDefesa;
	
	private String nomeCurso;
	
	private TipoTrabalho tipoTrabalho;
	
}
