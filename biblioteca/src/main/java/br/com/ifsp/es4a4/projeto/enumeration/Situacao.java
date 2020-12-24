package br.com.ifsp.es4a4.projeto.enumeration;

public enum Situacao {
	
	CONSULTA_LOCAL,
	DISPONIVEL,
	EMPRESTADO,
	RESERVADO;
	
	private int id;

	public int getId() {
		return id;
	}
	
	public static Situacao encontrarPorId(int id) {
		if(id == 1) 
			return CONSULTA_LOCAL;
		if(id == 2) 
			return DISPONIVEL;
		if(id == 3) 
			return EMPRESTADO;
		if(id == 4) 
			return RESERVADO;
		return null;
	}

}
