package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Produto;

@Repository
public interface IProdutoDao extends IGenericoCRUDDAO<Produto, Long> {

	public List<Produto> pesquisarProduto(Produto filtro, Integer first, Integer maxPerPage);

	public List<Produto> verificaProdutoExistente(Produto filtro);

}
