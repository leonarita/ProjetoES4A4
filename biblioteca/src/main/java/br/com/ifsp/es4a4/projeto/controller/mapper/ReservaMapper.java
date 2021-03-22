package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.ReservaDto;
import br.com.ifsp.es4a4.projeto.controller.mapper.abstracts.ItemAcervoMapper;
import br.com.ifsp.es4a4.projeto.model.Reserva;

public abstract class ReservaMapper {
	
	public static ReservaDto entityToDto(Reserva entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return ReservaDto.builder()
				.dataExpiracao(entity.getDataExpiracao())
				.dataReserva(entity.getDataReserva())
				.idItemAcervo(entity.getIdItemAcervo())
				.idUsuarioComum(entity.getIdUsuarioComum())
				.foiRetirado(entity.getFoiRetirado())
				.usuarioComum(UsuarioComumMapper.entityToDto(entity.getUsuarioComum()))
				.item(ItemAcervoMapper.entityToDto(entity.getItem()))
				.build();
	}
	
	public static Reserva dtoToEntity(ReservaDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Reserva.builder()
				.dataExpiracao(dto.getDataExpiracao())
				.dataReserva(dto.getDataReserva())
				.idItemAcervo(dto.getIdItemAcervo())
				.idUsuarioComum(dto.getIdUsuarioComum())
				.foiRetirado(dto.getFoiRetirado())
				.usuarioComum(UsuarioComumMapper.dtoToEntity(dto.getUsuarioComum()))
				//
				.build();
	}

}
