package br.com.ifsp.es4a4.projeto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_instituicao_editora")
public class InstituicaoEditora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_instituicao_editora")
	@SequenceGenerator(name = "sq_instituicao_editora", sequenceName = "sq_instituicao_editora", allocationSize = 1)
	@Column(name = "id_instituicao_editora")
	private Long id;
	
	@Column(name = "st_cnpj", nullable = false)
	private String cnpj;
	
	@Column(name = "st_cidade")
	private String cidade;
	
	@Column(name = "st_nome", nullable = false)
	private String nome;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "tb_editoras_items", joinColumns = { 
//			@JoinColumn(name = "id", referencedColumnName = "id_item_acervo") 
//	}, inverseJoinColumns = { 
//			@JoinColumn(name = "idItemAcervo", referencedColumnName = "id_instituicao_editora") 
//	})
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instituicao_editora")
	private List<EditoraItem> editoras;
	
	
	

}
