package br.com.ifsp.es4a4.projeto.utils.jwt;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class UserRegistrationDTO extends Usuario {
	
    private Long id;

    private String nome;
    private String email;
    private String senha;
    
    public Usuario toUser() {
        return new Usuario(getNome(), getEmail(), getSenha(), getId(), null);
    }

}
