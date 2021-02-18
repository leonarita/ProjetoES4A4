package br.com.ifsp.es4a4.projeto.utils.jwt.service;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import br.com.ifsp.es4a4.projeto.service.UsuarioService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.ExistingEmailException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.ExpiredTokenException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidLoginException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidTokenException;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService {
	
	private final UsuarioService usuarioService;
	private final TokenService tokenService;
	
	public Usuario registrate(Usuario user){
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		user.setSenha(encoder.encode(user.getSenha()));
		
		String token = tokenService.generateToken(user);
		user.setToken(tokenService.generateToken(user));
		
		Usuario newUser = usuarioService.save(user);
		newUser.setToken(token);
		return newUser;
	}
	
	public Usuario authenticate(DadosLogin dados) {
		
		Usuario user = usuarioService.findByEmail(dados.getEmail()).orElseThrow(ExistingEmailException::new);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(encoder.matches(dados.getSenha(), user.getSenha())) {
			user.setToken(tokenService.generateToken(user));
			return user;
		}
		
		throw new InvalidLoginException();
	}
	
	public boolean validate(String token) {
		if(token.isEmpty())
			throw new InvalidTokenException();
		
		try {
			String tokenTratado = token.replace("Bearer ", "");
			Claims claims = tokenService.decodeToken(tokenTratado);
			
			if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) 
				throw new ExpiredTokenException();
			
			return true;
		}
		catch (ExpiredTokenException et) {
			et.printStackTrace();
			throw et;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new InvalidTokenException();
		}
	}

	public Claims decodeToken(String token) {
		return this.tokenService.decodeToken(token.replace("Bearer ", ""));
	}

}
