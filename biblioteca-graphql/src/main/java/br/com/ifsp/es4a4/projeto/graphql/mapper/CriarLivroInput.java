package br.com.ifsp.es4a4.projeto.graphql.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarLivroInput {
	
	private String titulo;
	
	private String areaConhecimento;
	
	private String codigoCatalogacao;
	
	private Long isbn;

}
