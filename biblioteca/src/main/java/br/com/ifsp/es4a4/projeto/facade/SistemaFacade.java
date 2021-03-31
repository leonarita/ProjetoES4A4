package br.com.ifsp.es4a4.projeto.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.factory.ItemsFactory;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.LivroSpecRepository;
import br.com.ifsp.es4a4.projeto.repository.spec.RevistaSpecRepository;
import br.com.ifsp.es4a4.projeto.repository.spec.TrabalhoAcademicoSpecRepository;
import br.com.ifsp.es4a4.projeto.service.UsuarioComumService;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidBodyEmailException;
import br.com.ifsp.es4a4.projeto.utils.exceptions.InvalidRecipientException;
import br.com.ifsp.es4a4.projeto.utils.jwt.service.UserSecurityService;
import br.com.ifsp.es4a4.projeto.utils.mail.EmailDto;
import br.com.ifsp.es4a4.projeto.utils.mail.SenderEmail;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SistemaFacade {
		
	private final SenderEmail senderEmail;
	private final UserSecurityService userSecurityService;
	private final ItemsFactory devolutionFactory;

	private final LivroSpecRepository livroSpecRepository;
	private final RevistaSpecRepository revistaSpecRepository;
	private final TrabalhoAcademicoSpecRepository trabalhoAcademicoSpecRepository;
	private final UsuarioComumService usuarioComumService;
	
	private final SimpleDateFormat FORMAT_TIMESTAMP_PT_BR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private final SimpleDateFormat FORMAT_DATE_PT_BR = new SimpleDateFormat("dd/MM/yyyy");
		
	public Emprestimo emprestarItem(String Authorization, String tipoItem, ItemFiltroDto filtro) {
		
		Long idUser = userSecurityService.getIdByToken(Authorization);
		Emprestimo emprestimo = devolutionFactory.emprestarItem(idUser, tipoItem, filtro);
		
		if(Objects.nonNull(emprestimo)) {
			emprestimo.setUsuarioComum(usuarioComumService.findById(idUser));

			sendEmail(
				EmailDto.builder()
					.recipientsTO(new ArrayList<>(Arrays.asList(emprestimo.getUsuarioComum().getUsuario().getEmail())))
					.title("BIBLIOTECA IFSP: Empréstimo de item")
					.msgHTML("O item '" + emprestimo.getItem().getTitulo() + "' da biblioteca do IFSP foi retirado hoje (" + FORMAT_TIMESTAMP_PT_BR.format(emprestimo.getDataRetirada().getTime()) + ") e deve ser devolvido no dia " + FORMAT_DATE_PT_BR.format(emprestimo.getDataDevolucaoEfetiva()) + "!")
					.build()
			);
		}
		
		return emprestimo;
	}
	
	public Reserva reservarItem(String Authorization, String tipoItem, ItemFiltroDto filtro) {

		Long idUser = userSecurityService.getIdByToken(Authorization);
		Reserva reserva = devolutionFactory.reservarItem(idUser, tipoItem, filtro);
		
		if(Objects.nonNull(reserva)) {
			reserva.setUsuarioComum(usuarioComumService.findById(idUser));

			sendEmail(
					EmailDto.builder()
						.recipientsTO(new ArrayList<>(Arrays.asList(reserva.getUsuarioComum().getUsuario().getEmail())))
						.title("BIBLIOTECA IFSP: Reserva de item")
						.msgHTML("O item '" + reserva.getItem().getTitulo() + "' da biblioteca do IFSP foi reservado hoje (" + FORMAT_TIMESTAMP_PT_BR.format(reserva.getDataReserva().getTime()) + ") para ser retirado até dia " + FORMAT_DATE_PT_BR.format(reserva.getDataExpiracao()) + "!")
						.build()
				);
		}
		
		return reserva;
	}
	
	public Emprestimo pegarEmprestadoItemReservado(String authorization, String tipoItem, String name) {
		return devolutionFactory.emprestarItemReservado(userSecurityService.getIdByToken(authorization), name, tipoItem);
	}
	
	public ItemAcervo devolverItemEmprestado(String authorization, String tipoItem, String codigoCatalogacao) {
		return devolutionFactory.realizarDevolucaoItem(userSecurityService.getIdByToken(authorization), tipoItem.toLowerCase(), codigoCatalogacao);
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
