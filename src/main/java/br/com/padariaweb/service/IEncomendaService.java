package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.exception.ValidacaoException;

public interface IEncomendaService extends IGenericoCRUDManager<Encomenda, Long> {

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage);

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro);

	public void salvar(Encomenda encomenda) throws ValidacaoException;

//	public Encomenda pesquisarEncomenda(Integer encomendaAlteracao);

	public void inativarEncomenda(Encomenda encomendaInativar);

//	public void salvarPrimeiroAcesso(Encomenda u);

//	public void recuperarSenha(String email);
//
//	public void recuperarSenha(Encomenda encomendaSelecionado);

}
