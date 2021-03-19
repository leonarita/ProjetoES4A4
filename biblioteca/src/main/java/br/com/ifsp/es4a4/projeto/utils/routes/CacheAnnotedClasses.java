package br.com.ifsp.es4a4.projeto.utils.routes;

import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class CacheAnnotedClasses {
	
	List<String> classes = new ArrayList<String>();
	
	public CacheAnnotedClasses() {
		
		Reflections ref = new Reflections("br.com.ifsp.es4a4.projeto.controller");
		
		for (Class<?> cl : ref.getTypesAnnotatedWith(AllowAnnonymous.class)) {
			classes.add(cl.getCanonicalName());
		}
		
		classes.add("br.com.ifsp.es4a4.projeto.graphql.GraphQLController");
		classes.add("com.oembedler.moon.graphiql.boot.GraphiQLController");
		classes.add("graphql.kickstart.playground.boot.PlaygroundController");
	}
	
	public boolean classHasAnonymousAnnotation(String clazz) {
		if(classes.contains(clazz)) {
			return true;
		}
		return false;
	}

}
