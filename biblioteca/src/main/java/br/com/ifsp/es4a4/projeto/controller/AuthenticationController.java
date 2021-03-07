package br.com.ifsp.es4a4.projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserAutheticatedDTO;
import br.com.ifsp.es4a4.projeto.utils.jwt.UserRegistrationDTO;
import br.com.ifsp.es4a4.projeto.utils.jwt.service.UserSecurityService;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
	private final UserSecurityService userSecurityService;
	
	@AllowAnnonymous
	@PostMapping("/registration")
	public ResponseEntity<UserAutheticatedDTO> registrate(@RequestBody UserRegistrationDTO userRegistrationDTO){
		Usuario user = userSecurityService.registrate(userRegistrationDTO.toUser());
		return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
	}
	
	@AllowAnnonymous
	@PostMapping("/login")
	public ResponseEntity<UserAutheticatedDTO> autenticar(@RequestBody DadosLogin data){
		Usuario user = userSecurityService.authenticate(data);
		return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/decode")
	public String decodeToken(@RequestHeader String Authorization) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this.userSecurityService.decodeToken(Authorization));
	}

}
