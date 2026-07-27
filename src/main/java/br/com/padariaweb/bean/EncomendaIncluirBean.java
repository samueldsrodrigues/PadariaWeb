package br.com.padariaweb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.entity.ValorProduto;
import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.entity.EncomendaProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IClienteService;
import br.com.padariaweb.service.IFormaPagamentoService;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.service.IProdutoService;
import br.com.padariaweb.service.IValorProdutoService;
import br.com.padariaweb.service.IEncomendaProdutoService;
import br.com.padariaweb.service.IEncomendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class EncomendaIncluirBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -1334154181303309954L;

	@ManagedProperty("#{appBean}")
	private @Setter AppBean appBean;
	
	@ManagedProperty("#{produtoService}")
	private @Setter IProdutoService produtoService;
	
	@ManagedProperty("#{valorProdutoService}")
	private @Setter IValorProdutoService valorProdutoService;
	
	@ManagedProperty("#{encomendaService}")
	private @Setter IEncomendaService encomendaService;

	@ManagedProperty("#{encomendaProdutoService}")
	private @Setter IEncomendaProdutoService encomendaProdutoService;
	
	@ManagedProperty("#{funcionarioService}")
	private @Setter IFuncionarioService funcionarioService;
	
	@ManagedProperty("#{clienteService}")
	private @Setter IClienteService clienteService;
	
	@ManagedProperty("#{formaPagamentoService}")
	private @Setter IFormaPagamentoService formaPagamentoService;

	private @Getter @Setter Encomenda encomenda;
	private @Getter @Setter List<EncomendaProduto> itens;
	private @Getter @Setter EncomendaProduto item;
	private @Getter @Setter List<Produto> produtos;
	private @Getter @Setter List<Cliente> clientes;
	private @Getter @Setter Produto produtoSelecionado;
	private @Getter @Setter Integer quantidade;
	private @Getter @Setter BigDecimal total;
	private @Getter @Setter FormaPagamento formaPagamento;
	
	private static final String URL_PAGINA = "/pages/encomendas/incluir";

	@PostConstruct
	public void init() {
	    encomenda = new Encomenda();
	    itens = new ArrayList<>();
	    item = new EncomendaProduto();
	    total = BigDecimal.ZERO;
	    formaPagamento = new FormaPagamento();

	    try {
	        produtos = produtoService.buscarTodos();
	        clientes = clienteService.buscarTodos();
	    } catch (ValidacaoException e) {
	        addMsgError("Erro ao carregar produtos.");
	    }
	}
	
	public String incluir() {
		return redirect(URL_PAGINA);
	}

	public String adicionarProduto() {
	    if (produtoSelecionado == null || produtoSelecionado.getSqProduto() == null) {
	        addMsgError("É preciso selecionar um produto.");
	        return null;
	    }

	    if (quantidade == null || quantidade <= 0) {
	        addMsgError("Quantidade deve ser maior que zero.");
	        return null;
	    }

	    EncomendaProduto item = new EncomendaProduto();
	    item.setProduto(produtoSelecionado);
	    item.setQuantidade(quantidade);

	    ValorProduto valorProduto = valorProdutoService.buscarPorProduto(produtoSelecionado.getSqProduto());

	    if (valorProduto == null) {
	        addMsgError("Produto sem preço cadastrado.");
	        return null;
	    }

	    BigDecimal desconto = valorProduto.getDesconto() == null ? BigDecimal.ZERO : valorProduto.getDesconto();

	    BigDecimal precoUnitario = valorProduto.getPreco().subtract(desconto);
	    BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));

	    item.setPrecoUnitario(precoUnitario);
	    item.setSubtotal(subtotal);

	    itens.add(item);

	    calcularTotal();

	    produtoSelecionado = null;
	    quantidade = null;

	    return null;
	}
	
	public String finalizarEncomenda() {
	    try {
	        if (itens == null || itens.isEmpty()) {
	            addMsgError("Adicione pelo menos um produto na encomenda.");
	            return null;
	        }

	        encomenda.setDtEncomenda(new Date());
//	        encomenda.setVlTotal(total);

	        // Por enquanto, vamos pegar um funcionário fixo.
	        // Depois trocamos pelo funcionário logado.
	        // encomenda.setFuncionario(appBean.getFuncionarioLogado());

	        Funcionario funcionario = funcionarioService.buscarPeloId(1L);
//	        encomenda.setFuncionario(funcionario);
	        
	        encomendaService.salvar(encomenda);

	        for (EncomendaProduto item : itens) {
	            item.setEncomenda(encomenda);
	            encomendaProdutoService.salvar(item);

	            Produto produto = item.getProduto();

	            if (produto.getEstoque() == null) {
	                produto.setEstoque(0);
	            }

	            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
	            produtoService.salvar(produto);
	        }
	        
//	        formaPagamento.setEncomenda(encomenda);
	        formaPagamento.setValor(total);

	        formaPagamentoService.salvar(formaPagamento);

	        addMsgInfo("Encomenda finalizada com sucesso.");

	        return "/pages/encomendas/incluir?faces-redirect=true";

	    } catch (ValidacaoException e) {
	        addMsgError(e.getMessage());
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        addMsgError("Erro ao finalizar encomenda.");
	        return null;
	    } 
	}
	
	private void calcularTotal() {
	    total = BigDecimal.ZERO;

	    for (EncomendaProduto item : itens) {
	        if (item.getSubtotal() != null) {
	            total = total.add(item.getSubtotal());
	        }
	    }
	}
	
	public void removerItem(EncomendaProduto item) {
	    itens.remove(item);
	    calcularTotal();
	}

}
