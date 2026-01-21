package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IVendaDao;
import br.com.padariaweb.entity.Venda;

@Repository
public class VendaDao extends GenericoCRUDDAOJPA<Venda, Long> implements IVendaDao {

	public List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage) {
		return createCriteriaPesquisar(filtro, first, maxPerPage);
	}

	/*
	 * public int countVenda(Venda filtro, Integer userSqLoja, Integer
	 * userSqGrupoLoja, boolean isUserRoot) { Integer count = (Integer)
	 * createCriteriaPesquisar(filtro, null, null, userSqLoja, userSqGrupoLoja,
	 * isUserRoot) .setProjection(Projections.rowCount()) .uniqueResult(); return
	 * count.intValue(); }
	 */

	@SuppressWarnings("unchecked")
	private List<Venda> createCriteriaPesquisar(Venda filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();
		c.createAlias("loja", "l");

		if (filtro.getSqVenda() != null)
			c.add(Restrictions.eq("sqVenda", filtro.getSqVenda()));

		if (filtro.getDtVenda() != null)
			c.add(Restrictions.ilike("dtVenda", filtro.getDtVenda()));
		
		if (filtro.getFkFuncionario() != null)
			c.add(Restrictions.ilike("nome", filtro.getFkFuncionario()));

		if (first != null)
			c.setFirstResult(first);
		if (maxPerPage != null)
			c.setMaxResults(maxPerPage);

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Venda> verificaVendaExistente(Venda filtro) {
		Criteria c = criteria();

		if (filtro.getSqVenda() != null)
			c.add(Restrictions.ne("sqVenda", filtro.getSqVenda()));

		return c.list();
	}

	

}
