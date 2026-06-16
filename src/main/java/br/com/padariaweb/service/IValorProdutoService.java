package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.ValorProduto;
import br.com.padariaweb.exception.ValidacaoException;

public interface IValorProdutoService extends IGenericoCRUDManager<ValorProduto, Long> {

    List<ValorProduto> pesquisarValorProduto(ValorProduto filtro, Integer first, Integer maxPerPage);

    ValorProduto buscarPorProduto(Long sqProduto);

    void salvar(ValorProduto valorProduto) throws ValidacaoException;
}