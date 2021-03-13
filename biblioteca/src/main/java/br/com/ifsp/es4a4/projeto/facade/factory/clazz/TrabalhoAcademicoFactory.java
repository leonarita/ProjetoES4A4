package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.TrabalhoAcademicoController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.TrabalhoAcademicoSpecRepository;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabalhoAcademicoFactory implements BaseFactory {
	
	private final EmprestimoController emprestimoController;
	private final TrabalhoAcademicoController trabalhoAcademicoController;
	private final TrabalhoAcademicoSpecRepository trabalhoAcademicoSpecRepository;
	
	@Override
	public void devolverItem(Long idUser, String codigoCatalogacao) {
		
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
				trabalhoAcademicoController.create(trabalhosAcademicos.get(0));
				
				return;
			}
			
		}

		throw new NotFoundException("Trabalho Acadêmico não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		TrabalhoAcademico trabalhoAcademico = trabalhoAcademicoController.findAById(id);
		trabalhoAcademico.setSituacaoItem(Situacao.DISPONIVEL);
		trabalhoAcademicoController.create(trabalhoAcademico);
	}

}
