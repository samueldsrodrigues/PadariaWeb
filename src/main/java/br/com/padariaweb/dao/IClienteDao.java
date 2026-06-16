package br.com.padariaweb.dao;

import java.util.List;

import br.com.padariaweb.entity.Cliente;

public interface IClienteDao extends IGenericoCRUDDAO<Cliente, Long> {

    List<Cliente> pesquisarCliente(Cliente filtro, Integer first, Integer maxPerPage);

    List<Cliente> verificaClienteExistente(Cliente filtro);
}