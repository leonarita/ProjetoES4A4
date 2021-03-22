package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.EditoraItemDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.abstracts.ItemAcervoMapper;
import br.com.ifsp.es4a4.projeto.model.EditoraItem;

public abstract class EditoraItemMapper {
	
	public static EditoraItemDto entityToDto(EditoraItem entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return EditoraItemDto.builder()
				.idInstituicaoEditora(entity.getIdInstituicaoEditora())
				.idItemAcervo(entity.getIdItemAcervo())
				.instituicaoEditora(InstituicaoEditoraMapper.entityToDto(entity.getInstituicaoEditora()))
				.item(ItemAcervoMapper.entityToDto(entity.getItem()))
				.build();
	}
	
	public static EditoraItem dtoToEntity(EditoraItemDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return EditoraItem.builder()
				.idInstituicaoEditora(dto.getIdInstituicaoEditora())
				.idItemAcervo(dto.getIdItemAcervo())
				.instituicaoEditora(InstituicaoEditoraMapper.dtoToEntity(dto.getInstituicaoEditora()))
				//
				.build();
	}

}
