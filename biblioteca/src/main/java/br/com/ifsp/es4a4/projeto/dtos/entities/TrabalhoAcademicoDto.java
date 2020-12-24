package br.com.ifsp.es4a4.projeto.dtos.entities;

import java.util.Date;

import br.com.ifsp.es4a4.projeto.enumeration.TipoTrabalho;
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
public class TrabalhoAcademicoDto extends ItemAcervo {
	
	private Date dataDefesa;
	private String nomeCurso;
	private TipoTrabalho tipoTrabalho;

}
