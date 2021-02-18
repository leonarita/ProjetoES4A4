package br.com.ifsp.es4a4.projeto.facade;

import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidBodyEmailException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidRecipientException;
import br.com.ifsp.es4a4.projeto.utils.mail.EmailDto;
import br.com.ifsp.es4a4.projeto.utils.mail.SenderEmail;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SistemaFacade {
	
	private final SenderEmail senderEmail;

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
