package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.AutoriaDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.abstracts.ItemAcervoMapper;
import br.com.ifsp.es4a4.projeto.model.Autoria;

public abstract class AutoriaMapper {
	
	public static AutoriaDto entityToDto(Autoria entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return AutoriaDto.builder()
				.idAutor(entity.getIdAutor())
				.idItemAcervo(entity.getIdItemAcervo())
				.eEditor(entity.getEEditor())
				.autor(AutorMapper.entityToDto(entity.getAutor()))
				.item(ItemAcervoMapper.entityToDto(entity.getItem()))
				.build();
	}
	
	public static Autoria dtoToEntity(AutoriaDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Autoria.builder()
				.idAutor(dto.getIdAutor())
				.idItemAcervo(dto.getIdItemAcervo())
				.eEditor(dto.getEEditor())
				.autor(AutorMapper.dtoToEntity(dto.getAutor()))
				//
				.build();
	}

}
