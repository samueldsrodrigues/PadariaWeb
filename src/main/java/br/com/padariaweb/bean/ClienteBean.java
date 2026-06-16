package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IClienteService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ClienteBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -5954041217952634247L;

	@ManagedProperty("#{clienteService}")
	private @Setter IClienteService clienteService;

	private @Getter @Setter List<Cliente> clientes;

	private @Getter @Setter Cliente filtro;

	private @Getter @Setter Cliente clienteSelecionado;

	/* Informações do Usuário Logado */

	@SuppressWarnings("unused")
	private static final String URL_PAGINA = "/pages/clientes/listar";
	private static final String URL_PAGINA_INCLUIR = "/pages/clientes/incluir";
	
	@PostConstruct
	public void init() {
	    limpar();
	}
	
	public void limpar() {
		filtro = new Cliente();
		clienteSelecionado = new Cliente();
		pesquisar();
	}

	public String incluir() {
	    return redirect(URL_PAGINA_INCLUIR);
	}
	
	
	public String alterar() {
	    return redirect(URL_PAGINA_INCLUIR + "?clienteAlterar=" + clienteSelecionado.getSqCliente());
	}
	
	public void pesquisar() {
		clientes = clienteService.pesquisarCliente(filtro, null, 500);
	}

	// Exclui cliente PERMANENTEMENTE
	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
		clienteService.excluir(clienteSelecionado);
		context.addCallbackParam("retorno", "ok");

	}

}
