package br.com.ifsp.es4a4.projeto.controller.dto;

import br.com.ifsp.es4a4.projeto.controller.dto.abstracts.PessoaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AutorDto extends PessoaDto {

	private String nomeCitacoes;
	
	private String orcid;
	
}
