package br.com.ifsp.es4a4.projeto;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	
	@PostConstruct
    void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-0300"));
    }

}
