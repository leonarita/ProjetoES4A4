package br.com.ifsp.es4a4.projeto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurity {
	
	private final PasswordEncoder passwordEncoder;
	
	public WebSecurity() {
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}

}
