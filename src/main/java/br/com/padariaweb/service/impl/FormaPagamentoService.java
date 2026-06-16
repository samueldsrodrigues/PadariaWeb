package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IFormaPagamentoDao;
import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IFormaPagamentoService;

@Service
@Transactional
public class FormaPagamentoService extends GenericoCRUDManager<FormaPagamento, Long> implements IFormaPagamentoService {

    @Autowired
    private IFormaPagamentoDao formaPagamentoDao;

    public List<FormaPagamento> pesquisarFormaPagamento(FormaPagamento filtro, Integer first, Integer maxPerPage) {
        return formaPagamentoDao.pesquisarFormaPagamento(filtro, first, maxPerPage);
    }

    public void salvar(FormaPagamento formaPagamento) throws ValidacaoException {

        if (formaPagamento.getVenda() == null) {
            throw new ValidacaoException("Venda é obrigatória.");
        }

        String tipo = formaPagamento.getTipo();

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new ValidacaoException("Tipo é obrigatório.");
        }

        tipo = tipo.trim();

        if ("Crédito".equalsIgnoreCase(tipo)
                && (formaPagamento.getQtdParcelas() == null || formaPagamento.getQtdParcelas() <= 0)) {
            throw new ValidacaoException("Quantidade de parcelas deve ser maior que zero para pagamento em crédito.");
        }

        if ("Crédito".equalsIgnoreCase(formaPagamento.getTipo())
                && (formaPagamento.getQtdParcelas() == null || formaPagamento.getQtdParcelas() <= 0)) {
            throw new ValidacaoException("Quantidade de parcelas deve ser maior que zero para pagamento em crédito.");
        }

        if (formaPagamento.getValor() == null) {
            throw new ValidacaoException("Valor é obrigatório.");
        }

        if (formaPagamento.getSqFormaPagamento() != null)
            formaPagamentoDao.update(formaPagamento);
        else
            formaPagamentoDao.save(formaPagamento);
    }
}