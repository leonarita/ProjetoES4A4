package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.controller.AuthenticationController;
import br.com.ifsp.es4a4.projeto.controller.SistemaController;
import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.controller.crud.TrabalhoAcademicoController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoTrabalho;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User History: Realizar operações de reserva e empréstimo de itens")
public class UserHistory_OperacoesBiblioteca_v1 {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private SistemaController sistemaController;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Autowired
	private LivroController livroController;
	
	@Autowired
	private TrabalhoAcademicoController trabalhoAcademicoController;
	
	Map<String, Long> idsValuesInserts = new HashMap<>();
	private String token;
	
	@BeforeAll
	public void setup() {
		
		// JdbcTemplate
		
		idsValuesInserts.put("idLivro", livroController.create(
				Livro.builder()
					.titulo("Homem-Aranha")
					.areaConhecimento("Ação")
					.codigoCatalogacao("EH63HS1I")
					.dataPublicacao(new Date())
					.situacaoItem(Situacao.EMPRESTADO)
					.edicao((short)1)
					.isbn((long)555)
					.idAcervo((long)1)
					.tipoItem(TipoItemAcervo.LIVRO)
					.build()
				).getIdItemAcervo()
		);
		
		idsValuesInserts.put("idTrabalhoAcademico", trabalhoAcademicoController.create(
				TrabalhoAcademico.builder()
					.titulo("Impactos da Tecnologia no Ambiente de Trabalho")
					.subtitulo("Home Office")
					.areaConhecimento("Tecnologia da Informação")
					.codigoCatalogacao("DGCA3HU5K")
					.dataPublicacao(new Date())
					.situacaoItem(Situacao.RESERVADO)
					.idAcervo((long)1)
					.tipoTrabalho(TipoTrabalho.DISSERTACAO)
					.nomeCurso("ADS")
					.dataDefesa(new Date())
					.tipoItem(TipoItemAcervo.TRABALHO_ACADEMICO)
					.build()
				).getIdItemAcervo()
		);
	}
	
	@AfterAll
	public void clear() {
		livroController.deleteById(idsValuesInserts.get("idLivro"));
		trabalhoAcademicoController.deleteById(idsValuesInserts.get("idTrabalhoAcademico"));
	}

	@BeforeEach
	public void authenticate() {
		token = authenticationController.autenticar(new DadosLogin("leo@gmail.com", "123")).getBody().getToken();
	}
	
	@AfterEach
	public void flush() {
		token = null;
	}
	
	@Test
	@DisplayName("Ao emprestar um livro, deverá mudar os status")
	public void emprestarItem() {
		Emprestimo emprestimo = this.sistemaController.emprestarItem(token, "livro", ItemFiltroDto.builder().titulo("Homem-Aranha").build());
		
		assertAll(
				() -> assertFalse(emprestimo.getFoiDevolvido()),
				() -> assertThat(emprestimo.getDataDevolucaoEfetiva()).isAfter(new Date()),
				() -> assertThat(emprestimo.getIdItemAcervo()).isEqualTo((long)3),
				() -> assertThat(emprestimo.getItem().getSituacaoItem()).isIn(new ArrayList<>(Arrays.asList(Situacao.EMPRESTADO)))
		);
	}
	
	@Test
	@DisplayName("Ao reservar um trabalho acadêmico, deverá mudar os status")
	public void reservarItem() {
		Reserva reserva = this.sistemaController.reservarItem(token, "trabalho-academico", ItemFiltroDto.builder().subtitulo("Home Office").build());

		assertAll(
				() -> assertFalse(reserva.getFoiRetirado()),
				() -> assertThat(reserva.getDataExpiracao()).isAfter(new Date()),
				() -> assertThat(reserva.getIdItemAcervo()).isEqualTo((long)3),
				() -> assertThat(reserva.getItem().getSituacaoItem()).isIn(new ArrayList<>(Arrays.asList(Situacao.RESERVADO)))
		);
	}

}
