package br.com.padariaweb.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IGenericoCRUDDAO;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IGenericoCRUDManager;



@Service("genericoCRUDManager")
@Transactional
public abstract class GenericoCRUDManager<TObjPersist, TObjID extends Serializable> implements IGenericoCRUDManager<TObjPersist, TObjID> {

	@Autowired
	@Qualifier("genericoCRUDDAOJPA")
	private IGenericoCRUDDAO<TObjPersist, TObjID> iAbstractDAO;
	
	/**
	 * Insere um registro na camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void salvar(TObjPersist pEntidade) throws ValidacaoException {
		preSalvar(pEntidade);
		iAbstractDAO.save(pEntidade);
		posSalvar(pEntidade);
	}
	
	public void preSalvar(TObjPersist pEntidade) throws ValidacaoException {}
	
	public void posSalvar(TObjPersist pEntidade) throws ValidacaoException {}
	
	
	/**
	 * Insere uma lista de registros na camada de dados, em uma única transação.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void salvar(List<TObjPersist> pListEntidade) throws ValidacaoException {
		preSalvar(pListEntidade);
		for (TObjPersist tObjPersist : pListEntidade) {
			iAbstractDAO.save(tObjPersist);
		}
		posSalvar(pListEntidade);		
	}
	
	public void preSalvar(List<TObjPersist> pListEntidade) throws ValidacaoException {}
	
	public void posSalvar(List<TObjPersist> pListEntidade) throws ValidacaoException {}


	/**
	 * Atualiza um registro na camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public TObjPersist atualizar(TObjPersist pEntidade) throws ValidacaoException {
		preAtualizar(pEntidade);
		iAbstractDAO.update(pEntidade);
		posAtualizar(pEntidade);
		return pEntidade;
	}
	
	public void preAtualizar(TObjPersist pEntidade) throws ValidacaoException {}
	
	public void posAtualizar(TObjPersist pEntidade) throws ValidacaoException {}

	/**
	 * Remove um registro da camada de dados.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void excluir(TObjPersist pEntidade) throws ValidacaoException {
		preExcluir(pEntidade);
		iAbstractDAO.delete(pEntidade);
		posExcluir(pEntidade);
	}
	
	public void preExcluir(TObjPersist pEntidade) throws ValidacaoException {}
	
	public void posExcluir(TObjPersist pEntidade) throws ValidacaoException {}
	
	/**
	 * Remove uma lista de registros em uma transação.
	 * 
	 * @since 1.2.0
	 * @param pEntidade
	 * @throws ValidacaoException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "*Exception")
	public void excluir(List<TObjPersist> pListEntidade) throws ValidacaoException {
		preExcluir(pListEntidade);
		for (TObjPersist tObjPersist : pListEntidade) {
			iAbstractDAO.delete(tObjPersist);			
		}
		posExcluir(pListEntidade);
	}
	
	public void preExcluir(List<TObjPersist> pListEntidade) throws ValidacaoException {}
	
	public void posExcluir(List<TObjPersist> pListEntidade) throws ValidacaoException {}

	/**
	 * Dada uma PK retorna o objeto correspondente.
	 * 
	 * @since 1.2.0
	 * @param pPK
	 * @return
	 * @throws ValidacaoException
	 */
	@Override
	public TObjPersist buscarPeloId(TObjID pPK) throws ValidacaoException {
		TObjPersist pEntidade = null;
		
		if (pPK != null) {
			pEntidade = iAbstractDAO.findById(getEntityClass(), pPK);
		}
		
		return pEntidade;
	}
	
	/**
	 * Busca todas as entidades.
	 * 
	 * @return todos os registros da tabela
	 * @throws ValidacaoException
	 */
	@Override
	public List<TObjPersist> buscarTodos() throws ValidacaoException {
		return iAbstractDAO.findAll(getEntityClass());
	}
	
	/**
	 * Busca todas as entidades po exemplo.
	 * 
	 * @return todos os registros da tabela por exemplo
	 * @throws ValidacaoException
	 */
	public List<TObjPersist> buscarExemplo(TObjPersist obj)
		throws ValidacaoException{
		return iAbstractDAO.buscaExemplo(obj);
	}

	public void setIAbstractDAO(IGenericoCRUDDAO<TObjPersist, TObjID> iAbstractDAO) {
		this.iAbstractDAO = iAbstractDAO;
	}

	@SuppressWarnings("unchecked")
	protected Class<TObjPersist> getEntityClass() {
		return (Class<TObjPersist>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	
}