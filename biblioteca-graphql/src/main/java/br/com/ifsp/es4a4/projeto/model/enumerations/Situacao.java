package br.com.ifsp.es4a4.projeto.model.enumerations;

import br.com.ifsp.es4a4.projeto.utils.enumerations.AbstractEnumConverter;
import br.com.ifsp.es4a4.projeto.utils.enumerations.EnumID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Situacao implements EnumID {
	
	CONSULTA_LOCAL(1),
	DISPONIVEL(2),
	EMPRESTADO(3),
	RESERVADO(4);
	
	private int id;

	public static class Converter extends AbstractEnumConverter<Situacao> {
        public Converter() {
            super(Situacao.values());
        }
    }

}
