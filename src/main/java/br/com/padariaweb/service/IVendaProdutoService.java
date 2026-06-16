package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.VendaProduto;
import br.com.padariaweb.exception.ValidacaoException;

public interface IVendaProdutoService extends IGenericoCRUDManager<VendaProduto, Long> {

	public List<VendaProduto> pesquisarVendaProduto(VendaProduto filtro, Integer first, Integer maxPerPage);

	public void salvar(VendaProduto vendaProduto) throws ValidacaoException;


}
