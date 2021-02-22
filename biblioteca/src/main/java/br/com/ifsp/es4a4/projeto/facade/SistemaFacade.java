package br.com.ifsp.es4a4.projeto.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidBodyEmailException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidRecipientException;
import br.com.ifsp.es4a4.projeto.utils.jwt.service.UserSecurityService;
import br.com.ifsp.es4a4.projeto.utils.mail.EmailDto;
import br.com.ifsp.es4a4.projeto.utils.mail.SenderEmail;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SistemaFacade {
	
	// PATTERN -> AGRUPA REGRAS DE NEGÓCIOS COMPLEXAS
	
	private final EmprestimoService emprestimoService;
	private final SenderEmail senderEmail;
	private final UserSecurityService userSecurityService;
	
	public void emprestarItem(String Authorization) {
		
		// Pegar o usuário
		Long idUsuario = this.userSecurityService.getIdByToken(Authorization);
		
		// Pegar o item
		long item = 2l;
		
		// Salvar no BD
		this.emprestimoService.create(
				Emprestimo.builder().dataDevolucaoEfetiva(new Date()).dataRetirada(new Date()).idItemAcervo(item).idUsuarioComum(idUsuario).build()
		);
		
		// Enviar o email do item pegado
		sendEmail(
				EmailDto.builder()
					.recipientsTO(new ArrayList<>(Arrays.asList("natan.apk@gmail.com")))
					.title("AVISO ITEM BIBLIOTECA")
					.msgHTML("DEU CERTO, DEVOLVA O ITEM LOGO")
					.build()
		);
		
	}

	private boolean sendEmail(EmailDto email) {
		
		if(Objects.isNull(email.getRecipientsTO()) || email.getRecipientsTO().isEmpty()) {
			throw new InvalidRecipientException();
		}
		
		if(email.validateBodyEmail()) {
			throw new InvalidBodyEmailException();
		}
		
		return this.senderEmail.sendEmail(email);
	}

}
