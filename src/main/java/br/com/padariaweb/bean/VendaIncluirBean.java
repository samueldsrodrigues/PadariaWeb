package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.padariaweb.entity.Cargo;
import br.com.padariaweb.entity.Venda;
import br.com.padariaweb.service.ICargoService;
import br.com.padariaweb.service.IVendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class VendaIncluirBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -1334154181303309954L;

	@ManagedProperty("#{appBean}")
	private @Setter AppBean appBean;

	@ManagedProperty("#{vendaService}")
	private @Setter IVendaService vendaService;

	@ManagedProperty("#{cargoService}")
	private @Setter ICargoService cargoService;

	private @Getter @Setter Venda venda;

	private @Getter @Setter String senhaAtual;
	private @Getter @Setter String senha;
	private @Getter @Setter String confirmacaoSenha;

	private @Getter @Setter Long vendaAlteracao;

	private @Getter @Setter List<Cargo> cargos;

	private static final String URL_PAGINA = "/pages/venda/incluir";

	
	public String incluir() {
		return redirect(URL_PAGINA);
	}

	public String alterarVenda() {
		// venda = vendaService.pesquisarVenda(vendaAlteracao);

		return "/pages/venda/incluir";
	}

}
