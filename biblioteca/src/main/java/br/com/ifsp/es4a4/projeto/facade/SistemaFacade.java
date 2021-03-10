package br.com.ifsp.es4a4.projeto.facade;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.repository.spec.RevistaSpecRepository;
import br.com.ifsp.es4a4.projeto.repository.spec.TrabalhoAcademicoSpecRepository;
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
	
	private final SenderEmail senderEmail;
	private final UserSecurityService userSecurityService;
	
	private final LivroSpecRepository livroSpecRepository;
	private final RevistaSpecRepository revistaSpecRepository;
	private final TrabalhoAcademicoSpecRepository trabalhoAcademicoSpecRepository;
	
	public void emprestarItem(String Authorization) {
		
	}
	
	public List<Livro> pegarLivrosDisponiveis(ItemFiltroDto filtro) {
		return livroSpecRepository.findBooks(filtro);
	}
	
	public List<Revista> pegarRevistasDisponiveis(ItemFiltroDto filtro) {
		return revistaSpecRepository.findMaganizes(filtro);
	}
	
	public List<TrabalhoAcademico> pegarTrabalhosAcademicoDisponiveis(ItemFiltroDto filtro) {
		return trabalhoAcademicoSpecRepository.findWorks(filtro);
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
