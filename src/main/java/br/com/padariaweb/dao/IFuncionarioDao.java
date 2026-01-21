package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Funcionario;

@Repository
public interface IFuncionarioDao extends IGenericoCRUDDAO<Funcionario, Long> {

	public List<Funcionario> pesquisarFuncionario(Funcionario filtro, Integer first, Integer maxPerPage);

	public List<Funcionario> verificaFuncionarioExistente(Funcionario filtro);

}
