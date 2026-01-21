package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.exception.ValidacaoException;
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

	private @Getter @Setter List<Venda> vendas;

	private @Getter @Setter Venda filtro;

	private @Getter @Setter Venda vendaSelecionada;

	/* Informações do Usuário Logado */

//	@SuppressWarnings("unused")
//	private static final String URL_PAGINA = "/pages/admin/venda/listar";
//	private static final String URL_PAGINA_INCLUIR = "/pages/admin/venda/incluir";

	public void limpar() {
		filtro = new Venda();
		vendaSelecionada = new Venda();
		pesquisar();
	}

	public void pesquisar() {
		vendas = vendaService.pesquisarVenda(filtro, null, 500);
	}

	// Exclui venda PERMANENTEMENTE
	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
		vendaService.excluir(vendaSelecionada);
		context.addCallbackParam("retorno", "ok");

	}

}
