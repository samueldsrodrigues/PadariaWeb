package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.exception.ValidacaoException;

public interface IFuncionarioService extends IGenericoCRUDManager<Funcionario, Long> {

	public List<Funcionario> pesquisarFuncionario(Funcionario filtro, Integer first, Integer maxPerPage);

	public List<Funcionario> pesquisarFuncionario(Funcionario filtro);

	public void salvar(Funcionario funcionario) throws ValidacaoException;

//	public Funcionario pesquisarFuncionario(Integer funcionarioAlteracao);

	public void inativarFuncionario(Funcionario funcionarioInativar);

//	public void salvarPrimeiroAcesso(Funcionario u);

//	public void recuperarSenha(String email);
//
//	public void recuperarSenha(Funcionario funcionarioSelecionado);

}
