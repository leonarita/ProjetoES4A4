package br.com.ifsp.es4a4.projeto;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.ifsp.es4a4.projeto.utils.files.StorageFile;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class BibliotecaGraphqlApplication {
	
	// http://localhost:8081/playground
	// http://localhost:8081/graphiql

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaGraphqlApplication.class, args);
	}
	
	@PostConstruct
    void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-0300"));
    }
	
	@Bean
	@Qualifier("files")
	StorageFile storageFile(@Value("${biblioteca.file.dir}") String path) {
		return new StorageFile(path);
	}

}
