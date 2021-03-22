package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.EmprestimoDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.abstracts.ItemAcervoMapper;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;

public abstract class EmprestimoMapper {
	
	public static EmprestimoDto entityToDto(Emprestimo entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return EmprestimoDto.builder()
				.dataRetirada(entity.getDataRetirada())
				.dataDevolucaoEfetiva(entity.getDataDevolucaoEfetiva())
				.idItemAcervo(entity.getIdItemAcervo())
				.idUsuarioComum(entity.getIdUsuarioComum())
				.foiDevolvido(entity.getFoiDevolvido())
				.usuarioComum(UsuarioComumMapper.entityToDto(entity.getUsuarioComum()))
				.item(ItemAcervoMapper.entityToDto(entity.getItem()))
				.build();
	}
	
	public static Emprestimo dtoToEntity(EmprestimoDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Emprestimo.builder()
				.dataRetirada(dto.getDataRetirada())
				.dataDevolucaoEfetiva(dto.getDataDevolucaoEfetiva())
				.idItemAcervo(dto.getIdItemAcervo())
				.idUsuarioComum(dto.getIdUsuarioComum())
				.foiDevolvido(dto.getFoiDevolvido())
				.usuarioComum(UsuarioComumMapper.dtoToEntity(dto.getUsuarioComum()))
				//
				.build();
	}

}
