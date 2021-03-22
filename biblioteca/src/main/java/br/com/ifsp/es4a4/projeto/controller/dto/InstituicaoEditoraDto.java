package br.com.ifsp.es4a4.projeto.controller.dto;

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
public class InstituicaoEditoraDto {
	
	private Long id;
	
	private String cnpj;
	
	private String cidade;
	
	private String nome;
	
}
