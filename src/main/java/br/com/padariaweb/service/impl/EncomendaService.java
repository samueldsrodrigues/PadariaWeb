package br.com.padariaweb.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IEncomendaDao;
import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.entity.EncomendaProduto;
import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.entity.VendaEncomenda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IEncomendaProdutoService;
import br.com.padariaweb.service.IEncomendaService;
import br.com.padariaweb.service.IFormaPagamentoService;
import br.com.padariaweb.service.IVendaEncomendaService;
import br.com.padariaweb.service.IVendaService;

@Service
@Transactional
public class EncomendaService extends GenericoCRUDManager<Encomenda, Long> implements IEncomendaService {

	@Autowired
	IEncomendaDao encomendaDao;

	@Autowired
	private IVendaService vendaService;

	@Autowired
	private IEncomendaProdutoService encomendaProdutoService;

	@Autowired
	private IVendaEncomendaService vendaEncomendaService;

	@Autowired
	private IFormaPagamentoService formaPagamentoService;

	@Override
	public List<Encomenda> pesquisarEncomenda(Encomenda filtro) {
		return encomendaDao.verificaEncomendaExistente(filtro);
	}

	@Override
	public List<Encomenda> pesquisarEncomenda(Encomenda filtro, Integer first, Integer maxPerPage) {

		return encomendaDao.pesquisarEncomenda(filtro, first, maxPerPage);
	}

	public void salvar(Encomenda encomenda) throws ValidacaoException {
		List<Encomenda> encomendas = encomendaDao.verificaEncomendaExistente(encomenda);
		// Caso seja inclusao de um novo encomenda
		if ((encomenda.getSqEncomenda() == null && !encomendas.isEmpty())
				// Caso seja alteracao de encomenda
				|| (encomenda.getSqEncomenda() != null && !encomendas.isEmpty()
						&& !encomenda.getSqEncomenda().equals(encomendas.get(0).getSqEncomenda())))
			throw new ValidacaoException("Encomenda já cadastrada na base de dados.");

		if (encomenda.getSqEncomenda() != null)
			encomendaDao.update(encomenda);
		else
			encomendaDao.save(encomenda);
	}

	public Encomenda pesquisarEncomenda(Long encomendaAlteracao) {
		Encomenda u = (Encomenda) encomendaDao.findById(Encomenda.class, encomendaAlteracao);
		encomendaDao.evict(u);
		return u;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void finalizarEncomenda(Encomenda encomenda, List<EncomendaProduto> itens, FormaPagamento pagamentoEntrada,
			Funcionario funcionario) throws ValidacaoException {

		if (encomenda == null) {
			throw new ValidacaoException("Encomenda é obrigatória.");
		}

		if (encomenda.getCliente() == null || encomenda.getCliente().getSqCliente() == null) {
			throw new ValidacaoException("Cliente é obrigatório.");
		}

		if (encomenda.getDtRetirada() == null) {
			throw new ValidacaoException("Data de retirada é obrigatória.");
		}

		if (funcionario == null || funcionario.getSqFuncionario() == null) {
			throw new ValidacaoException("Funcionário responsável é obrigatório.");
		}

		if (itens == null || itens.isEmpty()) {
			throw new ValidacaoException("Adicione pelo menos um item à encomenda.");
		}

		BigDecimal valorTotal = calcularTotal(itens);

		BigDecimal valorEntrada = encomenda.getValorEntrada();

		if (valorEntrada == null || valorEntrada.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidacaoException("O valor da entrada deve ser maior que zero.");
		}

		if (valorEntrada.compareTo(valorTotal) > 0) {
			throw new ValidacaoException("O valor da entrada não pode ser maior que o valor total.");
		}

		if (pagamentoEntrada == null || pagamentoEntrada.getTipo() == null
				|| pagamentoEntrada.getTipo().trim().isEmpty()) {
			throw new ValidacaoException("A forma de pagamento da entrada é obrigatória.");
		}

		/*
		 * 1. Salva a encomenda.
		 */
		encomenda.setDtEncomenda(new Date());
		encomenda.setValorTotal(valorTotal);
		encomenda.setStatus("PENDENTE");

		encomendaDao.save(encomenda);

		/*
		 * 2. Cria a venda financeira ligada à encomenda.
		 */
		Venda venda = new Venda();

		venda.setCliente(encomenda.getCliente());
		venda.setFuncionario(funcionario);
		venda.setDtVenda(new Date());
		venda.setVlTotal(valorTotal);

		if (valorEntrada.compareTo(valorTotal) == 0) {
			venda.setStatus("PAGA");
		} else {
			venda.setStatus("ABERTA");
		}

		vendaService.salvar(venda);

		/*
		 * 3. Salva os itens da encomenda.
		 */
		for (EncomendaProduto item : itens) {
			item.setEncomenda(encomenda);
			encomendaProdutoService.salvar(item);
		}

		/*
		 * 4. Liga a venda à encomenda.
		 */
		VendaEncomenda vendaEncomenda = new VendaEncomenda(venda, encomenda);

		vendaEncomendaService.salvar(vendaEncomenda);

		/*
		 * 5. Registra o pagamento da entrada.
		 */
		pagamentoEntrada.setVenda(venda);
		pagamentoEntrada.setValor(valorEntrada);

		formaPagamentoService.salvar(pagamentoEntrada);
	}

	public void inativarEncomenda(Encomenda encomendaInativar) {
		encomendaDao.save(encomendaInativar);

	}

	private BigDecimal calcularTotal(List<EncomendaProduto> itens) throws ValidacaoException {

		BigDecimal total = BigDecimal.ZERO;

		for (EncomendaProduto item : itens) {
			if (item == null) {
				throw new ValidacaoException("Existe um item inválido na encomenda.");
			}

			if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
				throw new ValidacaoException("A quantidade dos itens deve ser maior que zero.");
			}

			if (item.getPrecoUnitario() == null || item.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValidacaoException("O preço dos itens deve ser maior que zero.");
			}

			BigDecimal subtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));

			item.setSubtotal(subtotal);
			total = total.add(subtotal);
		}
		return total;
	}

}
