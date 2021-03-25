package br.com.ifsp.es4a4.projeto.graphql.operations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroDto;
import br.com.ifsp.es4a4.projeto.graphql.mapper.LivroMapper;
import br.com.ifsp.es4a4.projeto.service.LivroService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroQueryResolver implements GraphQLQueryResolver {
	
	private final LivroService livroService;
	
	public List<LivroDto> todosLivros() {
		return this.livroService.findAll().stream().map(LivroMapper::entityToDto).collect(Collectors.toList());
	}
	
	public LivroDto livroId(Long id) {
		return LivroMapper.entityToDto(this.livroService.findById(id));
	}
	
	public LivroDto livro(String titulo) {
		return LivroMapper.entityToDto(this.livroService.findByTituloIgnoreCase(titulo).get(0));
	}

}
