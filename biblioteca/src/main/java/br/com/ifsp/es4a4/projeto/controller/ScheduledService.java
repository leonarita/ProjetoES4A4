package br.com.ifsp.es4a4.projeto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.SistemaFacade;
import br.com.ifsp.es4a4.projeto.facade.factory.ItemsFactory;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.service.EmprestimoService;
import br.com.ifsp.es4a4.projeto.service.ReservaService;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import br.com.ifsp.es4a4.projeto.utils.mail.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduledService {
	
	private final SistemaFacade sistemaFacade;
	
	private final EmprestimoService emprestimoService;
	private final ReservaService reservaService;
	
	private final ItemsFactory devolutionFactory;
	
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000)
	public void enviarEmailsParaItemsCujaDataDevolucaoEhHoje() {
		
		List<Emprestimo> emprestimos = emprestimoService.findAllByDevolutionDate(new Date());
		
		emprestimos.forEach(emprestimo -> {
			
			if(emprestimo.getFoiDevolvido() == false && emprestimo.getItem().getSituacaoItem().equals(Situacao.EMPRESTADO)) {
				
				sistemaFacade.sendEmail(
						EmailDto.builder()
							.recipientsTO(new ArrayList<>(Arrays.asList(emprestimo.getUsuarioComum().getUsuario().getEmail())))
							.title("BIBLIOTECA IFSP: Devolução de Item marcado para hoje")
							.msgHTML("O item '" + emprestimo.getItem().getTitulo() + "' da biblioteca do IFSP, retirado no dia " + simpleDateFormat.format(emprestimo.getDataRetirada().getTime()) + ", deve ser devolvido hoje!")
							.build()
				);
			}
			
			log.info("SCHEDULE EMPRESTIMO: ID Item: " + emprestimo.getIdItemAcervo() + ", ID Usuário: " + emprestimo.getIdUsuarioComum());
		});
	}
	
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000)
	public void mudarStatusItensReservadosQueNaoForamRetirados() {
		
		List<Reserva> reservas = reservaService.findAllByExpirationDate(DateFormat.subtractDays(new Date(), 1));
		
		reservas.forEach(reserva -> {
			
			if(reserva.getFoiRetirado() == false && reserva.getItem().getSituacaoItem().equals(Situacao.RESERVADO)) 
				devolutionFactory.normalizarItem(reservas.get(0).getItem().getIdItemAcervo(), reservas.get(0).getItem().getTipoItem());

			log.info("SCHEDULE RESERVA   : ID Item: " + reserva.getIdItemAcervo() + ", ID Usuário: " + reserva.getIdUsuarioComum());
		});
	}

}
