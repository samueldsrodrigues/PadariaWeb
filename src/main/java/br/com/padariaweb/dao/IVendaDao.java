package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.Venda;

@Repository
public interface IVendaDao extends IGenericoCRUDDAO<Venda, Long> {

    List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage);

    List<Venda> verificaVendaExistente(Venda filtro);
}
