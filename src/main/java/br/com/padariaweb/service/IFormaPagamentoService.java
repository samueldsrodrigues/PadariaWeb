package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.exception.ValidacaoException;

public interface IFormaPagamentoService extends IGenericoCRUDManager<FormaPagamento, Long> {

	public List<FormaPagamento> pesquisarFormaPagamento(FormaPagamento filtro, Integer first, Integer maxPerPage);

	public void salvar(FormaPagamento formaPagamento) throws ValidacaoException;


}
