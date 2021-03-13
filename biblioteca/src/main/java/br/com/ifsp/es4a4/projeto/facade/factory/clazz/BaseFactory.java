package br.com.ifsp.es4a4.projeto.facade.factory.clazz;

public interface BaseFactory {
	
	void devolverItem(Long idUser, String codigoCatalogacao);
	void normalizarItem(Long id);
}
