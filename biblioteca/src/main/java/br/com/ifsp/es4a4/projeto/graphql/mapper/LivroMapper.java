package br.com.ifsp.es4a4.projeto.graphql.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.model.Livro;

public abstract class LivroMapper {

	public static LivroDto entityToDto(Livro entity) {
		
		if(Objects.isNull(entity)) {
			return new LivroDto();
		}
		
		return LivroDto.builder()
				.id(entity.getIdItemAcervo())
				.titulo(entity.getTitulo())
				.subtitulo(entity.getSubtitulo())
				.isbn(entity.getIsbn())
				.build();
	}
}
