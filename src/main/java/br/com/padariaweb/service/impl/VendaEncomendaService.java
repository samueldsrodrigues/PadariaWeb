package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IVendaEncomendaDao;
import br.com.padariaweb.entity.VendaEncomenda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IVendaEncomendaService;

@Service
@Transactional
public class VendaEncomendaService extends GenericoCRUDManager<VendaEncomenda, Long> implements IVendaEncomendaService {

    @Autowired
    private IVendaEncomendaDao vendaEncomendaDao;

    public List<VendaEncomenda> pesquisarVendaEncomenda(VendaEncomenda filtro, Integer first, Integer maxPerPage) {
        return vendaEncomendaDao.pesquisarVendaEncomenda(filtro, first, maxPerPage);
    }

    public void salvar(VendaEncomenda vendaEncomenda) throws ValidacaoException {

    	if (vendaEncomenda.getVenda() == null
    	        || vendaEncomenda.getVenda().getSqVenda() == null) {
    	    throw new ValidacaoException("Venda é obrigatória.");
    	}

    	if (vendaEncomenda.getEncomenda() == null
    	        || vendaEncomenda.getEncomenda().getSqEncomenda() == null) {
    	    throw new ValidacaoException("Encomenda é obrigatória.");
    	}

        if (vendaEncomenda.getSqVendaEncomenda() != null)
            vendaEncomendaDao.update(vendaEncomenda);
        else
            vendaEncomendaDao.save(vendaEncomenda);
    }
}