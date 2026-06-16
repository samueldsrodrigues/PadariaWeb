package br.com.padariaweb.service;

import java.util.Date;
import java.util.List;

import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.exception.ValidacaoException;

public interface IVendaService extends IGenericoCRUDManager<Venda, Long> {

	public List<Venda> pesquisarVenda(Venda filtro, Date dtInicial, Date dtFinal, Integer first, Integer maxPerPage);

	public void salvar(Venda venda) throws ValidacaoException;

	void cancelarVenda(Venda venda) throws ValidacaoException;

}
