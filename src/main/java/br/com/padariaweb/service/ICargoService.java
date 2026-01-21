package br.com.padariaweb.service;

import java.util.List;

import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.exception.ValidacaoException;

public interface ICargoService extends IGenericoCRUDManager<Cargo, Long> {

	public List<Cargo> pesquisarCargo(Cargo filtro, Integer first, Integer maxPerPage);

	public List<Cargo> pesquisarCargo(Cargo filtro);

	public void salvar(Cargo cargo) throws ValidacaoException;

}
