package br.com.padariaweb.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Funcionario;


@Repository
public class LoginDao extends GenericoCRUDDAOJPA<Funcionario, Long> implements br.com.padariaweb.dao.ILoginDao{
	
	public Funcionario verificaFuncionario(Funcionario func){
		Criteria c = getSession().createCriteria(Funcionario.class);
		
		c.add(Restrictions.eq("email", func.getEmail()));
		
		Funcionario funcionario = (Funcionario) c.uniqueResult();
		
		if(funcionario != null){
			return funcionario;	
		}
		return null;
	}
}
