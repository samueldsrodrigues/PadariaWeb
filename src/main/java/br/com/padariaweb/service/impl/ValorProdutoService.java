package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IValorProdutoDao;
import br.com.padariaweb.entity.ValorProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IValorProdutoService;

@Service
@Transactional
public class ValorProdutoService extends GenericoCRUDManager<ValorProduto, Long> implements IValorProdutoService {

    @Autowired
    private IValorProdutoDao valorProdutoDao;

    public List<ValorProduto> pesquisarValorProduto(ValorProduto filtro, Integer first, Integer maxPerPage) {
        return valorProdutoDao.pesquisarValorProduto(filtro, first, maxPerPage);
    }

    public ValorProduto buscarPorProduto(Long sqProduto) {
        return valorProdutoDao.buscarPorProduto(sqProduto);
    }

    public void salvar(ValorProduto valorProduto) throws ValidacaoException {
        if (valorProduto.getProduto() == null || valorProduto.getProduto().getSqProduto() == null) {
            throw new ValidacaoException("Produto é obrigatório.");
        }

        if (valorProduto.getPreco() == null || valorProduto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacaoException("Preço deve ser maior ou igual a zero.");
        }

        if (valorProduto.getDesconto() == null) {
            valorProduto.setDesconto(BigDecimal.ZERO);
        }

        if (valorProduto.getSqValorProduto() != null)
            valorProdutoDao.update(valorProduto);
        else
            valorProdutoDao.save(valorProduto);
    }
}