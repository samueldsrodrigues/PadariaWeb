package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.padariaweb.entity.FormaPagamento;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.entity.VendaProduto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IFormaPagamentoService;
import br.com.padariaweb.service.IVendaProdutoService;
import br.com.padariaweb.service.IVendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class VendaDetalhesBean extends AbstractView implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{vendaService}")
    private @Setter IVendaService vendaService;

    @ManagedProperty("#{vendaProdutoService}")
    private @Setter IVendaProdutoService vendaProdutoService;

    @ManagedProperty("#{formaPagamentoService}")
    private @Setter IFormaPagamentoService formaPagamentoService;

    private @Getter @Setter Venda venda;
    private @Getter @Setter List<VendaProduto> itensVenda;
    private @Getter @Setter List<FormaPagamento> pagamentosVenda;

    @PostConstruct
    public void init() {
        try {
            String param = ((HttpServletRequest) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest())
                    .getParameter("venda");

            if (param == null || param.trim().isEmpty()) {
                addMsgError("Venda não informada.");
                venda = new Venda();
                return;
            }

            Long sqVenda = Long.valueOf(param);

            venda = vendaService.buscarPeloId(sqVenda);

            VendaProduto filtroItem = new VendaProduto();
            filtroItem.setVenda(venda);
            itensVenda = vendaProdutoService.pesquisarVendaProduto(filtroItem, null, 500);

            FormaPagamento filtroPagamento = new FormaPagamento();
            filtroPagamento.setVenda(venda);
            pagamentosVenda = formaPagamentoService.pesquisarFormaPagamento(filtroPagamento, null, 500);

        } catch (ValidacaoException e) {
            addMsgError("Erro ao carregar venda.");
            venda = new Venda();
        } catch (Exception e) {
            e.printStackTrace();
            addMsgError("Erro inesperado ao carregar venda.");
            venda = new Venda();
        }
    }

    public String voltar() {
        return redirect("/pages/vendas/listar");
    }
}