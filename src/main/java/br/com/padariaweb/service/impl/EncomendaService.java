package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IEncomendaDao;
import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IEncomendaService;

@Service
@Transactional
public class EncomendaService extends GenericoCRUDManager<Encomenda, Long> implements IEncomendaService {

	@Autowired
	IEncomendaDao encomendaDao;

	@Override
	public List<Encomenda> pesquisarEncomenda(Encomenda filtro) {
		return encomendaDao.verificaEncomendaExistente(filtro);
	}

	@Override
	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage) {

		return encomendaDao.pesquisarEncomenda(filtro, first, maxPerPage);
	}

	public void salvar(Encomenda encomenda) throws ValidacaoException {
		List<Encomenda> encomendas = encomendaDao.verificaEncomendaExistente(encomenda);
		// Caso seja inclusao de um novo encomenda
		if ((encomenda.getSqEncomenda() == null && !encomendas.isEmpty())
				// Caso seja alteracao de encomenda
				|| (encomenda.getSqEncomenda() != null && !encomendas.isEmpty()
						&& !encomenda.getSqEncomenda().equals(encomendas.get(0).getSqEncomenda())))
			throw new ValidacaoException("Encomenda já cadastrada na base de dados.");

		if (encomenda.getSqEncomenda() != null)
			encomendaDao.update(encomenda);
		else
			encomendaDao.save(encomenda);
	}

	public Encomenda pesquisarEncomenda(Long encomendaAlteracao) {
		Encomenda u = (Encomenda) encomendaDao.findById(Encomenda.class, encomendaAlteracao);
		encomendaDao.evict(u);
		return u;
	}

	public void inativarEncomenda(Encomenda encomendaInativar) {
		encomendaDao.save(encomendaInativar);

	}

}
