package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;

public interface BaseFactory {
	
	ItemAcervo devolverItem(Long idUser, String codigoCatalogacao);
	
	void normalizarItem(Long id);
	
	public Emprestimo emprestarItemReservado(Long idUser, String name);
	
	Emprestimo emprestarItem(Long idUser, ItemFiltroDto filtro);
	
	Reserva reservarItem(Long idUser, ItemFiltroDto filtro);
}
