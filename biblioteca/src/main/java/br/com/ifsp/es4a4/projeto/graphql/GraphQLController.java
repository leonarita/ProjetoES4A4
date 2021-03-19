package br.com.ifsp.es4a4.projeto.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import graphql.ExecutionResult;

@RequestMapping("/graphql")
@RestController
@AllowAnnonymous
public class GraphQLController {

	// http://localhost:8080/playground
	// http://localhost:8080/graphiql
	
	@Autowired
	private GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> livro(@RequestBody String query) {
		System.out.println("Hello " + query);
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	

}
