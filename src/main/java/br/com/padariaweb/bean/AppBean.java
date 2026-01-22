package br.com.padariaweb.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.service.IVendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Setter;

@SuppressWarnings("deprecation")
@SessionScoped
@ManagedBean
public class AppBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -3403916514148392387L;

	@ManagedProperty("#{funcionarioService}")
	private @Setter IFuncionarioService funcionarioService;

	@ManagedProperty("#{vendaService}")
	private @Setter IVendaService vendaService;

	private Funcionario funcionarioLogado;

	public String salvar() {
		return "SALVAR";
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String recuperarSenha() {
		return "/login";
	}

}
