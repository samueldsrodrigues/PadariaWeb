package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.exception.ValidacaoException;

public interface IProdutoService extends IGenericoCRUDManager<Produto, Long> {

	public List<Produto> pesquisarProduto(Produto filtro, Integer first, Integer maxPerPage);

	public List<Produto> pesquisarProduto(Produto filtro);

	public void salvar(Produto produto) throws ValidacaoException;

//	public Produto pesquisarProduto(Integer produtoAlteracao);

	public void inativarProduto(Produto produtoInativar);

//	public void salvarPrimeiroAcesso(Produto u);

//	public void recuperarSenha(String email);
//
//	public void recuperarSenha(Produto produtoSelecionado);

}
