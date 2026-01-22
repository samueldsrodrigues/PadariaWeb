package br.com.padariaweb.util;


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


/**
 * Classe suporte a todas as actions do projeto.
 * <p>
 * Possui uma série de métodos que facilitam o desenvolvimento 
 * como adição de mensagens no FacesMessage, download de arquivos, etc.
 */
@SuppressWarnings("serial")
public abstract class AbstractView implements Serializable {
	
	/**
     * Retorna a referencia do FacesContext.
     * @return HttpServletResponse
     */
    protected FacesContext context(){
        return FacesContext.getCurrentInstance();
    }
	
	/**
     * Adiciona genericamente uma mensagem do bundle na view.
     * Se a mensagem do bundle não for encontrada, adiciona a própria mensagem.
     * 
     * @param severity - severidade da mensagem
     * @param mensagem - chave da mensagem no bundle ou a própria mensagem 
     * @param parametros - parâmetros a substituir na mensagem
     */
	private void addMsg(Severity severity, String mensagem, Object... parametros) {
		String saida = mensagem;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			saida = MessageFormat.format(bundle.getString(mensagem), parametros);
		} catch (Exception e) {}
		
		// Armazena o nome da variável de mensagem
		context().getExternalContext().getFlash().put("message_" + severity.toString().toLowerCase().substring(0,4), saida);
	}
    
    /**
     * Adiciona uma mensagem na view com severidade INFO.
     * 
     * @param mensagem - mensagem a adicionar
     * @param parametros - parâmetros a substituir na mensagem
     */
	protected void addMsgInfo(String mensagem, Object... parametros) {
		addMsg(FacesMessage.SEVERITY_INFO, mensagem, parametros);
	}
	

	/**
     * Adiciona uma mensagem no facesMessages com severidade de WARNING.
     * 
     * @param mensagem - mensagem a adicionar
     * @param parametros - parâmetros a substituir na mensagem
     */
	protected void addMsgWarn(String mensagem, Object... parametros) {
		addMsg(FacesMessage.SEVERITY_WARN, mensagem, parametros);
	}
	

	/**
     * Adiciona uma mensagem no facesMessages com severidade de WARNING.
     * 
     * @param mensagem - mensagem a adicionar
     * @param parametros - parâmetros a substituir na mensagem
     */
	protected void addMsgError(String mensagem, Object... parametros) {
		addMsg(FacesMessage.SEVERITY_ERROR, mensagem, parametros);
	}
	
	
	/**
	 * Efetua o redirect (código 302) para a URL especificada. O método apenas
	 * concatena a string <code>"?faces-redirect=true"</code> ao final dos endereços.
	 * <p>
	 * Esta navegação melhora a atualização das páginas e só exibe as mensagens do sistema 
	 * na tela se recuperá-las usando o <a href=\"http://mkblog.exadel.com/2010/07/learning-jsf2-using-flash-scope/\">flash scope</a>.
	 * 
	 * @param url - endereço para redirect no padrão "/pasta/pagina.jsf"
	 */
	protected String redirect(String url) {
		if (url.contains("?"))
			return url + "&amp;faces-redirect=true";
		return url + "?faces-redirect=true";
	}
}
