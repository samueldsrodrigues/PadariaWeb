package br.com.padariaweb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IClienteService;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.service.IVendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class VendaBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -5954041217952634247L;

	@ManagedProperty("#{vendaService}")
	private @Setter IVendaService vendaService;
	
	@ManagedProperty("#{clienteService}")
	private @Setter IClienteService clienteService;

	@ManagedProperty("#{funcionarioService}")
	private @Setter IFuncionarioService funcionarioService;
	
	private @Getter @Setter List<Venda> vendas;
	private @Getter @Setter List<Funcionario> funcionarios;
	private @Getter @Setter List<Cliente> clientes;
	private @Getter @Setter Venda filtro;
	private @Getter @Setter Venda vendaSelecionada;
	private @Getter @Setter Date dtInicial;
	private @Getter @Setter Date dtFinal;
	private @Getter BigDecimal totalVendido;
	private @Getter Integer quantidadeVendas;
	private @Getter BigDecimal ticketMedio;

	@SuppressWarnings("unused")
	private static final String URL_PAGINA = "/pages/vendas/listar";
	private static final String URL_PAGINA_INCLUIR = "/pages/vendas/incluir";

	@PostConstruct
	public void init() {
		limpar();
		try {
			clientes = clienteService.buscarTodos();
			funcionarios = funcionarioService.buscarTodos();
		} catch (ValidacaoException e) {
			e.printStackTrace();
		}
		
	}

	public void limpar() {
		filtro = new Venda();
		dtInicial = null;
		dtFinal = null;
		vendaSelecionada = new Venda();
		pesquisar();
	}

//	public void pesquisar() {
//		vendas = vendaService.pesquisarVenda(filtro, dtInicial, dtFinal, null, 500);
//	}
	
	public void pesquisar() {
	    vendas = vendaService.pesquisarVenda(
	            filtro,
	            dtInicial,
	            dtFinal,
	            null,
	            500);

	    calcularResumo();
	}

	public String incluir() {
		return redirect(URL_PAGINA_INCLUIR);
	}

	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
		vendaService.cancelarVenda(vendaSelecionada);
		context.addCallbackParam("retorno", "ok");
	}
	
	public String visualizar() {
	    return redirect("/pages/vendas/detalhes?venda=" + vendaSelecionada.getSqVenda());
	}
	
	private void calcularResumo() {

		quantidadeVendas = 0;
		totalVendido = BigDecimal.ZERO;

		for (Venda venda : vendas) {

		    if (!"CANCELADA".equals(venda.getStatus())
		            && venda.getVlTotal() != null) {

		        quantidadeVendas++;

		        totalVendido = totalVendido.add(venda.getVlTotal());
		    }
		}

	    if (quantidadeVendas > 0) {
	    	ticketMedio = totalVendido.divide(
	    	        BigDecimal.valueOf(quantidadeVendas),
	    	        2,
	    	        RoundingMode.HALF_UP);
	    } else {
	        ticketMedio = BigDecimal.ZERO;
	    }
	}
}