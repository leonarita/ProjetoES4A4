package br.com.ifsp.es4a4.projeto.utils.jwt;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAutheticatedDTO {

    private String tipo;
    private String email;
    private String nome;
    private String token;
    
    public static UserAutheticatedDTO toDTO(Usuario user, String tipo) {
        return new UserAutheticatedDTO("Bearer", user.getEmail(), user.getLogin(), tipo + user.getToken());
    }
}
