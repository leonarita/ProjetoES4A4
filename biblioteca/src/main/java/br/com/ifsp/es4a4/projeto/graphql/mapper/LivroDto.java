package br.com.ifsp.es4a4.projeto.graphql.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {
	
	private Long id;
	
	private String titulo;
	
	private String subtitulo;
	
	private Long isbn;

}
