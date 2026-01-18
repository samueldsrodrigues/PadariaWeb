package br.com.padariaweb.exception;

/**
 * Exceção lançada durante o processo de validação de uma entidade.
 * 
 * @author Renato
 */
public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	private Object[] parametros;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem - mensagem a exibir para o usuário.
	 */
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
	
	
	/**
	 * Construtor com parâmetros para substituição da mensagem.
	 * Deve ser usado para parametrizar a mensagem com fácil
	 * substituição dos parâmetros. Exemplo: 
	 * 
	 * <pre>new ValidacaoException(null, null, "Campo {0} obrigatório", "Data");
	 */
	public ValidacaoException(String mensagem, Object... parametros) {
//		super(MessageFormat.format(mensagem, parametros));
		super(mensagem);
		this.parametros = parametros;
	}

	public Object[] getParametros() {
		return parametros;
	}
}