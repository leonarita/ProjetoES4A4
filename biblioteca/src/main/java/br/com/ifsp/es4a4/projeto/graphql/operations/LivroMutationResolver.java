package br.com.ifsp.es4a4.projeto.graphql.operations;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import br.com.ifsp.es4a4.projeto.graphql.mapper.CriarLivroInput;
import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroDto;
import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroMapper;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;

@Validated
@Component
@RequiredArgsConstructor
public class LivroMutationResolver implements GraphQLMutationResolver {
	
	private final LivroService livroService;
	
	public LivroDto criarLivro(@Validated CriarLivroInput input) {
		return LivroMapper.entityToDto(this.livroService.create(LivroMapper.dtoToEntity(input)));
	}

}
