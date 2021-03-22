package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.TrabalhoAcademicoDto;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;

public abstract class TrabalhoAcademicoMapper {
	
	public static TrabalhoAcademicoDto entityToDto(TrabalhoAcademico entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return TrabalhoAcademicoDto.builder()
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
				.dataDefesa(entity.getDataDefesa())
				.nomeCurso(entity.getNomeCurso())
				.tipoTrabalho(entity.getTipoTrabalho())
				.build();
	}
	
	public static TrabalhoAcademico dtoToEntity(TrabalhoAcademicoDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return TrabalhoAcademico.builder()
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
				.dataDefesa(dto.getDataDefesa())
				.nomeCurso(dto.getNomeCurso())
				.tipoTrabalho(dto.getTipoTrabalho())
				.build();
	}

}
