package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.padariaweb.entity.Encomenda;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IEncomendaService;
import br.com.padariaweb.util.AbstractView;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class EncomendaBean extends AbstractView implements Serializable {

	private static final long serialVersionUID = -5954041217952634247L;

	@ManagedProperty("#{encomendaService}")
	private @Setter IEncomendaService encomendaService;

	private @Getter @Setter List<Encomenda> encomendas;

	private @Getter @Setter Encomenda filtro;

	private @Getter @Setter Encomenda encomendaSelecionada;

	/* Informações do Usuário Logado */

//	@SuppressWarnings("unused")
//	private static final String URL_PAGINA = "/pages/admin/encomenda/listar";
//	private static final String URL_PAGINA_INCLUIR = "/pages/admin/encomenda/incluir";

	public void limpar() {
		filtro = new Encomenda();
		encomendaSelecionada = new Encomenda();
		pesquisar();
	}

	public void pesquisar() {
		encomendas = encomendaService.pesquisarEncomenda(filtro, null, 500);
	}

	// Exclui encomenda PERMANENTEMENTE
	public void remover() throws ValidacaoException {
		RequestContext context = RequestContext.getCurrentInstance();
		encomendaService.excluir(encomendaSelecionada);
		context.addCallbackParam("retorno", "ok");

	}

}
