package br.com.padariaweb.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericoCRUDDAO <T, ID extends Serializable> {

public T findById(Class<T> entityClass, ID identifier);
	
	public List<T> findByHQLQuery(String hqlQuery);
	
	public List<T> findByNativeQuery(String nativeQuery);
	
	public List<T> findAll(Class<T> entityClass);
	
	public List<T> buscaExemplo(T entity);

	public void save(T entity);
	
	public T update(T entity);
	
	public void delete(T entity);
	
	public void evict(T entity);
	
	public void flush();
	public List<T> findAll(String... colunas);
	public T findFirst(Object... parametros);
	public List<T> find(Object... parametros);
	
}
