package br.com.ifsp.es4a4.projeto.facade.factory;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.facade.factory.clazz.LivroFactory;
import br.com.ifsp.es4a4.projeto.facade.factory.clazz.RevistaFactory;
import br.com.ifsp.es4a4.projeto.facade.factory.clazz.TrabalhoAcademicoFactory;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemsFactory {
	
	private final LivroFactory livroDevolucaoFactory;
	private final RevistaFactory revistaDevolucaoFactory;
	private final TrabalhoAcademicoFactory trabalhoAcademicoDevolucaoFactory;
	
	public ItemAcervo realizarDevolucaoItem(Long idUser, String tipoItem, String codigoCatalogacao) {
		
		if(tipoItem.equals("livro")) {
			return livroDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
		else if(tipoItem.equals("revista")) {
			return revistaDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
		else if(tipoItem.equals("trabalho-academico")) {
			return trabalhoAcademicoDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
		
		return null;
	}
	
	public void normalizarItem(Long id, TipoItemAcervo tipoItem) {
		
		if(tipoItem.equals(TipoItemAcervo.LIVRO)) {
			livroDevolucaoFactory.normalizarItem(id);
		}
		else if(tipoItem.equals(TipoItemAcervo.REVISTA)) {
			revistaDevolucaoFactory.normalizarItem(id);
		}
		else if(tipoItem.equals(TipoItemAcervo.TRABALHO_ACADEMICO)) {
			trabalhoAcademicoDevolucaoFactory.normalizarItem(id);
		}
	}
	
	public Emprestimo emprestarItemReservado(Long idUser, String name, String tipoItem) {
		if(tipoItem.equals("livro")) {
			return livroDevolucaoFactory.emprestarItemReservado(idUser, name);
		}
		else if(tipoItem.equals("revista")) {
			return revistaDevolucaoFactory.emprestarItemReservado(idUser, name);
		}
		else if(tipoItem.equals("trabalho-academico")) {
			return trabalhoAcademicoDevolucaoFactory.emprestarItemReservado(idUser, name);
		}
		
		return null;
	}
	
	public Emprestimo emprestarItem(Long idUser, String tipoItem, ItemFiltroDto filtro) {
		if(tipoItem.equals("livro")) {
			return livroDevolucaoFactory.emprestarItem(idUser, filtro);
		}
		else if(tipoItem.equals("revista")) {
			return revistaDevolucaoFactory.emprestarItem(idUser, filtro);
		}
		else if(tipoItem.equals("trabalho-academico")) {
			return trabalhoAcademicoDevolucaoFactory.emprestarItem(idUser, filtro);
		}
		
		return null;
	}
	
	public Reserva reservarItem(Long idUser, String tipoItem, ItemFiltroDto filtro) {
		if(tipoItem.equals("livro")) {
			return livroDevolucaoFactory.reservarItem(idUser, filtro);
		}
		else if(tipoItem.equals("revista")) {
			return revistaDevolucaoFactory.reservarItem(idUser, filtro);
		}
		else if(tipoItem.equals("trabalho-academico")) {
			return trabalhoAcademicoDevolucaoFactory.reservarItem(idUser, filtro);
		}
		
		return null;
	}

}
