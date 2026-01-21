package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IFuncionarioDao;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.util.Util;

@Repository
public class FuncionarioDao extends GenericoCRUDDAOJPA<Funcionario, Long> implements IFuncionarioDao {

	public List<Funcionario> pesquisarFuncionario(Funcionario filtro, Integer first, Integer maxPerPage) {
		return createCriteriaPesquisar(filtro, first, maxPerPage);
	}

	/*
	 * public int countFuncionario(Funcionario filtro, Integer userSqLoja, Integer
	 * userSqGrupoLoja, boolean isUserRoot) { Integer count = (Integer)
	 * createCriteriaPesquisar(filtro, null, null, userSqLoja, userSqGrupoLoja,
	 * isUserRoot) .setProjection(Projections.rowCount()) .uniqueResult(); return
	 * count.intValue(); }
	 */

	@SuppressWarnings("unchecked")
	private List<Funcionario> createCriteriaPesquisar(Funcionario filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();
//		c.createAlias("nome", "f");

		if (filtro.getSqFuncionario() != null)
			c.add(Restrictions.eq("sqFuncionario", filtro.getSqFuncionario()));
		
		if (filtro.getEmail() != null && !filtro.getEmail().isEmpty())
			c.add(Restrictions.eq("email", filtro.getEmail()));

		if (filtro.getNome() != null && !filtro.getNome().isEmpty())
			c.add(Restrictions.ilike("nome", '%' + filtro.getNome() + '%'));
		
		if(Util.isValido(filtro.getCargo()) && Util.isValido(filtro.getCargo().getSqCargo())){
			c.add(Restrictions.eq("cargo", filtro.getCargo()));
		}

		if (first != null)
			c.setFirstResult(first);
		if (maxPerPage != null)
			c.setMaxResults(maxPerPage);

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> verificaFuncionarioExistente(Funcionario filtro) {
		Criteria c = criteria();
		if (filtro.getEmail() != null && !filtro.getEmail().isEmpty())
			c.add(Restrictions.eq("email", filtro.getEmail().toLowerCase().trim()));

		if (filtro.getSqFuncionario() != null)
			c.add(Restrictions.ne("sqFuncionario", filtro.getSqFuncionario()));

		return c.list();
	}

	

}
