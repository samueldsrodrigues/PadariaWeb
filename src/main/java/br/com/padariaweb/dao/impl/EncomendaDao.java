package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IEncomendaDao;
import br.com.padariaweb.entity.Encomenda;

@Repository
public class EncomendaDao extends GenericoCRUDDAOJPA<Encomenda, Long> implements IEncomendaDao {

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage) {
		return createCriteriaPesquisar(filtro, first, maxPerPage);
	}

	/*
	 * public int countEncomenda(Encomenda filtro, Integer userSqLoja, Integer
	 * userSqGrupoLoja, boolean isUserRoot) { Integer count = (Integer)
	 * createCriteriaPesquisar(filtro, null, null, userSqLoja, userSqGrupoLoja,
	 * isUserRoot) .setProjection(Projections.rowCount()) .uniqueResult(); return
	 * count.intValue(); }
	 */

	@SuppressWarnings("unchecked")
	private List<Encomenda> createCriteriaPesquisar(Encomenda filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();
		c.createAlias("loja", "l");

		if (filtro.getSqEncomenda() != null)
			c.add(Restrictions.eq("sqEncomenda", filtro.getSqEncomenda()));

		if (filtro.getDtRetirada() != null)
			c.add(Restrictions.ilike("dtRetirada", filtro.getDtRetirada()));
		
		if (filtro.getFkCliente() != null)
			c.add(Restrictions.ilike("fkCliente", filtro.getFkCliente()));

		if (first != null)
			c.setFirstResult(first);
		if (maxPerPage != null)
			c.setMaxResults(maxPerPage);

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Encomenda> verificaEncomendaExistente(Encomenda filtro) {
		Criteria c = criteria();

		if (filtro.getSqEncomenda() != null)
			c.add(Restrictions.ne("sqEncomenda", filtro.getSqEncomenda()));

		return c.list();
	}

	

}
