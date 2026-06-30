package br.com.padariaweb.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.getPrincipal() instanceof Funcionario) {
	        return (Funcionario) authentication.getPrincipal();
	    }

	    return null;
	}
	
	public boolean isGerente() {
	    Funcionario funcionario = getFuncionarioLogado();

	    return funcionario != null
	            && funcionario.getCargo() != null
	            && "Gerente".equalsIgnoreCase(funcionario.getCargo().getNome());
	}

	public boolean isCaixa() {
	    Funcionario funcionario = getFuncionarioLogado();

	    return funcionario != null
	            && funcionario.getCargo() != null
	            && "Caixa".equalsIgnoreCase(funcionario.getCargo().getNome());
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String recuperarSenha() {
		return "/login";
	}

}
