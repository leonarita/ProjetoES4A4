package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Calendar;
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
import br.com.ifsp.es4a4.projeto.controller.crud.EmprestimoController;
import br.com.ifsp.es4a4.projeto.controller.crud.LivroController;
import br.com.ifsp.es4a4.projeto.controller.crud.ReservaController;
import br.com.ifsp.es4a4.projeto.controller.crud.TrabalhoAcademicoController;
import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.Reserva;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.abstracts.ItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoItemAcervo;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoTrabalho;
import br.com.ifsp.es4a4.projeto.model.pk.EmprestimoId;
import br.com.ifsp.es4a4.projeto.model.pk.ReservaId;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;
import br.com.ifsp.es4a4.projeto.utils.jwt.DadosLogin;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User History: Realizar operações de emprestimo e devolução de itens")
public class UserHistory_OperacoesBiblioteca_v2 {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private SistemaController sistemaController;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Autowired
	private LivroController livroController;
	
	@Autowired
	private TrabalhoAcademicoController trabalhoAcademicoController;
	
	@Autowired
	private EmprestimoController emprestimoController;
	
	@Autowired
	private ReservaController reservaController;
	
	Map<String, Long> idsValuesInserts = new HashMap<>();
	private final Calendar cal = Calendar.getInstance();
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
		
		emprestimoController.create(
				Emprestimo.builder()
					.idItemAcervo((Long)idsValuesInserts.get("idLivro"))
					.idUsuarioComum((long)3)
					.dataRetirada(cal)
					.dataDevolucaoEfetiva(DateFormat.addDays(new Date(), 7))
					.foiDevolvido(false)
					.build()
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
		
		reservaController.create(
					Reserva.builder()
					.idItemAcervo((Long)idsValuesInserts.get("idTrabalhoAcademico"))
					.idUsuarioComum((long)3)
					.dataReserva(cal)
					.dataExpiracao(DateFormat.addDays(new Date(), 7))
					.foiRetirado(false)
					.build()
		);
	}
	
	@AfterAll
	public void clear() {
		emprestimoController.deleteById(
				EmprestimoId.builder()
					.dataRetirada(cal)
					.idItemAcervo(idsValuesInserts.get("idLivro"))
					.idUsuarioComum((long)3)
					.build()
		);
		
		reservaController.deleteById(
				ReservaId.builder()
					.dataReserva(cal)
					.idItemAcervo(idsValuesInserts.get("idTrabalhoAcademico"))
					.idUsuarioComum((long)3)
					.build()
		);
		
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
	@DisplayName("Devolução de livro corretamente")
	public void devolverLivroEmprestado() {
		ItemAcervo item = sistemaController.devolverItemEmprestado(token, "livro", "EH63HS1I");
		assertThat(item.getSituacaoItem()).isEqualByComparingTo(Situacao.DISPONIVEL);
	}
	
	@Test
	@DisplayName("Empréstimo de livro reservado corretamente")
	public void pegarEmprestadoItemReservado() {
		Emprestimo emprestimo = sistemaController.pegarEmprestadoItemReservado(token, "trabalho-academico", "Tecnologia no Ambiente de Trabalho");
		
		assertAll(
				() -> assertThat(emprestimo.getDataDevolucaoEfetiva()).isAfter(new Date()),
				() -> assertFalse(emprestimo.getFoiDevolvido())
		);
	}

}
