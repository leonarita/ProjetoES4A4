package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.ReservaController;
import br.com.ifsp.es4a4.projeto.controller.crud.TrabalhoAcademicoController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.TrabalhoAcademicoSpecRepository;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabalhoAcademicoFactory implements BaseFactory {
	
	private final EmprestimoController emprestimoController;
	private final ReservaController reservaController;
	private final TrabalhoAcademicoController trabalhoAcademicoController;
	private final TrabalhoAcademicoSpecRepository trabalhoAcademicoSpecRepository;
	
	@Override
	public ItemAcervo devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<TrabalhoAcademico> trabalhosAcademicos = trabalhoAcademicoSpecRepository.findWorks(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(trabalhosAcademicos) && trabalhosAcademicos.size() == 1) {
			
			Emprestimo emprestimo = emprestimoController.findByIdsAndWasntReturned(
					idUser, 
					trabalhosAcademicos.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoController.create(emprestimo);
				
				trabalhosAcademicos.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				return trabalhoAcademicoController.create(trabalhosAcademicos.get(0));
			}
			
			throw new NotFoundException("Empréstimo não encontrado com parâmetros informados");
		}

		throw new NotFoundException("Trabalho Acadêmico não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		TrabalhoAcademico trabalhoAcademico = trabalhoAcademicoController.findAById(id);
		trabalhoAcademico.setSituacaoItem(Situacao.DISPONIVEL);
		trabalhoAcademicoController.create(trabalhoAcademico);
	}
	
	@Override
	public Emprestimo emprestarItemReservado(Long idUser, String name) {
		
		Reserva reserva = reservaController.findByIdUserAndNameItem(idUser, name);
		
		if(Objects.nonNull(reserva)) {
			reserva.setFoiRetirado(true);
			reservaController.create(reserva);
			
			TrabalhoAcademico trabalhoAcademico = trabalhoAcademicoController.findAById(reserva.getIdItemAcervo());
			trabalhoAcademico.setSituacaoItem(Situacao.EMPRESTADO);
			trabalhoAcademicoController.create(trabalhoAcademico);
			
			return emprestimoController.create(
					Emprestimo.builder()
						.idItemAcervo(reserva.getIdItemAcervo())
						.idUsuarioComum(idUser)
						.dataRetirada(Calendar.getInstance())
						.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
						.foiDevolvido(false)
						.build()
			);
		}
		
		throw new NotFoundException("Reserva não encontrada com parâmetros informados");
	}

}
