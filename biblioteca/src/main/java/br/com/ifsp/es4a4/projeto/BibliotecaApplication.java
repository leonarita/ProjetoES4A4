package br.com.ifsp.es4a4.projeto;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.ifsp.es4a4.projeto.utils.files.StorageFile;
import br.com.ifsp.es4a4.projeto.utils.mail.SmtpAuthenticator;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@EnableScheduling
@EnableCaching
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	
	@PostConstruct
    void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-0300"));
    }
	
	@Bean
	@Qualifier("mailer")
	SmtpAuthenticator mailerAccount(@Value("${biblioteca.mail.username}") String username, @Value("${biblioteca.mail.password}") String password) {
		return new SmtpAuthenticator(username, password);
	}
	
	@Bean
	@Qualifier("files")
	StorageFile storageFile(@Value("${biblioteca.file.dir}") String path) {
		return new StorageFile(path);
	}

}
