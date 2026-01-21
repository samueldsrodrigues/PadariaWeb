package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IProdutoService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ProdutoBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -5954041217952634247L;

	@ManagedProperty("#{produtoService}")
	private @Setter IProdutoService produtoService;

	private @Getter @Setter List<Produto> produtos;

	private @Getter @Setter Produto filtro;

	private @Getter @Setter Produto produtoSelecionado;

	/* Informações do Usuário Logado */

//	@SuppressWarnings("unused")
//	private static final String URL_PAGINA = "/pages/admin/produto/listar";
//	private static final String URL_PAGINA_INCLUIR = "/pages/admin/produto/incluir";

	public void limpar() {
		filtro = new Produto();
		produtoSelecionado = new Produto();
		pesquisar();
	}

	public void pesquisar() {
		produtos = produtoService.pesquisarProduto(filtro, null, 500);
	}

	// Exclui produto PERMANENTEMENTE
	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
		produtoService.excluir(produtoSelecionado);
		context.addCallbackParam("retorno", "ok");

	}

}
