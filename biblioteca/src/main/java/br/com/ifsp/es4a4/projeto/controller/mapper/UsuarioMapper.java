package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.UsuarioDto;
import br.com.ifsp.es4a4.projeto.model.Usuario;

public abstract class UsuarioMapper {
	
	public static UsuarioDto entityToDto(Usuario entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return UsuarioDto.builder()
				.idUsuario(entity.getIdUsuario())
				.login(entity.getLogin())
				.email(entity.getEmail())
				.senha(entity.getSenha())
				.token(entity.getToken())
				.build();
	}
	
	public static Usuario dtoToEntity(UsuarioDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Usuario.builder()
				.idUsuario(dto.getIdUsuario())
				.login(dto.getLogin())
				.email(dto.getEmail())
				.senha(dto.getSenha())
				.token(dto.getToken())
				.build();
	}

}
