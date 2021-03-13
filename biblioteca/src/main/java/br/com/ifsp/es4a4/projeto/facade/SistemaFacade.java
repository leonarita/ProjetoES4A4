package br.com.ifsp.es4a4.projeto.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.factory.ItemsFactory;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
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
	
	private final ItemsFactory devolutionFactory;
	
	public void emprestarItem(String Authorization, String tipoItem, ItemFiltroDto filtro) {

	}
	
	public void reservarItem(String Authorization, String tipoItem, ItemFiltroDto filtro) {
		
	}
	
	public void devolverItemEmprestado(String authorization, String tipoItem, String codigoCatalogacao) {
		devolutionFactory.realizarDevolucaoItem(userSecurityService.getIdByToken(authorization), tipoItem.toLowerCase(), codigoCatalogacao);
	}
	
	public List<Livro> pegarLivrosDisponiveis(ItemFiltroDto filtro) {
		return livroSpecRepository.findBooks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL)));
	}
	
	public List<Revista> pegarRevistasDisponiveis(ItemFiltroDto filtro) {
		return revistaSpecRepository.findMaganizes(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL)));
	}
	
	public List<TrabalhoAcademico> pegarTrabalhosAcademicoDisponiveis(ItemFiltroDto filtro) {
		return trabalhoAcademicoSpecRepository.findWorks(filtro, new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL)));
	}

	public boolean sendEmail(EmailDto email) {
		
		if(Objects.isNull(email.getRecipientsTO()) || email.getRecipientsTO().isEmpty()) {
			throw new InvalidRecipientException();
		}
		
		if(email.validateBodyEmail()) {
			throw new InvalidBodyEmailException();
		}
		
		return this.senderEmail.sendEmail(email);
	}

}
