package br.com.padariaweb.bean.consultas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.padariaweb.util.AbstractView;

/**
 * Classe responsável pelo módulo CONSULTAS
 * */
@SessionScoped
@ManagedBean
public class ConsultasListarBean extends AbstractView {

	private static final long serialVersionUID = -1089314759739289519L;

	/**
	 *Construtor do bean 
	 **/
	public String init(){
		return redirect("/pages/consultas/listar");
	}
	
	/**
	 * Redirects
	 * */
	public String redirectFuncionarios(){
		return redirect("/pages/funcionarios/listar");
		/*return "pretty:usuario-listar";*/
	}
	
	public String redirectInicio(){
		return redirect("/index");
		/*return "pretty:loja-listar";*/
	}
	
	public String redirectGrupoLojas(){
		return redirect("/pages/admin/grupo-loja/listar");
		/*return "pretty:grupo-loja-listar";*/
	}
	
	public String redirectVisitas(){
		return redirect("/pages/visitas/listar");
		/*return "pretty:visita-listar";*/
	}
	
	public String redirectCriancas(){
		return redirect("/pages/crianca/listar");
		/*return "pretty:crianca-listar";*/
	}
	
	public String redirectEventos(){
		return redirect("/pages/eventos/listar");
		/*return "pretty:evento-listar";*/
	}
	
	public String redirectPacotes(){
		return redirect("/pages/pacotes/listar");
		/*return "pretty:pacote-listar";*/
	}
	
	public String redirectAuditoria(){
		return redirect("/pages/auditoria/listar");
		/*return "pretty:auditoria-listar";*/
	}
	
	public String redirectResponsaveis(){
		return redirect("/pages/responsavel/listar");
		/*return "pretty:responsavel-listar";*/
	}
	
	/*Consultas*/
	
	public String redirectVisitasHora(){
		return redirect("/pages/consultas/visitas-hora");
	/*	return "pretty:visita-hora-listar";*/
	}
	
	public String redirectVisitasConvenio(){
		return redirect("/pages/consultas/visitas-convenio");
		/*return "pretty:visita-convenio-listar";*/
	}
	
	public String redirectVisitasHistorico(){
		return redirect("/pages/consultas/visitas-historico");
		/*return "pretty:visita-historico-listar";*/
	}
	
	public String redirectVisitasIdade(){
		return redirect("/pages/consultas/visitas-idade");
		/*return "pretty:visita-idade-listar";*/
	}
	
	public String redirectVisitasGenero(){
		return redirect("/pages/consultas/visitas-genero");
		/*return "pretty:visita-genero-listar";*/
	}
	
	public String  redirectVisitasLocalidade() {
		return redirect("/pages/visitas/visitas-localidade");
	}
	
	public String redirectArrecadacaoVisitas(){
		return redirect("/pages/consultas/arrecadacao-visitas");
		/*return "pretty:arrecadacao-visita-listar";*/
	}
	
	public String redirectFechamentoCaixa(){
		return redirect("/pages/consultas/fechamento-caixa");
		/*return "pretty:fechamento-caixa-listar";*/
	}
	
	public String redirectDetalhamentoCaixa() {
		return redirect("/pages/consultas/detalhamento-caixa");
	}
	
	public String redirectReceitas(){
		return redirect("/pages/consultas/receitas");
		/*return "pretty:receita-listar";*/
	}
	
	public String redirectVisitasFrequencia(){
		return redirect("/pages/consultas/visitas-frequencia");
	}
	
	public String redirectProdutosFrequencia() {
		return redirect("/pages/consultas/produtos-frequencia");
	}
	
	public String redirectLancamentoCaixa() {
		return redirect("/pages/consultas/caixa-lancamentos");
	}
	
	public String redirectCriancasPacote() {
		return redirect("/pages/consultas/crianca-pacote");
	}
	
	public String redirectEstoque() {
		return redirect("/pages/estoque/listar");
	}
	
	public String redirectVendas() {
		return redirect("/pages/vendas/listar");
	}
	
	public String redirectHistoricoPacotes() {
		return redirect("/pages/pacotes/auditoria-cadastro");
	}
	
	public String redirectConfValores() {
		return redirect("/pages/auditoria/auditoria-config-valores");
	}
	
	public String redirectHistoricoVendaPacotes() {
		return redirect("/pages/pacotes/historico-vendas");
	}
}
