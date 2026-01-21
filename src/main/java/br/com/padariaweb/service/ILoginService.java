package br.com.padariaweb.service;

import br.com.padariaweb.entity.Funcionario;

public interface ILoginService extends IGenericoCRUDManager<Funcionario, Long>{

	public Funcionario verificaFuncionario(Funcionario func);
}
