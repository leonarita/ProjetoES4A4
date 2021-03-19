package br.com.ifsp.es4a4.projeto.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.graphql.datafetcher.LivroIdDataFetcher;
import br.com.ifsp.es4a4.projeto.graphql.datafetcher.TodosLivrosDataFetcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GraphQLService {
	
	@Value("classpath:query.graphqls")
	Resource resource;
	
	private GraphQL graphQL;
	
	private final LivroIdDataFetcher bookDataFetcher;
	private final TodosLivrosDataFetcher todosLivrosDataFetcher;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();
		
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("livro", bookDataFetcher)
						.dataFetcher("todosLivros", todosLivrosDataFetcher)
				)
				.build();
	}
	
	public GraphQL getGraphQL() {
		return graphQL;
	}

}
