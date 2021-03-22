package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.AcervoDto;
import br.com.ifsp.es4a4.projeto.model.Acervo;

public abstract class AcervoMapper {
	
	public static AcervoDto entityToDto(Acervo entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return AcervoDto.builder()
				.id(entity.getId())
				.assunto(entity.getAssunto())
				.build();
	}
	
	public static Acervo dtoToEntity(AcervoDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Acervo.builder()
				.id(dto.getId())
				.assunto(dto.getAssunto())
				.build();
	}

}
