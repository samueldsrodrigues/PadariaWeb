package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.exception.ValidacaoException;

public interface IVendaService extends IGenericoCRUDManager<Venda, Long> {

	public List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage);

	public List<Venda> pesquisarVenda(Venda filtro);

	public void salvar(Venda venda) throws ValidacaoException;

//	public Venda pesquisarVenda(Integer vendaAlteracao);

	public void inativarVenda(Venda vendaInativar);

//	public void salvarPrimeiroAcesso(Venda u);

//	public void recuperarSenha(String email);
//
//	public void recuperarSenha(Venda vendaSelecionado);

}
