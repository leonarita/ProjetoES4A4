package br.com.ifsp.es4a4.projeto.repository.spec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ifsp.es4a4.projeto.facade.ItemFiltroDto;
import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;

@Repository
public class TrabalhoAcademicoSpecRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<TrabalhoAcademico> findWorks(ItemFiltroDto filtro, List<Situacao> situacoes) {
		
		String hql = "";
		Map<String, Object> parameters = new HashMap<>();
		
		hql = hql.concat("select distinct(l) from TrabalhoAcademico l ");
		hql = hql.concat("where l.situacaoItem in (:situacaoDisponivel) ");
		parameters.put("situacaoDisponivel", situacoes);
		
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
		
		if(Objects.nonNull(filtro.getDataDefesa())) {
			hql = hql.concat("and l.dataPublicacao between :dataDefesaInicio and :dataDefesaFim ");
			parameters.put("dataDefesaInicio", DateFormat.subtractDays(filtro.getDataDefesa(), 30));
			parameters.put("dataDefesaFim", DateFormat.addDays(filtro.getDataDefesa(), 30));
		}
		if(Objects.nonNull(filtro.getNomeCurso())) {
			hql = hql.concat("and l.nomeCurso = :nomeCurso ");
			parameters.put("nomeCurso", filtro.getNomeCurso());
		}
		if(Objects.nonNull(filtro.getTipoTrabalho())) {
			hql = hql.concat("and l.tipoTrabalho = :tipoTrabalho ");
			parameters.put("tipoTrabalho", filtro.getTipoTrabalho());
		}
		
		if(Objects.nonNull(filtro.getCodigoCatalogacao())) {
			hql = hql.concat("and l.codigoCatalogacao = :codigoCatalogacao ");
			parameters.put("codigoCatalogacao", filtro.getCodigoCatalogacao());
		}
		
		TypedQuery<TrabalhoAcademico> query = entityManager.createQuery(hql, TrabalhoAcademico.class);
		parameters.forEach((k, v) -> query.setParameter(k, v));
		
		return query.getResultList();
		
	}

}
