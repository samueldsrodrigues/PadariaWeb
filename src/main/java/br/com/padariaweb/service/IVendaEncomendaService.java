package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.VendaEncomenda;
import br.com.padariaweb.exception.ValidacaoException;

public interface IVendaEncomendaService extends IGenericoCRUDManager<VendaEncomenda, Long> {

	public List<VendaEncomenda> pesquisarVendaEncomenda(VendaEncomenda filtro, Integer first, Integer maxPerPage);

	public void salvar(VendaEncomenda vendaEncomenda) throws ValidacaoException;


}
