package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IEncomendaProdutoDao;
import br.com.padariaweb.entity.EncomendaProduto;

@Repository
public class EncomendaProdutoDao extends GenericoCRUDDAOJPA<EncomendaProduto, Long> implements IEncomendaProdutoDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<EncomendaProduto> pesquisarEncomendaProduto(EncomendaProduto filtro, Integer first,
			Integer maxPerPage) {
		Criteria c = criteria();

		if (filtro != null) {
		    if (filtro.getSqEncomendaProduto() != null) {
		        c.add(Restrictions.eq(
		            "sqEncomendaProduto",
		            filtro.getSqEncomendaProduto()
		        ));
		    }

		    if (filtro.getProduto() != null
		            && filtro.getProduto().getSqProduto() != null) {
		        c.createAlias("produto", "produto");
		        c.add(Restrictions.eq(
		            "produto.sqProduto",
		            filtro.getProduto().getSqProduto()
		        ));
		    }

		    if (filtro.getEncomenda() != null
		            && filtro.getEncomenda().getSqEncomenda() != null) {
		        c.createAlias("encomenda", "encomenda");
		        c.add(Restrictions.eq(
		            "encomenda.sqEncomenda",
		            filtro.getEncomenda().getSqEncomenda()
		        ));
		    }
		}

		if (first != null) {
			c.setFirstResult(first);
		}

		if (maxPerPage != null) {
			c.setMaxResults(maxPerPage);
		}

		return c.list();
	}

}