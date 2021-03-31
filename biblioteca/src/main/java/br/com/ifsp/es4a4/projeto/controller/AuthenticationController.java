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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	private final UserSecurityService userSecurityService;
	
	@AllowAnnonymous
	@PostMapping("/registration")
	public ResponseEntity<UserAutheticatedDTO> registrate(@RequestBody UserRegistrationDTO userRegistrationDTO){
		Usuario user = userSecurityService.registrate(userRegistrationDTO.toUser());
		return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
	}
	
	@AllowAnnonymous
	@PostMapping("/login")
	public ResponseEntity<UserAutheticatedDTO> autenticar(@RequestBody DadosLogin data) {
		logger.info("Tentativa de login...");

		Usuario user = userSecurityService.authenticate(data);
		logger.info("Login realizado com sucesso");
		return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/decode")
	public String decodeToken(@RequestHeader String Authorization) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this.userSecurityService.decodeToken(Authorization));
	}

}
