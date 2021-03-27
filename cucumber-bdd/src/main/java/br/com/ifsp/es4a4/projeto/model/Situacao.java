package br.com.ifsp.es4a4.projeto.model;

public enum Situacao {
	
	CONSULTA_LOCAL(1),
	DISPONIVEL(2),
	EMPRESTADO(3),
	RESERVADO(4);
	
	private int id;

	Situacao(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public static Situacao findSituacaoById(int id) {
		for(Situacao situacao : Situacao.values()) {
			if(situacao.getId() == id) {
				return situacao;
			}
		}
		return null;
	}

}