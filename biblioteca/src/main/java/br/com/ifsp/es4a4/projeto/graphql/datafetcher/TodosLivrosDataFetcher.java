package br.com.ifsp.es4a4.projeto.graphql.datafetcher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroDto;
import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroMapper;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodosLivrosDataFetcher implements DataFetcher<List<LivroDto>> {
	
	private final LivroService livroService;

	@Override
	public List<LivroDto> get(DataFetchingEnvironment environment) {
		return this.livroService.findAll().stream().map(LivroMapper::entityToDto).collect(Collectors.toList());
	}

}
