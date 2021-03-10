package br.com.ifsp.es4a4.projeto.repository.spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.Livro;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;

@Repository
public class LivroSpecRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Livro> findBooks(ItemFiltroDto filtro) {
		
		String hql = "";
		Map<String, Object> parameters = new HashMap<>();
		
		hql = hql.concat("select distinct(l) from Livro l ");
		hql = hql.concat("where l.situacaoItem in (:situacaoDisponivel) ");
		parameters.put("situacaoDisponivel", new ArrayList<Situacao>(Arrays.asList(Situacao.DISPONIVEL, Situacao.CONSULTA_LOCAL)));
		
		if(Objects.nonNull(filtro.getTitulo())) {
			hql = hql.concat("and l.titulo like :titulo ");
			parameters.put("titulo", "%" + filtro.getTitulo() + "%");
		}
		if(Objects.nonNull(filtro.getSubtitulo())) {
			hql = hql.concat("and l.subtitulo like :subtitulo ");
			parameters.put("subtitulo", "%" + filtro.getSubtitulo() + "%");
		}
		if(Objects.nonNull(filtro.getAreaConhecimento())) {
			hql = hql.concat("and l.areaConhecimento in (:areaConhecimento) ");
			parameters.put("areaConhecimento", filtro.getAreaConhecimento());
		}
		if(Objects.nonNull(filtro.getDataPublicacao())) {
			hql = hql.concat("and l.dataPublicacao between :dataInicio and :dataFim ");
			parameters.put("dataInicio", DateFormat.subtractDays(filtro.getDataPublicacao(), 30));
			parameters.put("dataFim", DateFormat.addDays(filtro.getDataPublicacao(), 30));
		}
		if(Objects.nonNull(filtro.getIdAcervo())) {
			hql = hql.concat("and l.idAcervo = :idAcervo ");
			parameters.put("idAcervo", filtro.getIdAcervo());
		}
		
		if(Objects.nonNull(filtro.getEdicao())) {
			hql = hql.concat("and l.edicao = :edicao ");
			parameters.put("edicao", filtro.getEdicao());
		}
		if(Objects.nonNull(filtro.getIsbn())) {
			hql = hql.concat("and l.isbn = :isbn ");
			parameters.put("isbn", filtro.getIsbn());
		}
		
		TypedQuery<Livro> query = entityManager.createQuery(hql, Livro.class);
		parameters.forEach((k, v) -> query.setParameter(k, v));
		
		return query.getResultList();
		
	}

}
