package br.com.ifsp.es4a4.projeto.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosLogin {

    private String email;
    private String senha;
}
