package br.com.padariaweb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Venda;

@Repository
public interface IVendaDao extends IGenericoCRUDDAO<Venda, Long> {

	public List<Venda> pesquisarVenda(Venda filtro, Date dtInicial, Date dtFinal, Integer first, Integer maxPerPage);

	List<Venda> verificaVendaExistente(Venda filtro);
}
