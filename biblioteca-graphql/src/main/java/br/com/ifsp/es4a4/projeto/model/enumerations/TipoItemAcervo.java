package br.com.ifsp.es4a4.projeto.model.enumerations;

import br.com.ifsp.es4a4.projeto.utils.enumerations.AbstractEnumConverter;
import br.com.ifsp.es4a4.projeto.utils.enumerations.EnumID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoItemAcervo implements EnumID {
	
	LIVRO(1),
	REVISTA(2),
	TRABALHO_ACADEMICO(3);
	
	private int id;

	public static class Converter extends AbstractEnumConverter<TipoItemAcervo> {
        public Converter() {
            super(TipoItemAcervo.values());
        }
    }

}
