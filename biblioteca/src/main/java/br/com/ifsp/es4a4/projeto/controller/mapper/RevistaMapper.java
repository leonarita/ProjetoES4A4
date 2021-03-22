package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.RevistaDto;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;

public abstract class RevistaMapper {
	
	public static RevistaDto entityToDto(Revista entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return RevistaDto.builder()
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
				.issn(entity.getIssn())
				.numero(entity.getNumero())
				.volume(entity.getVolume())
				.build();
	}
	
	public static Revista dtoToEntity(RevistaDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Revista.builder()
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
				.issn(dto.getIssn())
				.numero(dto.getNumero())
				.volume(dto.getVolume())
				.build();
	}

}
