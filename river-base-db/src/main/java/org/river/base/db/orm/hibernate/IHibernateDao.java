package org.river.base.db.orm.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.river.base.db.orm.IDao;
import org.river.base.db.pagination.IPagination;


/**
 * <p>
 * hibernate interface for data access object
 * 
 * @author river 2010/06/15
 */
public interface IHibernateDao<E, PK extends Serializable> extends IDao<E, PK> {
	/**
	 * get the data access object binding SessionFactory
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory();

	/**
	 * binding sessionFactory to data access object
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory);

	/**
	 * get current session of SessionFactory
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * open a session,which should be closed manually
	 * 
	 * @return
	 */
	public Session openSession();

	/**
	 * find entity by hql,pagination not supporting
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findByHql(String hql, Object... params);

	/**
	 * find entity by hql,pagination not supporting
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findByHql(String hql, Map<String, Object> params);

	/**
	 * find entity by hql,pagination supporting
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findByHql(String hql, int from, int scope,
			Object... params) throws Exception;

	/**
	 * find entity by hql,pagination supporting
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findByHql(String hql, int from, int scope,
			Map<String, Object> params) throws Exception;

	/**
	 * find entity by sql,pagination not supporting
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findBySQL(String sql, Object... params);

	/**
	 * find entity by sql,pagination not supporting
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<E> findBySQL(String sql, Map<String, Object> params);

	/**
	 * find entity by sql,pagination supporting
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findBySQL(String sql, int from, int scope,
			Object... params) throws Exception;

	/**
	 * find entity by sql,pagination supporting
	 * 
	 * @param hql
	 * @param from
	 * @param scope
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public IPagination<E> findBySQL(String sql, int from, int scope,
			Map<String, Object> params) throws Exception;

	/**
	 * <p>
	 * count by hql
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public long countBySQL(String sql, Object... params) throws Exception;

	/**
	 * count by hql
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public long countByHql(String hql, Object... params) throws Exception;

	/**
	 * <p>
	 * count by sql
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public long countBySQL(String sql, Map<String, Object> params)
			throws Exception;

	/**
	 * count by hql
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public long countByHql(String hql, Map<String, Object> params)
			throws Exception;
}
