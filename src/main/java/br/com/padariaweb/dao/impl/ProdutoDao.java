package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IProdutoDao;
import br.com.padariaweb.entity.Produto;

@Repository
public class ProdutoDao extends GenericoCRUDDAOJPA<Produto, Long> implements IProdutoDao {

	public List<Produto> pesquisarProduto(Produto filtro, Integer first, Integer maxPerPage) {
		return createCriteriaPesquisar(filtro, first, maxPerPage);
	}

	/*
	 * public int countProduto(Produto filtro, Integer userSqLoja, Integer
	 * userSqGrupoLoja, boolean isUserRoot) { Integer count = (Integer)
	 * createCriteriaPesquisar(filtro, null, null, userSqLoja, userSqGrupoLoja,
	 * isUserRoot) .setProjection(Projections.rowCount()) .uniqueResult(); return
	 * count.intValue(); }
	 */

	@SuppressWarnings("unchecked")
	private List<Produto> createCriteriaPesquisar(Produto filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();
		c.createAlias("loja", "l");

		if (filtro.getSqProduto() != null)
			c.add(Restrictions.eq("sqProduto", filtro.getSqProduto()));

		if (filtro.getNome() != null && !filtro.getNome().isEmpty())
			c.add(Restrictions.ilike("nome", '%' + filtro.getNome() + '%'));

		if (first != null)
			c.setFirstResult(first);
		if (maxPerPage != null)
			c.setMaxResults(maxPerPage);

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> verificaProdutoExistente(Produto filtro) {
		Criteria c = criteria();

		if (filtro.getSqProduto() != null)
			c.add(Restrictions.ne("sqProduto", filtro.getSqProduto()));

		return c.list();
	}

	

}
