package br.com.padariaweb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.ICargoDao;
import br.com.padariaweb.entity.Cargo;

@Repository
public class CargoDao extends GenericoCRUDDAOJPA<Cargo, Long> implements ICargoDao {

	public List<Cargo> pesquisarCargo(Cargo filtro, Integer first, Integer maxPerPage) {
		return createCriteriaPesquisar(filtro, first, maxPerPage);
	}

	@SuppressWarnings("unchecked")
	private List<Cargo> createCriteriaPesquisar(Cargo filtro, Integer first, Integer maxPerPage) {
		Criteria c = criteria();

		if (filtro.getSqCargo() != null)
			c.add(Restrictions.eq("sqCargo", filtro.getSqCargo()));

		if (filtro.getNome() != null && !filtro.getNome().isEmpty())
			c.add(Restrictions.ilike("nome", '%' + filtro.getNome() + '%'));

		if (first != null)
			c.setFirstResult(first);
		if (maxPerPage != null)
			c.setMaxResults(maxPerPage);

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Cargo> verificaCargoExistente(Cargo filtro) {
		Criteria c = criteria();

		if (filtro.getSqCargo() != null)
			c.add(Restrictions.ne("sqCargo", filtro.getSqCargo()));

		return c.list();
	}

}
