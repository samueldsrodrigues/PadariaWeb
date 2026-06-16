package br.com.padariaweb.dao;

import java.util.List;

import br.com.padariaweb.entity.ValorProduto;

public interface IValorProdutoDao extends IGenericoCRUDDAO<ValorProduto, Long> {

    List<ValorProduto> pesquisarValorProduto(ValorProduto filtro, Integer first, Integer maxPerPage);

    ValorProduto buscarPorProduto(Long sqProduto);
}