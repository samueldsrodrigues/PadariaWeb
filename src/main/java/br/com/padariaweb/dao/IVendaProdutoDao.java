package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.VendaProduto;

@Repository
public interface IVendaProdutoDao extends IGenericoCRUDDAO<VendaProduto, Long> {

    List<VendaProduto> pesquisarVendaProduto(VendaProduto filtro, Integer first, Integer maxPerPage);

}
