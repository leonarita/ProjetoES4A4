package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.RevistaController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.repository.spec.RevistaSpecRepository;
import br.com.ifsp.es4a4.projeto.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevistaFactory implements BaseFactory {
	
	private final EmprestimoController emprestimoController;
	private final RevistaController revistaController;
	private final RevistaSpecRepository revistaSpecRepository;

	@Override
	public void devolverItem(Long idUser, String codigoCatalogacao) {
		
		List<Revista> revistas = revistaSpecRepository.findMaganizes(
				ItemFiltroDto.builder().codigoCatalogacao(codigoCatalogacao).build(), 
				new ArrayList<Situacao>(Arrays.asList(Situacao.EMPRESTADO))
		);
		
		if(Objects.nonNull(revistas) && revistas.size() == 1) {
			
			Emprestimo emprestimo = emprestimoController.findByIdsAndWasntReturned(
					idUser, 
					revistas.get(0).getIdItemAcervo()
			);
			
			if(Objects.nonNull(emprestimo)) {
				
				emprestimo.setFoiDevolvido(true);
				emprestimoController.create(emprestimo);
				
				revistas.get(0).setSituacaoItem(Situacao.DISPONIVEL);
				revistaController.create(revistas.get(0));
				
				return;
			}
			
		}

		throw new NotFoundException("Revista não encontrado com parâmetros informados");
	}
	
	@Override
	public void normalizarItem(Long id) {
		Revista revista = revistaController.findAById(id);
		revista.setSituacaoItem(Situacao.DISPONIVEL);
		revistaController.create(revista);
	}

}
