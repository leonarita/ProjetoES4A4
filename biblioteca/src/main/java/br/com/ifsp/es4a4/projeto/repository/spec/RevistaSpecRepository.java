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
import br.com.ifsp.es4a4.projeto.model.Revista;
import br.com.ifsp.es4a4.projeto.model.enumerations.Situacao;
import br.com.ifsp.es4a4.projeto.utils.formatation.DateFormat;

@Repository
public class RevistaSpecRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Revista> findMaganizes(ItemFiltroDto filtro, List<Situacao> situacoes) {
		
		String hql = "";
		Map<String, Object> parameters = new HashMap<>();
		
		hql = hql.concat("select distinct(l) from Revista l ");
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
		
		if(Objects.nonNull(filtro.getIssn())) {
			hql = hql.concat("and l.issn = :issn ");
			parameters.put("issn", filtro.getIssn());
		}
		if(Objects.nonNull(filtro.getNumero())) {
			hql = hql.concat("and l.numero = :numero ");
			parameters.put("numero", filtro.getNumero());
		}
		if(Objects.nonNull(filtro.getVolume())) {
			hql = hql.concat("and l.volume = :volume ");
			parameters.put("volume", filtro.getVolume());
		}
		
		if(Objects.nonNull(filtro.getCodigoCatalogacao())) {
			hql = hql.concat("and l.codigoCatalogacao = :codigoCatalogacao ");
			parameters.put("codigoCatalogacao", filtro.getCodigoCatalogacao());
		}
		
		TypedQuery<Revista> query = entityManager.createQuery(hql, Revista.class);
		parameters.forEach((k, v) -> query.setParameter(k, v));
		
		return query.getResultList();
		
	}

}
