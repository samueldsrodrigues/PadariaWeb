package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.VendaEncomenda;

@Repository
public interface IVendaEncomendaDao extends IGenericoCRUDDAO<VendaEncomenda, Long> {

    List<VendaEncomenda> pesquisarVendaEncomenda(VendaEncomenda filtro, Integer first, Integer maxPerPage);

}
