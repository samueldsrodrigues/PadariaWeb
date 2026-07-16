package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IVendaProdutoDao;
import br.com.padariaweb.entity.VendaProduto;

@Repository
public class VendaProdutoDao extends GenericoCRUDDAOJPA<VendaProduto, Long> implements IVendaProdutoDao {

	@SuppressWarnings("unchecked")
	public List<VendaProduto> pesquisarVendaProduto(VendaProduto filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();

		if (filtro.getSqVendaProduto() != null) {
			c.add(Restrictions.eq("sqVendaProduto", filtro.getSqVendaProduto()));
		}

		if (filtro.getProduto() != null && filtro.getProduto().getSqProduto() != null) {
			c.createAlias("produto", "produto");
			c.add(Restrictions.eq("produto.sqProduto", filtro.getProduto().getSqProduto()));
		}

		if (filtro.getVenda() != null && filtro.getVenda().getSqVenda() != null) {
			c.createAlias("venda", "venda");
			c.add(Restrictions.eq("venda.sqVenda", filtro.getVenda().getSqVenda()));
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