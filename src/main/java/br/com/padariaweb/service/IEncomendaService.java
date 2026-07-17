package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.entity.EncomendaProduto;
import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.exception.ValidacaoException;

public interface IEncomendaService extends IGenericoCRUDManager<Encomenda, Long> {

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage);

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro);

	public void salvar(Encomenda encomenda) throws ValidacaoException;

	public void inativarEncomenda(Encomenda encomendaInativar);

	public void finalizarEncomenda(Encomenda encomenda, List<EncomendaProduto> itens, FormaPagamento pagamentoEntrada,
			Funcionario funcionario) throws ValidacaoException;

}
