package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.UsuarioComumDto;
import br.com.ifsp.es4a4.projeto.model.UsuarioComum;

public abstract class UsuarioComumMapper {
	
	public static UsuarioComumDto entityToDto(UsuarioComum entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return UsuarioComumDto.builder()
				.idPessoa(entity.getIdPessoa())
				.cpf(entity.getCpf())
				.nome(entity.getNome())
				.sobrenome(entity.getSobrenome())
				.nascimento(entity.getNascimento())
				.dataCadastro(entity.getDataCadastro())
				.idUsuario(entity.getIdUsuario())
				.usuario(UsuarioMapper.entityToDto(entity.getUsuario()))
				.build();
	}
	
	public static UsuarioComum dtoToEntity(UsuarioComumDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return UsuarioComum.builder()
				.idPessoa(dto.getIdPessoa())
				.cpf(dto.getCpf())
				.nome(dto.getNome())
				.sobrenome(dto.getSobrenome())
				.nascimento(dto.getNascimento())
				.dataCadastro(dto.getDataCadastro())
				.idUsuario(dto.getIdUsuario())
				.usuario(UsuarioMapper.dtoToEntity(dto.getUsuario()))
				.build();
	}

}
