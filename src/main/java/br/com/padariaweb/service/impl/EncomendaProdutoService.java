package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IEncomendaProdutoDao;
import br.com.padariaweb.entity.EncomendaProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IEncomendaProdutoService;

@Service
@Transactional
public class EncomendaProdutoService extends GenericoCRUDManager<EncomendaProduto, Long>
		implements IEncomendaProdutoService {

	@Autowired
	private IEncomendaProdutoDao encomendaProdutoDao;

	public List<EncomendaProduto> pesquisarEncomendaProduto(EncomendaProduto filtro, Integer first,
			Integer maxPerPage) {
		return encomendaProdutoDao.pesquisarEncomendaProduto(filtro, first, maxPerPage);
	}

	@Override
	public void salvar(EncomendaProduto encomendaProduto)
	        throws ValidacaoException {

	    if (encomendaProduto == null) {
	        throw new ValidacaoException("Item da encomenda é obrigatório.");
	    }

	    if (encomendaProduto.getEncomenda() == null
	            || encomendaProduto.getEncomenda().getSqEncomenda() == null) {
	        throw new ValidacaoException("Encomenda é obrigatória.");
	    }

	    boolean possuiProduto = encomendaProduto.getProduto() != null
	            && encomendaProduto.getProduto().getSqProduto() != null;

	    boolean possuiNomePersonalizado =
	            encomendaProduto.getNomeItem() != null
	            && !encomendaProduto.getNomeItem().trim().isEmpty();

	    if (!possuiProduto && !possuiNomePersonalizado) {
	        throw new ValidacaoException(
	            "Selecione um produto cadastrado ou informe um item personalizado."
	        );
	    }

	    if (possuiProduto) {
	        encomendaProduto.setNomeItem(
	            encomendaProduto.getProduto().getNome()
	        );
	    } else {
	        encomendaProduto.setNomeItem(
	            encomendaProduto.getNomeItem().trim()
	        );
	    }

	    if (encomendaProduto.getQuantidade() == null
	            || encomendaProduto.getQuantidade() <= 0) {
	        throw new ValidacaoException(
	            "Quantidade deve ser maior que zero."
	        );
	    }

	    if (encomendaProduto.getPrecoUnitario() == null
	            || encomendaProduto.getPrecoUnitario()
	                    .compareTo(BigDecimal.ZERO) <= 0) {
	        throw new ValidacaoException(
	            "Preço unitário deve ser maior que zero."
	        );
	    }

	    BigDecimal subtotal = encomendaProduto.getPrecoUnitario()
	            .multiply(BigDecimal.valueOf(
	                encomendaProduto.getQuantidade()
	            ));

	    encomendaProduto.setSubtotal(subtotal);

	    if (encomendaProduto.getSqEncomendaProduto() == null) {
	        encomendaProdutoDao.save(encomendaProduto);
	    } else {
	        encomendaProdutoDao.update(encomendaProduto);
	    }
	}
}