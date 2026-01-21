package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.ICargoDao;
import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.ICargoService;

@Service
@Transactional
public class CargoService extends GenericoCRUDManager<Cargo, Long> implements ICargoService {

	@Autowired
	ICargoDao cargoDao;

	@Override
	public List<Cargo> pesquisarCargo(Cargo filtro) {
		return cargoDao.verificaCargoExistente(filtro);
	}

	@Override
	public List<Cargo> pesquisarCargo(Cargo filtro, Integer first, Integer maxPerPage) {

		return cargoDao.pesquisarCargo(filtro, first, maxPerPage);
	}

	public void salvar(Cargo cargo) throws ValidacaoException {
		List<Cargo> cargos = cargoDao.verificaCargoExistente(cargo);
		// Caso seja inclusao de um novo cargo
		if ((cargo.getSqCargo() == null && !cargos.isEmpty())
				// Caso seja alteracao de cargo
				|| (cargo.getSqCargo() != null && !cargos.isEmpty()
						&& !cargo.getSqCargo().equals(cargos.get(0).getSqCargo())))
			throw new ValidacaoException("Cargo já cadastrado na base de dados.");

		

		if (cargo.getSqCargo() != null)
			cargoDao.update(cargo);
		else
			cargoDao.save(cargo);
	}

	public Cargo pesquisarCargo(Long cargoAlteracao) {
		Cargo u = (Cargo) cargoDao.findById(Cargo.class, cargoAlteracao);
		cargoDao.evict(u);
		return u;
	}

	public void inativarCargo(Cargo cargoInativar) {
		cargoDao.save(cargoInativar);

	}

}
