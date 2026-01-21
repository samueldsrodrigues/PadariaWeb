package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Encomenda;

@Repository
public interface IEncomendaDao extends IGenericoCRUDDAO<Encomenda, Long> {

	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage);

	public List<Encomenda> verificaEncomendaExistente(Encomenda filtro);

}
