package br.com.ifsp.es4a4.projeto;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.ifsp.es4a4.projeto.utils.mail.SmtpAuthenticator;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
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

}
