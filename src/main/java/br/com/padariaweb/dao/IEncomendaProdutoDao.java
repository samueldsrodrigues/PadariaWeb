package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.EncomendaProduto;

@Repository
public interface IEncomendaProdutoDao extends IGenericoCRUDDAO<EncomendaProduto, Long> {

    List<EncomendaProduto> pesquisarEncomendaProduto(EncomendaProduto filtro, Integer first, Integer maxPerPage);

}
