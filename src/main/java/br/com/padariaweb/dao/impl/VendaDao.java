package br.com.padariaweb.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IVendaDao;
import br.com.padariaweb.entity.Venda;

@Repository
public class VendaDao extends GenericoCRUDDAOJPA<Venda, Long> implements IVendaDao {

    @SuppressWarnings("unchecked")
    public List<Venda> pesquisarVenda(Venda filtro, Date dtInicial, Date dtFinal, Integer first, Integer maxPerPage) {
        Criteria c = criteria();

        if (filtro.getSqVenda() != null) {
            c.add(Restrictions.eq("sqVenda", filtro.getSqVenda()));
        }

        if (dtInicial != null) {
            c.add(Restrictions.ge("dtVenda", dtInicial));
        }

        if (dtFinal != null) {
            c.add(Restrictions.le("dtVenda", dtFinal));
        }

        if (filtro.getFuncionario() != null && filtro.getFuncionario().getSqFuncionario() != null) {
            c.createAlias("funcionario", "funcionario");
            c.add(Restrictions.eq("funcionario.sqFuncionario", filtro.getFuncionario().getSqFuncionario()));
        }

        if (filtro.getCliente() != null && filtro.getCliente().getSqCliente() != null) {
            c.createAlias("cliente", "cliente");
            c.add(Restrictions.eq("cliente.sqCliente", filtro.getCliente().getSqCliente()));
        }
        
        if (filtro.getStatus() != null && !filtro.getStatus().trim().isEmpty()) {
            c.add(Restrictions.eq("status", filtro.getStatus()));
        }

        if (first != null) {
            c.setFirstResult(first);
        }

        if (maxPerPage != null) {
            c.setMaxResults(maxPerPage);
        }

        return c.list();
    }

    public List<Venda> verificaVendaExistente(Venda filtro) {
        Criteria c = criteria();

        if (filtro.getSqVenda() != null) {
            c.add(Restrictions.ne("sqVenda", filtro.getSqVenda()));
        }

        return c.list();
    }
}