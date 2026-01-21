package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Cargo;

@Repository
public interface ICargoDao extends IGenericoCRUDDAO<Cargo, Long> {

	public List<Cargo> pesquisarCargo(Cargo filtro, Integer first, Integer maxPerPage);

	public List<Cargo> verificaCargoExistente(Cargo filtro);

}
