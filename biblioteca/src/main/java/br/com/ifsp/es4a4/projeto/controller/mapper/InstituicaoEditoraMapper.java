package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.InstituicaoEditoraDto;
import br.com.ifsp.es4a4.projeto.model.InstituicaoEditora;

public abstract class InstituicaoEditoraMapper {
	
	public static InstituicaoEditoraDto entityToDto(InstituicaoEditora entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return InstituicaoEditoraDto.builder()
				.id(entity.getId())
				.cnpj(entity.getCnpj())
				.cidade(entity.getCidade())
				.nome(entity.getNome())
				.build();
	}
	
	public static InstituicaoEditora dtoToEntity(InstituicaoEditoraDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return InstituicaoEditora.builder()
				.id(dto.getId())
				.cnpj(dto.getCnpj())
				.cidade(dto.getCidade())
				.nome(dto.getNome())
				.build();
	}

}
