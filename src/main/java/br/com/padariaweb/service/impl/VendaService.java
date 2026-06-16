package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IFormaPagamentoDao;
import br.com.padariaweb.dao.IProdutoDao;
import br.com.padariaweb.dao.IVendaDao;
import br.com.padariaweb.dao.IVendaProdutoDao;
import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.entity.VendaProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IVendaService;

@Service
@Transactional
public class VendaService extends GenericoCRUDManager<Venda, Long> implements IVendaService {

	@Autowired
    private IVendaDao vendaDao;
	
	@Autowired
    private IVendaProdutoDao vendaProdutoDao;
	
	@Autowired
    private IFormaPagamentoDao formaPagamentoDao;
	
	@Autowired
	private IProdutoDao produtoDao;

    public List<Venda> pesquisarVenda(Venda filtro, Integer first, Integer maxPerPage) {
        return vendaDao.pesquisarVenda(filtro, first, maxPerPage);
    }

    public void salvar(Venda venda) throws ValidacaoException {
        if (venda.getDtVenda() == null) {
            venda.setDtVenda(new Date());
        }

        if (venda.getVlTotal() == null) {
            venda.setVlTotal(BigDecimal.ZERO);
        }

        if (venda.getSqVenda() != null)
            vendaDao.update(venda);
        else
            vendaDao.save(venda);
    }

	@Override
	public void excluirVendaCompleta(Venda venda) throws ValidacaoException {

	    VendaProduto filtroItem = new VendaProduto();
	    filtroItem.setVenda(venda);

	    List<VendaProduto> itens = vendaProdutoDao.pesquisarVendaProduto(filtroItem, null, 500);

	    for (VendaProduto item : itens) {
	    	Produto produto = item.getProduto();

	        if (produto.getEstoque() == null) {
	            produto.setEstoque(0);
	        }

	        produto.setEstoque(produto.getEstoque() + item.getQuantidade());

	        produtoDao.update(produto);

	        vendaProdutoDao.delete(item);
	    }

	    FormaPagamento filtroPagamento = new FormaPagamento();
	    filtroPagamento.setVenda(venda);

	    List<FormaPagamento> pagamentos = formaPagamentoDao.pesquisarFormaPagamento(filtroPagamento, null, 500);

	    for (FormaPagamento pagamento : pagamentos) {
	        formaPagamentoDao.delete(pagamento);
	    }

	    vendaDao.delete(venda);
	}
}


