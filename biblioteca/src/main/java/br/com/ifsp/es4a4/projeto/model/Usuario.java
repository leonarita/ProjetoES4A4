package br.com.ifsp.es4a4.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_usuario")
public class Usuario {
	
	@Column(name = "st_login", nullable = false)
	protected String login;
	
	@Column(name = "st_email", nullable = false)
	protected String email;
	
	@Column(name = "st_senha", nullable = false)
	protected String senha;
	
	@Id
	@Column(name = "id_pessoa", nullable = false, updatable = false)
	protected Long idUsuario;
	
	@Transient
	private String token;

}
