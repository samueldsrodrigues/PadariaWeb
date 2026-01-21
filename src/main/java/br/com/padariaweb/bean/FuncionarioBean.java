package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.ICargoService;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.util.AbstractView;
import br.com.padariaweb.util.Util;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class FuncionarioBean extends AbstractView implements Serializable {

	@ManagedProperty("#{funcionarioService}")
	private @Setter IFuncionarioService funcionarioService;

	@ManagedProperty("#{cargoService}")
	private @Setter ICargoService cargoService;

//	@ManagedProperty("#{appBean}")
//	private @Setter AppBean appBean;

	private static final long serialVersionUID = 6362823659055280894L;

	private @Getter @Setter List<Funcionario> funcionarios;
	private @Getter @Setter List<Cargo> cargos;

	private @Getter @Setter Funcionario filtro;

	private @Getter @Setter Funcionario funcionarioSelecionado;

	/* Informações do Usuário Logado */
	private @Setter @Getter Long funcionarioSqCargo;

//	@SuppressWarnings("unused")
//	private static final String URL_PAGINA = "/pages/admin/funcionario/listar";
//	private static final String URL_PAGINA_INCLUIR = "/pages/admin/funcionario/incluir";
	
	@PostConstruct
	public void init() {
		limpar();
	}

	public void limpar() {
		filtro = new Funcionario();
		filtro.setCargo(new Cargo());
		funcionarioSelecionado = new Funcionario();

		try {
			cargos = cargoService.buscarTodos();
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pesquisar();
	}

	public void pesquisar() {
		if (Util.isValido(filtro)) {
			funcionarios = funcionarioService.pesquisarFuncionario(filtro, null, 500);
			if (Util.isValido(funcionarios)) {
				System.out.println("resultado :: " + funcionarios.toString());
			}

		}

	}

//	public void buscarCargos() {
//		lojas = lojaService.pesquisarCargoByGrupoCargo(filtroGrupoCargo);
//	}
//
//	private boolean isFuncionarioAdministrador(Funcionario u) {
//		for (Perfil perfil : u.getPerfis()) {
//			if (perfil.getSgPerfil().equals("ADMINISTRADOR"))
//				return true;
//		}
//		return false;
//	}
//
//	public void load() {
////			funcionarios.load();
//	}

	// Exclui usuário PERMANENTEMENTE
	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
//		if (funcionarioSelecionado.getIdFuncionario()
//				.compareTo(appBean.getFuncionarioLogado().getIdFuncionario()) == 0) {
//			context.addCallbackParam("retorno", "nOk");
//		} else {
		funcionarioService.excluir(funcionarioSelecionado);
		context.addCallbackParam("retorno", "ok");

	}

}
