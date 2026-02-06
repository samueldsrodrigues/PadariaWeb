package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.ICargoService;
import br.com.padariaweb.service.IProdutoService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ProdutoIncluirBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -1334154181303309954L;

	@ManagedProperty("#{appBean}")
	private @Setter AppBean appBean;

	@ManagedProperty("#{produtoService}")
	private @Setter IProdutoService produtoService;

	@ManagedProperty("#{cargoService}")
	private @Setter ICargoService cargoService;

	private @Getter @Setter Produto produto;

	private @Getter @Setter String senhaAtual;
	private @Getter @Setter String senha;
	private @Getter @Setter String confirmacaoSenha;

	private @Getter @Setter Long produtoAlteracao;

	private @Getter @Setter List<Cargo> cargos;

	private static final String URL_PAGINA = "/pages/produto/incluir";

	
	public String incluir() {
		return redirect(URL_PAGINA);
	}

	public String alterarProduto() {
		// produto = produtoService.pesquisarProduto(produtoAlteracao);

		return "/pages/produto/incluir";
	}

}
