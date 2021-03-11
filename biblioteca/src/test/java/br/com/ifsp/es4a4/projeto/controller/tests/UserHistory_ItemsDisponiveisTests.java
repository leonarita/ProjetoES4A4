package br.com.ifsp.es4a4.projeto.controller.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ifsp.es4a4.projeto.controller.SistemaController;
import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.enumerations.TipoTrabalho;

@SpringBootTest
public class UserHistory_ItemsDisponiveisTests {
	
	// TESTES BASEADOS NOS DADOS DO SCRIPT FLYWAY Nº 4
	
	@Autowired
	private SistemaController sistemaController;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	// LIVROS
	
	@Test
	void testarLivrosDisponiveis() {
		assertThat(sistemaController.pegarLivrosDisponiveis(new ItemFiltroDto()).size()).isEqualTo(4);
	}
	
	@Test
	void testarLivrosDisponiveisComParametros1() {
		assertThat(sistemaController.pegarLivrosDisponiveis(
				ItemFiltroDto.builder()
					.titulo("arry ")
					.areaConhecimento(new ArrayList<String>(Arrays.asList("Aventura")))
					.build()
		).size()).isEqualTo(1);
	}

	
	@Test
	void testarLivrosDisponiveisComParametros2() {
		assertThat(sistemaController.pegarLivrosDisponiveis(
					ItemFiltroDto.builder()
					.titulo("arry ")
					.areaConhecimento(new ArrayList<String>(Arrays.asList("Aventura")))
					.edicao((short)2)
					.isbn((long)222)
					.build()
		).get(0).getTitulo()).isEqualToIgnoringCase("harry potter");
	}
	
	
	// REVISTAS
	
	@Test
	void testarRevistasDisponiveis() {
		assertThat(sistemaController.pegarRevistasDisponiveis(new ItemFiltroDto()).size()).isEqualTo(2);
	}
	
	@Test
	void testarRevistasDisponiveisComParametros1() {
		assertThat(sistemaController.pegarRevistasDisponiveis(
				ItemFiltroDto.builder()
					.titulo("Culinária:")
					.areaConhecimento(new ArrayList<String>(Arrays.asList("Culinária")))
					.build()
		).size()).isEqualTo(2);
	}

	@Test
	void testarRevistasDisponiveisComParametros2() {
		assertThat(sistemaController.pegarRevistasDisponiveis(
					ItemFiltroDto.builder()
						.titulo("Pressão")
						.areaConhecimento(new ArrayList<String>(Arrays.asList("Culinária")))
						.build()
		).get(0).getCodigoCatalogacao()).isEqualTo("HAH359EJ");
	}
	
	
	// TRABALHOS ACADÊMICOS
	
	@Test
	void testarTrabalhosAcademicosDisponiveis() {
		assertThat(sistemaController.pegarTrabalhosAcademicosDisponiveis(new ItemFiltroDto()).size()).isEqualTo(1);
	}
	
	@Test
	void testarTrabalhosAcademicosDisponiveisComParametros1() {
		assertThat(sistemaController.pegarTrabalhosAcademicosDisponiveis(
				ItemFiltroDto.builder()
					.idAcervo((long)3)
					.build()
		).get(0).getTipoTrabalho()).isNotIn(new ArrayList<TipoTrabalho>(Arrays.asList(TipoTrabalho.MONOGRAFIA, TipoTrabalho.TESE)));
	}

	@Test
	void testarTrabalhosAcademicosDisponiveisComParametros2() throws ParseException {
		assertThat(sistemaController.pegarTrabalhosAcademicosDisponiveis(
					ItemFiltroDto.builder()
						.tipoTrabalho(TipoTrabalho.DISSERTACAO)
						.dataDefesa(formatter.parse("07/03/2021"))
						.build()
		).get(0).getCodigoCatalogacao()).isNotEmpty();
	}

}
