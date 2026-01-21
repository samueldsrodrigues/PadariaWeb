package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Venda;

@Repository
public interface IVendaDao extends IGenericoCRUDDAO<Venda, Long> {

	public List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage);

	public List<Venda> verificaVendaExistente(Venda filtro);

}
