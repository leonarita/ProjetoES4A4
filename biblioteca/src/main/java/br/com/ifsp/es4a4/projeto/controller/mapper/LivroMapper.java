package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.LivroDto;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;

public abstract class LivroMapper {
	
	public static LivroDto entityToDto(Livro entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return LivroDto.builder()
				.idItemAcervo(entity.getIdItemAcervo())
				.titulo(entity.getTitulo())
				.subtitulo(entity.getSubtitulo())
				.areaConhecimento(entity.getAreaConhecimento())
				.codigoCatalogacao(entity.getCodigoCatalogacao())
				.dataPublicacao(entity.getDataPublicacao())
				.idAcervo(entity.getIdAcervo())
				.situacaoItem(entity.getSituacaoItem())
				.tipoItem(entity.getTipoItem())
				.acervo(AcervoMapper.entityToDto(entity.getAcervo()))
				.edicao(entity.getEdicao())
				.isbn(entity.getIsbn())
				.build();
	}
	
	public static Livro dtoToEntity(LivroDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Livro.builder()
				.idItemAcervo(dto.getIdItemAcervo())
				.titulo(dto.getTitulo())
				.subtitulo(dto.getSubtitulo())
				.areaConhecimento(dto.getAreaConhecimento())
				.codigoCatalogacao(dto.getCodigoCatalogacao())
				.dataPublicacao(dto.getDataPublicacao())
				.idAcervo(dto.getIdAcervo())
				.situacaoItem(Objects.isNull(dto.getSituacaoItem()) ? Situacao.DISPONIVEL : dto.getSituacaoItem())
				.tipoItem(dto.getTipoItem())
				.acervo(AcervoMapper.dtoToEntity(dto.getAcervo()))
				.edicao(dto.getEdicao())
				.isbn(dto.getIsbn())
				.build();
	}

}
