package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IVendaProdutoDao;
import br.com.padariaweb.entity.VendaProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IVendaProdutoService;

@Service
@Transactional
public class VendaProdutoService extends GenericoCRUDManager<VendaProduto, Long> implements IVendaProdutoService {

    @Autowired
    private IVendaProdutoDao vendaProdutoDao;

    public List<VendaProduto> pesquisarVendaProduto(VendaProduto filtro, Integer first, Integer maxPerPage) {
        return vendaProdutoDao.pesquisarVendaProduto(filtro, first, maxPerPage);
    }

    public void salvar(VendaProduto vendaProduto) throws ValidacaoException {

        if (vendaProduto.getVenda() == null) {
            throw new ValidacaoException("Venda é obrigatória.");
        }

        if (vendaProduto.getProduto() == null) {
            throw new ValidacaoException("Produto é obrigatório.");
        }

        if (vendaProduto.getQuantidade() == null || vendaProduto.getQuantidade() <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero.");
        }

        if (vendaProduto.getPrecoUnitario() == null) {
            throw new ValidacaoException("Preço unitário é obrigatório.");
        }

        if (vendaProduto.getSubtotal() == null) {
            vendaProduto.setSubtotal(
                vendaProduto.getPrecoUnitario().multiply(BigDecimal.valueOf(vendaProduto.getQuantidade()))
            );
        }

        if (vendaProduto.getSqVendaProduto() != null)
            vendaProdutoDao.update(vendaProduto);
        else
            vendaProdutoDao.save(vendaProduto);
    }
}