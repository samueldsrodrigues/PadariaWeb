package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IVendaEncomendaDao;
import br.com.padariaweb.entity.VendaEncomenda;

@Repository
public class VendaEncomendaDao extends GenericoCRUDDAOJPA<VendaEncomenda, Long> implements IVendaEncomendaDao {

	@SuppressWarnings("unchecked")
	public List<VendaEncomenda> pesquisarVendaEncomenda(VendaEncomenda filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();

		if (filtro.getSqVendaEncomenda() != null) {
			c.add(Restrictions.eq("sqVendaEncomenda", filtro.getSqVendaEncomenda()));
		}

		if (filtro.getEncomenda() != null && filtro.getEncomenda().getSqEncomenda() != null) {
			c.createAlias("encomenda", "encomenda");
			c.add(Restrictions.eq("encomenda.sqEncomenda", filtro.getEncomenda().getSqEncomenda()));
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