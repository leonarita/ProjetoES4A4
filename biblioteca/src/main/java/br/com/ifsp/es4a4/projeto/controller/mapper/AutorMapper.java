package br.com.ifsp.es4a4.projeto.controller.mapper;

import java.util.Objects;

import br.com.ifsp.es4a4.projeto.controller.dto.AutorDto;
import br.com.ifsp.es4a4.projeto.model.Autor;

public abstract class AutorMapper {
	
	public static AutorDto entityToDto(Autor entity) {

		if(Objects.isNull(entity)) {
			return null;
		}
		
		return AutorDto.builder()
				.idPessoa(entity.getIdPessoa())
				.cpf(entity.getCpf())
				.nome(entity.getNome())
				.sobrenome(entity.getSobrenome())
				.nascimento(entity.getNascimento())
				.nomeCitacoes(entity.getNomeCitacoes())
				.orcid(entity.getOrcid())
				.usuario(UsuarioMapper.entityToDto(entity.getUsuario()))
				.build();
	}
	
	public static Autor dtoToEntity(AutorDto dto) {

		if(Objects.isNull(dto)) {
			return null;
		}
		
		return Autor.builder()
				.idPessoa(dto.getIdPessoa())
				.cpf(dto.getCpf())
				.nome(dto.getNome())
				.sobrenome(dto.getSobrenome())
				.nascimento(dto.getNascimento())
				.nomeCitacoes(dto.getNomeCitacoes())
				.orcid(dto.getOrcid())
				.usuario(UsuarioMapper.dtoToEntity(dto.getUsuario()))
				.build();
	}

}
