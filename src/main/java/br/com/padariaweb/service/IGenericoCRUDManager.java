package br.com.padariaweb.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.exception.ValidacaoException;



public interface IGenericoCRUDManager<TObjPersist, TObjID extends Serializable> {

	/**
	 * Insere um registro na camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void salvar(TObjPersist pEntidade) throws ValidacaoException;

	/**
	 * Insere uma lista de registros na camada de dados, em uma única transação.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void salvar(List<TObjPersist> listObj) throws ValidacaoException;

	/**
	 * Atualiza um registro na camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public TObjPersist atualizar(TObjPersist pEntidade) throws ValidacaoException;

	/**
	 * Remove um registro da camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void excluir(TObjPersist pEntidade) throws ValidacaoException;
	
	
	/**
	 * Remove uma lista de registros em uma transação.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void excluir(List<TObjPersist> listObj) throws ValidacaoException;

	/**
	 * Dada uma PK retorna o objeto correspondente.
	 * 
	 * @since 1.2.0
	 * @param pPK
	 * @return
	 * @throws ValidacaoException
	 */
	public TObjPersist buscarPeloId(TObjID pPK) throws ValidacaoException;

	/**
	 * Busca todas as entidades.
	 * 
	 * @return todos os registros da tabela
	 * @throws ValidacaoException
	 */
	public List<TObjPersist> buscarTodos() throws ValidacaoException;

	/**
	 * Busca todas as entidades po exemplo.
	 * 
	 * @return todos os registros da tabela por exemplo
	 * @throws ValidacaoException
	 */
	public List<TObjPersist> buscarExemplo(TObjPersist obj) throws ValidacaoException;

}