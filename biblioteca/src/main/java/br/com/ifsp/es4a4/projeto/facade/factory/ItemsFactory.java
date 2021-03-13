package br.com.ifsp.es4a4.projeto.facade.factory;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.facade.factory.clazz.LivroFactory;
import br.com.ifsp.es4a4.projeto.facade.factory.clazz.RevistaFactory;
import br.com.ifsp.es4a4.projeto.facade.factory.clazz.TrabalhoAcademicoFactory;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemsFactory {
	
	private final LivroFactory livroDevolucaoFactory;
	private final RevistaFactory revistaDevolucaoFactory;
	private final TrabalhoAcademicoFactory trabalhoAcademicoDevolucaoFactory;
	
	public void realizarDevolucaoItem(Long idUser, String tipoItem, String codigoCatalogacao) {
		
		if(tipoItem.equals("livro")) {
			livroDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
		else if(tipoItem.equals("revista")) {
			revistaDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
		else if(tipoItem.equals("trabalho-academico")) {
			trabalhoAcademicoDevolucaoFactory.devolverItem(idUser, codigoCatalogacao);
		}
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

}
