package br.com.ifsp.es4a4.projeto.model;

import java.util.Date;

public class Book {
	
	private Long idItemAcervo;
	
	private String titulo;
	
	private String subtitulo;
	
	private String areaConhecimento;
	
	private String codigoCatalogacao;
	
	private Date dataPublicacao;
	
	private Long idAcervo;
	
	private Short edicao;
	
	private Long isbn;
	
	private Situacao situacaoItem;
	
	public Book() {
	}

	public Long getIdItemAcervo() {
		return idItemAcervo;
	}

	public void setIdItemAcervo(Long idItemAcervo) {
		this.idItemAcervo = idItemAcervo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getCodigoCatalogacao() {
		return codigoCatalogacao;
	}

	public void setCodigoCatalogacao(String codigoCatalogacao) {
		this.codigoCatalogacao = codigoCatalogacao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Long getIdAcervo() {
		return idAcervo;
	}

	public void setIdAcervo(Long idAcervo) {
		this.idAcervo = idAcervo;
	}

	public Short getEdicao() {
		return edicao;
	}

	public void setEdicao(Short edicao) {
		this.edicao = edicao;
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
