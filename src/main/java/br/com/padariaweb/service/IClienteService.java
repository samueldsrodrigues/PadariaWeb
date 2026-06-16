package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.exception.ValidacaoException;

public interface IClienteService extends IGenericoCRUDManager<Cliente, Long> {

    List<Cliente> pesquisarCliente(Cliente filtro, Integer first, Integer maxPerPage);

    void salvar(Cliente cliente) throws ValidacaoException;
}