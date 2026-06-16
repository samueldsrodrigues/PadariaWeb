package br.com.padariaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.padariaweb.entity.FormaPagamento;

@Repository
public interface IFormaPagamentoDao extends IGenericoCRUDDAO<FormaPagamento, Long> {

    List<FormaPagamento> pesquisarFormaPagamento(FormaPagamento filtro, Integer first, Integer maxPerPage);

}
