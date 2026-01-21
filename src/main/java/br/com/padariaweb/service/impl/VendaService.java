package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IVendaDao;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IVendaService;

@Service
@Transactional
public class VendaService extends GenericoCRUDManager<Venda, Long> implements IVendaService {

	@Autowired
	IVendaDao vendaDao;

	@Override
	public List<Venda> pesquisarVenda(Venda filtro) {
		return vendaDao.verificaVendaExistente(filtro);
	}

	@Override
	public List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage) {

		return vendaDao.pesquisarVenda(filtro, first, maxPerPage);
	}

	public void salvar(Venda venda) throws ValidacaoException {
		List<Venda> vendas = vendaDao.verificaVendaExistente(venda);
		// Caso seja inclusao de um novo venda
		if ((venda.getSqVenda() == null && !vendas.isEmpty())
				// Caso seja alteracao de venda
				|| (venda.getSqVenda() != null && !vendas.isEmpty()
						&& !venda.getSqVenda().equals(vendas.get(0).getSqVenda())))
			throw new ValidacaoException("Venda já cadastrada na base de dados.");

		

		if (venda.getSqVenda() != null)
			vendaDao.update(venda);
		else
			vendaDao.save(venda);
	}

	public Venda pesquisarVenda(Long vendaAlteracao) {
		Venda u = (Venda) vendaDao.findById(Venda.class, vendaAlteracao);
		vendaDao.evict(u);
		return u;
	}

	public void inativarVenda(Venda vendaInativar) {
		vendaDao.save(vendaInativar);

	}

}
