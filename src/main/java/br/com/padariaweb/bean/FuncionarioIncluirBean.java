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
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.exception.ValidacaoException;
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

	private static final String URL_PAGINA = "/pages/funcionario/incluir";

	@PostConstruct
	public void init() {
		//		Funcionario f = (Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("funcionarioLogado");
		//		cargos = cargoService.pesquisarTodosCargos(isFuncionarioAdministrador(u));
		//		
		//		//check FuncionarioLogado is ROOT
		//		for(Cargo c : f.getCargo()){
		//			if(c.getSqCargo().equals("ROOT")){
		//				
		//			}
		//		}

		//Alterar Usuário
		if(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("funcionarioAlterar") != null){
			String funcionarioAlteracao = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("funcionarioAlterar");
			funcionario = funcionarioService.pesquisarFuncionario(new Long(funcionarioAlteracao));
			List<Cargo> perfisAlteracao = funcionario.getCargos();
			funcionario.setCargos(new ArrayList<>());
			for (Cargo cargo : cargos) {
				for (Cargo cargoAlteracao : perfisAlteracao) {
					if(cargo.getSqCargo().equals(cargoAlteracao.getSqCargo()))
						funcionario.getCargo().add(cargo);
				}
			}

		}else{
			funcionario = new Funcionario();
		}
	}

	public String incluir() {
		return redirect(URL_PAGINA);
	}

	public String salvar() {
		try {
			if (funcionario.getSqFuncionario() == null) {
				if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
					addMsgError("Campo Nome é obrigatório");
					return null;
				}
				if (funcionario.getCargo() == null) {
					addMsgError("Campo Cargo é obrigatório");
					return null;
				}
				if (!validEmailEasy(funcionario.getEmail())) {
					addMsgError("Campo E-mail é inválido");
					return null;
				}
				funcionarioService.salvar(funcionario);
				addMsgInfo("Usuário salvo com sucesso");
			} else {
				if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
					addMsgError("Campo Nome é obrigatório");
					return null;
				}
				if (!validEmailEasy(funcionario.getEmail())) {
					addMsgError("Campo E-mail é inválido");
					return null;
				}
				funcionarioService.salvar(funcionario);
				addMsgInfo("Usuário alterado com sucesso");
			}
			return "listar";
		} catch (ValidacaoException e) {
			addMsgError(e.getMessage());
			return null;
		}
	}

	// public void carregarListaLoja(){
	// if(!Util.isValido(funcionario)){
	// lojas =
	// lojaService.pesquisarLojaByGrupoLoja(appBean.getFuncionarioLogado().getGrupoLoja());
	// }else if(Util.isValido(funcionario.getGrupoLoja())){
	// lojas = lojaService.pesquisarLojaByGrupoLoja(funcionario.getGrupoLoja());
	// }
	// }

	public boolean validEmail(String email) {
		System.out.println("Metodo de validacao de email");
		// Pattern p =
		// Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+([_A-Za-z0-9-])$";
		Matcher m = Pattern.compile(regex).matcher(email.toLowerCase());
		if (m.find()) {
			System.out.println("O email " + email + " e valido");
			return true;
		} else {
			System.out.println("O E-mail " + email + " é inválido");
			return false;
		}
	}

	public boolean validEmailEasy(String email) {
		if (email.trim().contains("@")) {
			return true;
		} else {
			return false;
		}
	}

	// public String salvarPrimeiroAcesso() {
	// if (senhaAtual == null || senhaAtual.isEmpty()) {
	// addMsgError("Campo Senha Atual é obrigatório");
	// return null;
	// }
	// if (senha == null || senha.isEmpty()) {
	// addMsgError("Campo Nova Senha é obrigatório");
	// return null;
	// }
	// if (confirmacaoSenha == null || confirmacaoSenha.isEmpty()) {
	// addMsgError("Campo Confirmar Senha é obrigatório");
	// return null;
	// }
	//
	// if (!senha.equals(confirmacaoSenha)) {
	// addMsgError("A Nova Senha e a confirmação não são iguais.");
	// return null;
	// }
	//
	//
	// // invalidating session
	// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	//
	// return "/login?faces-redirect=true";
	// }

	public String alterarFuncionario() {
		// funcionario = funcionarioService.pesquisarFuncionario(funcionarioAlteracao);

		return "/pages/funcionario/incluir";
	}

}
