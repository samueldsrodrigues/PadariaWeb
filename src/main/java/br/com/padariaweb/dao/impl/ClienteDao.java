package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IClienteDao;
import br.com.padariaweb.entity.Cliente;

@Repository
public class ClienteDao extends GenericoCRUDDAOJPA<Cliente, Long> implements IClienteDao {

    public List<Cliente> pesquisarCliente(Cliente filtro, Integer first, Integer maxPerPage) {
        Criteria c = criteria();

        if (filtro.getNome() != null && !filtro.getNome().trim().isEmpty())
            c.add(Restrictions.ilike("nome", "%" + filtro.getNome() + "%"));

        if (filtro.getCpf() != null && !filtro.getCpf().trim().isEmpty())
            c.add(Restrictions.ilike("cpf", "%" + filtro.getCpf() + "%"));

        if (first != null)
            c.setFirstResult(first);

        if (maxPerPage != null)
            c.setMaxResults(maxPerPage);

        return c.list();
    }

    public List<Cliente> verificaClienteExistente(Cliente filtro) {
        Criteria c = criteria();

        if (filtro.getSqCliente() != null)
            c.add(Restrictions.ne("sqCliente", filtro.getSqCliente()));

        if (filtro.getCpf() != null && !filtro.getCpf().trim().isEmpty())
            c.add(Restrictions.eq("cpf", filtro.getCpf()));

        return c.list();
    }
}