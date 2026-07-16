package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.EncomendaProduto;
import br.com.padariaweb.exception.ValidacaoException;

public interface IEncomendaProdutoService extends IGenericoCRUDManager<EncomendaProduto, Long> {

	public List<EncomendaProduto> pesquisarEncomendaProduto(EncomendaProduto filtro, Integer first, Integer maxPerPage);

	public void salvar(EncomendaProduto encomendaProduto) throws ValidacaoException;

}
