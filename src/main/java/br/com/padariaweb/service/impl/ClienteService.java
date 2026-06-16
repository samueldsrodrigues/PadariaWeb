package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IClienteDao;
import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IClienteService;

@Service
@Transactional
public class ClienteService extends GenericoCRUDManager<Cliente, Long> implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    public List<Cliente> pesquisarCliente(Cliente filtro, Integer first, Integer maxPerPage) {
        return clienteDao.pesquisarCliente(filtro, first, maxPerPage);
    }

    public void salvar(Cliente cliente) throws ValidacaoException {
        List<Cliente> clientes = clienteDao.verificaClienteExistente(cliente);

        if (!clientes.isEmpty()) {
            throw new ValidacaoException("Cliente já cadastrado com este CPF.");
        }

        if (cliente.getSqCliente() != null)
            clienteDao.update(cliente);
        else
            clienteDao.save(cliente);
    }
}