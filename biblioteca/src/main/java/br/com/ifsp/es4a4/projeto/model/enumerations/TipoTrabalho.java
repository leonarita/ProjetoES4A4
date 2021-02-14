package br.com.ifsp.es4a4.projeto.model.enumerations;

import br.com.ifsp.es4a4.projeto.utils.enumerations.AbstractEnumConverter;
import br.com.ifsp.es4a4.projeto.utils.enumerations.EnumID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoTrabalho implements EnumID {
	
	DISSERTACAO(1),
	MONOGRAFIA(2),
	TESE(3);
	
	private int id;
	
	public static class Converter extends AbstractEnumConverter<TipoTrabalho> {
        public Converter() {
            super(TipoTrabalho.values());
        }
    }

}
