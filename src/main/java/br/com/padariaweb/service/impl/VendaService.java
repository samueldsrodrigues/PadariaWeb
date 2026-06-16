package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IFormaPagamentoDao;
import br.com.padariaweb.dao.IProdutoDao;
import br.com.padariaweb.dao.IVendaDao;
import br.com.padariaweb.dao.IVendaProdutoDao;
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

	public List<Venda> pesquisarVenda(Venda filtro, Date dtInicial, Date dtFinal, Integer first, Integer maxPerPage) {
	    
		if (dtFinal != null) {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(dtFinal);
		    calendar.set(Calendar.HOUR_OF_DAY, 23);
		    calendar.set(Calendar.MINUTE, 59);
		    calendar.set(Calendar.SECOND, 59);
		    calendar.set(Calendar.MILLISECOND, 999);

		    dtFinal = calendar.getTime();
		}
		
		return vendaDao.pesquisarVenda(filtro, dtInicial, dtFinal, first, maxPerPage);
	}

	public void salvar(Venda venda) throws ValidacaoException {
		if (venda.getDtVenda() == null) {
			venda.setDtVenda(new Date());
		}

		if (venda.getVlTotal() == null) {
			venda.setVlTotal(BigDecimal.ZERO);
		}

		if (venda.getStatus() == null || venda.getStatus().trim().isEmpty()) {
			venda.setStatus("ATIVA");
		}

		if (venda.getSqVenda() != null)
			vendaDao.update(venda);
		else
			vendaDao.save(venda);
	}

	@Override
	public void cancelarVenda(Venda venda) throws ValidacaoException {

		if (venda == null || venda.getSqVenda() == null) {
			throw new ValidacaoException("Venda inválida para cancelamento.");
		}

		if (venda.getStatus() != null && "CANCELADA".equalsIgnoreCase(venda.getStatus().trim())) {
			throw new ValidacaoException("Venda já está cancelada.");
		}

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
		}

		venda.setStatus("CANCELADA");
		vendaDao.update(venda);
	}
}
