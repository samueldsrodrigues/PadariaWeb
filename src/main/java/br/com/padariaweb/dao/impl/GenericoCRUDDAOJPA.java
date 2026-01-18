package br.com.padariaweb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.padariaweb.dao.IGenericoCRUDDAO;

@Repository
public class GenericoCRUDDAOJPA<T, ID extends Serializable> implements IGenericoCRUDDAO<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> tipo;

	
	@SuppressWarnings("unchecked")
	public GenericoCRUDDAOJPA() {
		// Recuperado o tipo do class pelo generic
		Type superclass = getClass().getGenericSuperclass();
		if (superclass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superclass;
			if (parameterizedType.getActualTypeArguments().length > 0){
				tipo = (Class<T>) parameterizedType.getActualTypeArguments()[0];
			}
		}
	}
	
	public T findById(Class<T> entityClass, ID identifier) {
		T entity = null;
		
		if (identifier != null) {
			entity = entityManager.find(entityClass, identifier);
		}
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHQLQuery(String hqlQuery) {
		List<T> entities = null;
		
		if (hqlQuery != null) {
			entities = entityManager.createQuery(hqlQuery).getResultList();
		}
		
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String nativeQuery) {
		List<T> entities = null;
		
		if (nativeQuery != null) {
			entities = entityManager.createNativeQuery(nativeQuery).getResultList();
		}
		
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		return entityManager.createQuery(new String("FROM " + entityClass.getCanonicalName())).getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		T entityMerged = entityManager.merge(entity);
		entityManager.remove(entityMerged);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	protected void criarLike(StringBuilder query, String property) {
		query.append(" and TRANSLATE ( LOWER(" + property + "), 'ÁÇÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕËÜáçéíóúàèìòùâêîôûãõëü', 'ACEIOUAEIOUAEIOUAOEUaceiouaeiouaeiouaoeu') like '%'|| TRANSLATE(").append(" ? ").append(",'ÁÇÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕËÜáçéíóúàèìòùâêîôûãõëü', 'ACEIOUAEIOUAEIOUAOEUaceiouaeiouaeiouaoeu') ||'%' ");
	}
	
	protected void criarLikeSemCondicional(StringBuilder query, String property) {
		query.append(" TRANSLATE ( LOWER(" + property + "), 'ÁÇÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕËÜáçéíóúàèìòùâêîôûãõëü', 'ACEIOUAEIOUAEIOUAOEUaceiouaeiouaeiouaoeu') like '%'|| TRANSLATE(").append(" ? ").append(",'ÁÇÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕËÜáçéíóúàèìòùâêîôûãõëü', 'ACEIOUAEIOUAEIOUAOEUaceiouaeiouaeiouaoeu') ||'%' ");
	}

	public List<T> buscaExemplo(T entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void evict(T entity) {
		getSession().evict(entity);
		
	}
	@Override
	public void flush() {
		getSession().flush();
		
	}
	public Session getSession() {
		Session session = null;
		try{
			session = (Session) this.entityManager.getDelegate();
		}catch(Exception ex){}
		return session;
	}
	
	protected Criteria criteria(){
		return getSession().createCriteria(tipo);
	}
	
	/**
	 * Pesquisa uma entidade pela chave primária.
	 * 
	 * @param id - valor do identificador da entidade
	 * @return a entidade pesquisada ou <code>null</code> se não for encontrada.
	 */
	public T find(ID id){
		return (T) getSession().get(tipo, id);
	}
	
	/**
	 * Pesquisa uma entidade por uma lista de atributos desta.
	 * 
	 * @param parametros
	 * 			array de parâmetros na combinação "chave-valor", fornecendo o campo da entidade como chave 
	 * 			e o valor para pesquisa
	 * @param columnOrder
	 * 			nome de um ou mais campos da entidade para ordenar
	 * @return a lista de entidades encontradas ou uma lista vazia, caso não sejam
	 *         encontrados atributos correspondentes.
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(Object... parametros) {
		Criteria criteria = prepareCriteria(parametros);
		return criteria.list();
	}
	
	
	
	/**
	 * Pesquisa uma entidade por uma lista de atributos desta e retorna a primeira ocorrência.
	 * 
	 * @param parametros
	 * 			array de parâmetros na combinação "chave-valor", fornecendo o campo da entidade como chave 
	 * 			e o valor para pesquisa
	 * @param columnOrder
	 * 			nome de um ou mais campos da entidade para ordenar
	 * @return a primeira ocorrência da entidade pesquisada ou <code>null</code> se não encontrar.
	 */
	@SuppressWarnings("unchecked")
	public T findFirst(Object... parametros) {
		Criteria criteria = prepareCriteria(parametros);
		criteria.setMaxResults(1);
		return (T) criteria.uniqueResult();
	}
	
//	/**
//	 * Pesquisa uma entidade por uma lista de atributos desta e retorna a primeira ocorrência encontrada.
//	 * 
//	 * @param parametros - mapa de parâmetros fornecendo o nome do campo da entidade 
//	 *                     como chave e o valor para pesquisa
//	 * @return a entidade encontrada ou <code>null</code> se nada for encontrado com os atributos fornecidos.
//	 */
//	@SuppressWarnings("unchecked")
//	public E findFirst(Map<String, ?> parametros) {
//		Criteria criteria = prepareCriteria(parametros, 1);
//		return (E) criteria.uniqueResult();
//	}
	
	/**
	 * Pesquisa por todas as ocorrências de uma entidade.
	 * 
	 * @param colunas - nome de um ou mais campos da entidade para ordenar, seguido da ordenação "asc" ou "desc".
	 * 			Se não existir a sinalização "asc" ou "desc", será admitido ordenação crescente: "asc".
	 * 			ex.: <code>findAll("nome asc", "email desc")</code>
	 * @return a lista de entidades encontradas ou uma lista vazia, caso não sejam
	 *         encontrados atributos correspondentes.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String... colunas){
		Criteria criteria = prepareCriteria();
		int POSICAO_NOME = 0;
		int POSICAO_ORDENACAO = 1;
		
		for (String coluna : colunas) {
			// Quebra a string pelo espaço p/ recuperar a ordenação
			String[] quebra = coluna.split("\\ ");
			String nome 	= quebra[POSICAO_NOME];
			String ordem	= "asc";
			// Se não houver um 2º argumento, então ordenação "asc" prevalece
			if (quebra.length == 2)
				ordem = quebra[POSICAO_ORDENACAO].toLowerCase();
			criteria.addOrder(ordem.equals("asc") ? Order.asc(nome) : Order.desc(nome));
		}
		return criteria.list();
	}
	
	/**
	 * Monta um {@link Criteria} para pesquisa.
	 * 
	 * @param parametros - {@link Map} com o mapeamento parâmetro/valor a pesquisar
	 * @param maxResults - quantidade máxima de resultados
	 * @param columnOrder - colunas da entidade a aplicar a ordenação
	 * @return o criteria montado
	 */
	private Criteria prepareCriteria(Object... parametros) {
		if (parametros.length % 2 != 0)
			throw new IllegalArgumentException("Os parâmetros devem ser múltiplos de 2, seguindo o padrão 'chave-valor'.");
		
		Criteria criteria = getSession().createCriteria(tipo);
		for (int i = 0; i < parametros.length; i += 2)
			if (parametros[i+1] instanceof String)
				criteria.add(Restrictions.ilike(parametros[i].toString(), parametros[i+1].toString(), MatchMode.ANYWHERE));
			else
				criteria.add(Restrictions.eq(parametros[i].toString(), parametros[i+1]));
		
		return criteria;
	}
	
}
