package br.com.ifsp.es4a4.projeto.graphql.datafetcher;

import org.springframework.stereotype.Component;

import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroDto;
import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroMapper;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroIdDataFetcher implements DataFetcher<LivroDto> {
	
	private final LivroService livroService;

	@Override
	public LivroDto get(DataFetchingEnvironment environment) {
		Long id = Long.parseLong(environment.getArgument("id"));
		return LivroMapper.entityToDto(livroService.findById(id));
	}

}
