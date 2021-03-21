package br.com.ifsp.es4a4.projeto.graphql.mapper;

import java.util.Date;
import java.util.Objects;

import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;

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
	
	public static Livro dtoToEntity(CriarLivroInput dto) {
		
		if(Objects.isNull(dto)) {
			return new Livro();
		}
		
		return Livro.builder()
				.titulo(dto.getTitulo())
				.areaConhecimento(dto.getAreaConhecimento())
				.codigoCatalogacao(dto.getCodigoCatalogacao())
				.isbn(dto.getIsbn())
				.dataPublicacao(new Date())
				.tipoItem(TipoItemAcervo.LIVRO)
				.situacaoItem(Situacao.DISPONIVEL)
				.build();
	}
}
