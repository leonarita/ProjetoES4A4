package br.com.ifsp.es4a4.projeto.controller.mapper.abstracts;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.abstracts.ItemAcervoDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.AcervoMapper;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;

public abstract class ItemAcervoMapper {
	
	public static ItemAcervoDto entityToDto(ItemAcervo entity) {

		if(Objects.isNull(entity)) {
			return new ItemAcervoDto();
		}
		
		return ItemAcervoDto.builder()
				.idItemAcervo(entity.getIdAcervo())
				.titulo(entity.getTitulo())
				.subtitulo(entity.getSubtitulo())
				.areaConhecimento(entity.getAreaConhecimento())
				.codigoCatalogacao(entity.getCodigoCatalogacao())
				.dataPublicacao(entity.getDataPublicacao())
				.idAcervo(entity.getIdAcervo())
				.situacaoItem(entity.getSituacaoItem())
				.tipoItem(entity.getTipoItem())
				.acervo(AcervoMapper.entityToDto(entity.getAcervo()))
				.build();
	}
	
//	public static ItemAcervo dtoToEntity(ItemAcervoDto dto) {
//
//		if(Objects.isNull(dto)) {
//			return new ItemAcervo();
//		}
//		
//		return ItemAcervo.builder()
//				.idItemAcervo(dto.getIdAcervo())
//				.titulo(dto.getTitulo())
//				.subtitulo(dto.getSubtitulo())
//				.areaConhecimento(dto.getAreaConhecimento())
//				.codigoCatalogacao(dto.getCodigoCatalogacao())
//				.dataPublicacao(dto.getDataPublicacao())
//				.idAcervo(dto.getIdAcervo())
//				.situacaoItem(dto.getSituacaoItem())
//				.tipoItem(dto.getTipoItem())
//				.acervo(AcervoMapper.dtoToEntity(dto.getAcervo()))
//				.build();
//	}

}
