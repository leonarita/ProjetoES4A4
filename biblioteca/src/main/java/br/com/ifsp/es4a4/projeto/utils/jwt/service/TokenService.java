package br.com.ifsp.es4a4.projeto.utils.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Usuario;

import java.util.Date;

@Service
public class TokenService {
	
	private String key = "String Aleatoria Secret";
	
	// 30 minutos
	private static final long expirationTime = 1800000;
	
	public String generateToken(Usuario user) {
		
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("Teste JWT API")
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}
	
	public Claims decodeToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

}
