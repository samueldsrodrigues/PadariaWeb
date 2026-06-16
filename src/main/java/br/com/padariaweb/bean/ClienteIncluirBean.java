package br.com.padariaweb.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.padariaweb.entity.Cliente;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IClienteService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ClienteIncluirBean extends AbstractView implements Serializable {

    private static final long serialVersionUID = -1334154181303309954L;

    @ManagedProperty("#{clienteService}")
    private @Setter IClienteService clienteService;

    private @Getter @Setter Cliente cliente;

    private static final String URL_LISTAR = "/pages/clientes/listar";

    @PostConstruct
    public void init() {
        try {
            String param = ((HttpServletRequest) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest())
                    .getParameter("clienteAlterar");

            if (param != null && !param.trim().isEmpty() && !"null".equals(param)) {
                cliente = clienteService.buscarPeloId(Long.valueOf(param));
            } else {
                cliente = new Cliente();
            }

        } catch (ValidacaoException e) {
            addMsgError("Erro ao carregar cliente");
            cliente = new Cliente();
        }
    }

    public String salvar() {
        try {
            if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
                addMsgError("Campo Nome é obrigatório");
                return null;
            }

            if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
                addMsgError("Campo CPF é obrigatório");
                return null;
            }
            
            if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
                addMsgError("Campo Telefone é obrigatório");
                return null;
            }

            clienteService.salvar(cliente);
            addMsgInfo("Cliente salvo com sucesso");

            return URL_LISTAR + "?faces-redirect=true";

        } catch (ValidacaoException e) {
            addMsgError(e.getMessage());
            return null;
        }
    }
}