package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.service.ICargoService;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class FuncionarioIncluirBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -1334154181303309954L;

	@ManagedProperty("#{appBean}")
	private @Setter AppBean appBean;

	@ManagedProperty("#{funcionarioService}")
	private @Setter IFuncionarioService funcionarioService;

	@ManagedProperty("#{cargoService}")
	private @Setter ICargoService cargoService;


	private @Getter @Setter Funcionario funcionario;

	private @Getter @Setter String senhaAtual;
	private @Getter @Setter String senha;
	private @Getter @Setter String confirmacaoSenha;

	private @Getter @Setter Long funcionarioAlteracao;

	private @Getter @Setter List<Cargo> cargos;

	private static final String URL_PAGINA = "/pages/admin/funcionario/incluir";

	private @Getter @Setter boolean exibirFileUpload = false;
	private @Getter @Setter boolean exibirWebCam = false;
	private @Getter @Setter boolean exibirGrupoLojas = false;

	@PostConstruct
	public void init() {
		
		
	}

	public String incluir() {
		return redirect(URL_PAGINA);
	}

	public String salvar() {
		// Se o FuncionarioLogado não for ROOT então o GrupoLoja do Funcionario a ser incluído
		
			return "listar";
		
	}

	public String salvarPrimeiroAcesso() {
		if (senhaAtual == null || senhaAtual.isEmpty()) {
			addMsgError("Campo Senha Atual é obrigatório");
			return null;
		}
		if (senha == null || senha.isEmpty()) {
			addMsgError("Campo Nova Senha é obrigatório");
			return null;
		}
		if (confirmacaoSenha == null || confirmacaoSenha.isEmpty()) {
			addMsgError("Campo Confirmar Senha é obrigatório");
			return null;
		}

		if (!senha.equals(confirmacaoSenha)) {
			addMsgError("A Nova Senha e a confirmação não são iguais.");
			return null;
		}

		
		// invalidating session
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/login?faces-redirect=true";
	}




	public String alterarFuncionario() {
		//funcionario = funcionarioService.pesquisarFuncionario(funcionarioAlteracao);

		return "/pages/admin/funcionario/incluir";
	}

}
