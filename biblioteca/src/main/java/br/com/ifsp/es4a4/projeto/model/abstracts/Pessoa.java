package br.com.ifsp.es4a4.projeto.model.abstracts;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifsp.es4a4.projeto.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pessoa")
	@SequenceGenerator(name = "sq_pessoa", sequenceName = "sq_pessoa", allocationSize = 1)
	@Column(name = "id_pessoa")
	protected Long idPessoa;
	
	@Column(name = "st_cpf", nullable = false)
	protected Long cpf;
	
	@Column(name = "st_nome", nullable = false)
	protected String nome;
	
	@Column(name = "st_sobrenome")
	protected String sobrenome;
	
	@Column(name = "dt_data_nascimento")
	@Temporal(TemporalType.DATE)
	protected Date nascimento;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", nullable = false, insertable = false, updatable = false)
	protected Usuario usuario;


}
