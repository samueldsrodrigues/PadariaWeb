package br.com.padariaweb.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.padariaweb.entity.ValorProduto;
import br.com.padariaweb.service.IValorProdutoService;
import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IProdutoService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ProdutoIncluirBean extends AbstractView implements Serializable {

    private static final long serialVersionUID = -1334154181303309954L;

    @ManagedProperty("#{produtoService}")
    private @Setter IProdutoService produtoService;
    
    @ManagedProperty("#{valorProdutoService}")
    private @Setter IValorProdutoService valorProdutoService;

    private @Getter @Setter Produto produto;
    private @Getter @Setter ValorProduto valorProduto;

    private static final String URL_LISTAR = "/pages/produtos/listar";

    @PostConstruct
    public void init() {
        try {
            String param = ((HttpServletRequest) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest())
                    .getParameter("produtoAlterar");

            if (param != null && !param.trim().isEmpty() && !"null".equals(param)) {
                produto = produtoService.buscarPeloId(Long.valueOf(param));
            } else {
                produto = new Produto();
            }

            if (produto.getSqProduto() != null) {
                valorProduto = valorProdutoService.buscarPorProduto(produto.getSqProduto());
            }

            if (valorProduto == null) {
                valorProduto = new ValorProduto();
            }
        } catch (ValidacaoException e) {
            addMsgError("Erro ao carregar produto");
            produto = new Produto();
        }
    }

    public String salvar() {
        try {
            if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
                addMsgError("Campo Nome é obrigatório");
                return null;
            }

            if (produto.getTipo() == null || produto.getTipo().trim().isEmpty()) {
                addMsgError("Campo Tipo é obrigatório");
                return null;
            }

            produtoService.salvar(produto);
            valorProduto.setProduto(produto);
            valorProdutoService.salvar(valorProduto);
            addMsgInfo("Produto salvo com sucesso");

            return URL_LISTAR + "?faces-redirect=true";

        } catch (ValidacaoException e) {
            addMsgError(e.getMessage());
            return null;
        }
    }
}