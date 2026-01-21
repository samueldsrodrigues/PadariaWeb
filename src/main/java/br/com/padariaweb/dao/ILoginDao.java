package br.com.padariaweb.dao;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Funcionario;


@Repository
public interface ILoginDao extends IGenericoCRUDDAO<Funcionario, Long>{
	
	public Funcionario verificaFuncionario(Funcionario user);
}
