package br.com.ifsp.es4a4.projeto.model;

public class Book {
		
	private String titulo;
			
	private Long isbn;
	
	private Situacao situacaoItem;
	
	public Book() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Situacao getSituacaoItem() {
		return situacaoItem;
	}

	public void setSituacaoItem(Situacao situacaoItem) {
		this.situacaoItem = situacaoItem;
	}	

}