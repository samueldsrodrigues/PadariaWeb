package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IFormaPagamentoDao;
import br.com.padariaweb.entity.FormaPagamento;

@Repository
public class FormaPagamentoDao extends GenericoCRUDDAOJPA<FormaPagamento, Long> implements IFormaPagamentoDao {

    @SuppressWarnings("unchecked")
    public List<FormaPagamento> pesquisarFormaPagamento(FormaPagamento filtro, Integer first, Integer maxPerPage) {
        Criteria c = criteria();

        if (filtro.getSqFormaPagamento() != null) {
            c.add(Restrictions.eq("sqFormaPagamento", filtro.getSqFormaPagamento()));
        }

        if (filtro.getVenda() != null && filtro.getVenda().getSqVenda() != null) {
    	    c.createAlias("venda", "venda");
    	    c.add(Restrictions.eq("venda.sqVenda", filtro.getVenda().getSqVenda()));
    	}

        if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
            c.add(Restrictions.eq("tipo", filtro.getTipo()));
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