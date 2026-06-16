package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IValorProdutoDao;
import br.com.padariaweb.entity.ValorProduto;

@Repository
public class ValorProdutoDao extends GenericoCRUDDAOJPA<ValorProduto, Long> implements IValorProdutoDao {

    @SuppressWarnings("unchecked")
    public List<ValorProduto> pesquisarValorProduto(ValorProduto filtro, Integer first, Integer maxPerPage) {
        Criteria c = criteria();

        if (filtro.getSqValorProduto() != null) {
            c.add(Restrictions.eq("sqValorProduto", filtro.getSqValorProduto()));
        }

        if (filtro.getProduto() != null && filtro.getProduto().getSqProduto() != null) {
            c.createAlias("produto", "produto");
            c.add(Restrictions.eq("produto.sqProduto", filtro.getProduto().getSqProduto()));
        }

        if (first != null) {
            c.setFirstResult(first);
        }

        if (maxPerPage != null) {
            c.setMaxResults(maxPerPage);
        }

        return c.list();
    }

    @SuppressWarnings("unchecked")
    public ValorProduto buscarPorProduto(Long sqProduto) {
        Criteria c = criteria();
        c.createAlias("produto", "produto");
        c.add(Restrictions.eq("produto.sqProduto", sqProduto));
        c.setMaxResults(1);

        List<ValorProduto> resultados = c.list();

        if (resultados == null || resultados.isEmpty()) {
            return null;
        }

        return resultados.get(0);
    }
}