package org.river.base.db.orm.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.river.base.entity.IFilter;
import org.river.base.entity.util.IFilterParser;
import org.river.base.entity.util.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.river.base.db.pagination.IPagination;
import org.river.base.db.pagination.Pagination;

/**
 * <p>
 * Hibernate data access object 
 * @author river
 * @param <E>
 * @param <PK>
 * 2010/06/17
 */
@SuppressWarnings("unchecked")
public class HibernateDao<E extends BaseORMEntity, PK extends Serializable> implements
		IHibernateDao<E, PK> {

	private static Logger log = LoggerFactory.getLogger(HibernateDao.class);

	private SessionFactory sessionFactory;
	private Class<E> entityClass;	

	public HibernateDao() {
		super();
	}

	public HibernateDao(SessionFactory sessionFactory, Class<E> entityClass) {
		super();
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public static IHibernateDao newInstance(SessionFactory sessionFactory,
			Class entityClass) throws Exception {
		if (sessionFactory == null) {
			log.error("sessionFactory can't be null");
			throw new Exception("sessionFactory can't be null");
		}
		return new HibernateDao(sessionFactory, entityClass);
	}
	
	

	@Override
	public int update(Map<String, Object> params, IFilter filter) throws Exception {
		if(params==null||params.isEmpty())return 0;
		StringBuffer buf=new StringBuffer(" update ").append(entityClass.getSimpleName()).append(HibernateSqlTemplate.DEFAULT.updateSetString(params));
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!condition.trim().equals("")){
			buf.append(" where ").append(condition);
		}
		
		String updateSql=buf.toString();
		Session s=this.getSession();
		Query q=s.createQuery(updateSql);
		int result=q.executeUpdate();
		return result;
	}
	

	@Override
	public void updateIgnoreEmpty(E entity) {
		//TODO this method should be implemented when appropriate algorithm found;	
	}

	public List<E> findByHql(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	public List<E> findByHql(String hql, Map params) {
		Query query = this.getSession().createQuery(hql);
		if (params != null) {
			Set<String> paramNames = params.keySet();
			for (String name : paramNames) {
				query.setParameter(name, params.get(name));
			}
		}

		return query.list();
	}

	public IPagination<E> findByHql(String hql, int from, int scope,
			Object... params) throws  Exception {
		Query query = this.getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(from);
		query.setMaxResults(from + scope);
		IPagination pagination = new Pagination<E>(query.list(), from, scope,
				this.countByHql(hql, params));
		return pagination;
	}

	public IPagination<E> findByHql(String hql, int from, int scope, Map params) throws  Exception {
		Query query = this.getSession().createQuery(hql);
		if (params != null) {
			Set<String> paramNames = params.keySet();
			for (String name : paramNames) {
				query.setParameter(name, params.get(name));
			}
		}
		query.setFirstResult(from);
		query.setMaxResults(from + scope);
		IPagination pagination = new Pagination<E>(query.list(), from, scope,
				this.countByHql(hql, params));
		return pagination;
	}

	public List<E> findBySQL(String sql, Object... params) {
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				sqlQuery.setParameter(i, params[i]);
			}
		}
		return sqlQuery.list();
	}

	public List<E> findBySQL(String sql, Map params) {
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.addEntity(entityClass);
		if (params != null) {
			Set<String> paramNames = params.keySet();
			for (String name : paramNames) {
				sqlQuery.setParameter(name, params.get(name));
			}
		}

		return sqlQuery.list();
	}

	public IPagination<E> findBySQL(String sql, int from, int scope,
			Object... params) throws Exception {
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.addEntity(entityClass);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				sqlQuery.setParameter(i, params[i]);
			}
		}
		sqlQuery.setFirstResult(from);
		sqlQuery.setMaxResults(from + scope);
		IPagination pagination = new Pagination(sqlQuery.list(), from, scope,
				this.countByHql(sql, params));
		return pagination;
	}

	public IPagination<E> findBySQL(String sql, int from, int scope, Map params) throws Exception {
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.addEntity(entityClass);
		if (params != null) {
			Set<String> paramNames = params.keySet();
			for (String name : paramNames) {
				sqlQuery.setParameter(name, params.get(name));
			}
		}
		sqlQuery.setFirstResult(from);
		sqlQuery.setMaxResults(from + scope);

		IPagination pagination = new Pagination(sqlQuery.list(), from, scope,
				this.countBySQL(sql, params));
		return pagination;
	}

	public Session getSession() {
		return this.getSessionFactory().getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Session openSession() {
		return this.sessionFactory.openSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<E> all(Sort sort) {
		StringBuffer hqlBuffer = new StringBuffer("from ");
		hqlBuffer.append(this.entityClass.getSimpleName()).append(" ");
		
		String orderStr=HQLSortParser.newInstance().parse(sort);
		if(orderStr!=null&&!"".equals(orderStr)){
			hqlBuffer.append(orderStr);
		}			

		Query query = this.getSession().createQuery(hqlBuffer.toString());
		return query.list();
	}

	@SuppressWarnings("deprecation")
	public Connection connect() {
		return this.getSession().connection();
	}

	public void delete(E entity) {
		this.getSession().delete(entity);
	}

	public void delByPrimaryKey(PK pk) {
		E entity = this.load(pk);
		if (entity != null) {
			this.delete(entity);
		}
	}

	public int delete(IFilter filter) throws Exception {
		int rt=0;
		List<E> entities = this.search(filter);
		
		if(entities==null||entities.size()<=0) return rt;
		
		for (E entity : entities) {
			this.delete(entity);
		}
		
		return entities.size();
	}
	
	

	@Override
	public int deleteWithCascadeFilter(IFilter filter) throws Exception {
		int result=0;
		List<E> toDel=this.search(filter);
		if(toDel!=null&&!toDel.isEmpty()){
			result=toDel.size();
			for(E e:toDel){
				this.delete(e);
			}
		}
		return toDel.size();
	}

	public void flush() {
		this.getSession().flush();
	}

	public E get(PK pk) {
		return (E) this.getSession().get(this.entityClass, pk);
	}

	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public E load(PK pk) {
		return (E) this.getSession().load(this.entityClass, pk);
	}

	public void save(E entity) {
		this.getSession().save(entity);
	}

	public void saveOrUpdate(E entity) {
	    if(entity.getId()!=null){
	        entity.setUpdateTime(new Date());
	    }
		this.getSession().saveOrUpdate(entity);
	}

	public List<E> search(IFilter filter) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(this.entityClass.getSimpleName());		
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!"".equals(condition)){
			sb.append(" where ");
			sb.append(condition);
		}		
		
		String hql=sb.toString();
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<E> search(IFilter filter, Sort sort) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(this.entityClass.getSimpleName());
		
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!"".equals(condition)){
			sb.append(" where ");
			sb.append(condition);
		}		
		sb.append(" ");
		
		String orderStr=HQLSortParser.newInstance().parse(sort);
		if(orderStr!=null&&!"".equals(orderStr)){
			sb.append(orderStr);
		}		
		
		String hql=sb.toString();
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}

	public IPagination<E> search(int from, int scope, Sort sort) {
		StringBuffer sb=new StringBuffer("from ");
		sb.append(this.entityClass.getSimpleName()).append(" ");
		
		String orderStr=HQLSortParser.newInstance().parse(sort);
		if(orderStr!=null&&!"".equals(orderStr)){
			sb.append(orderStr);
		}		
		
		String hql=sb.toString();
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult(from);
		query.setMaxResults(from+scope);
		IPagination pageObj=new  Pagination(query.list(),from,scope,this.count());
		return pageObj;
	}

	public IPagination<E> search(IFilter filter, int from, int scope, Sort sort) throws Exception {
		StringBuffer sb=new StringBuffer("from ");
		sb.append(this.entityClass.getSimpleName()).append(" ");
		
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!"".equals(condition)){
			sb.append(" where ");
			sb.append(condition).append(" ");
		}	
		
		String orderStr=HQLSortParser.newInstance().parse(sort);
		if(orderStr!=null&&!"".equals(orderStr)){
			sb.append(orderStr);
		}	
		
		String hql=sb.toString();
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult(from);
		query.setMaxResults(scope);
		IPagination pageObj=new  Pagination(query.list(),from,scope,this.count(filter));
		return pageObj;
	}

	public E unique(IFilter filter) throws Exception {
		StringBuffer sb=new StringBuffer(" from ");
		sb.append(this.entityClass.getSimpleName()).append(" ");
		
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!"".equals(condition)){
			sb.append(" where ");
			sb.append(condition).append(" ");
		}		
		
		String hql=sb.toString();
		Query query=this.getSession().createQuery(hql);
		return (E) query.uniqueResult();
	}

	public void update(E entity) {
	    entity.setUpdateTime(new Date());
		this.getSession().update(entity);
	}


	public long count(IFilter filter) throws Exception {
		StringBuffer sb=new StringBuffer(" select count(*) from ");
		sb.append(this.entityClass.getSimpleName()).append(" ");
		IFilterParser parser = HibernateFilterParser.newInstance();
		String condition=parser.parse(filter);
		if(condition!=null&&!"".equals(condition)){
			sb.append(" where ");
			sb.append(condition).append(" ");
		}
		
		Query query=this.getSession().createQuery(sb.toString());
		return (Long)query.uniqueResult();
	}

	public long count() {
		StringBuffer sb=new StringBuffer(" select count(*) from ");
		sb.append(this.entityClass.getSimpleName());
		Query query=this.getSession().createQuery(sb.toString());
		return (Long)query.uniqueResult();
	}	
	
	
	public long countByHql(String hql, Map<String, Object> params) throws Exception {
		String newHql=this.buildCountSql(hql);
		Query query=this.getSession().createQuery(newHql);
		if(params!=null&&params.size()>0){
			Set<String> keySet=params.keySet();
			for(String key:keySet){
				query.setParameter(key, params.get(key));
			}
		}
		return (Long) query.uniqueResult();
	}



	public long countByHql(String hql, Object... params) throws Exception {
		String newHql=this.buildCountSql(hql);
		Query query=this.getSession().createQuery(newHql);
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		return (Long)query.uniqueResult();
	}

	public long countBySQL(String sql, Map<String, Object> params) throws Exception {
		String newSql=this.buildCountSql(sql);
		SQLQuery query=this.getSession().createSQLQuery(newSql);
		if(params!=null&&params.size()>0){
			Set<String> keySet=params.keySet();
			for(String key:keySet){
				query.setParameter(key, params.get(key));
			}
		}
		BigDecimal rt=(BigDecimal) query.uniqueResult();
		return rt.longValue();
	}

	public long countBySQL(String sql, Object... params) throws Exception {
		String newSql=this.buildCountSql(sql);
		Query query=this.getSession().createQuery(newSql);
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		BigDecimal rt=(BigDecimal) query.uniqueResult();
		return rt.longValue();
	}

	private String buildCountSql(String sql) throws Exception{
		StringBuffer rt=new StringBuffer("select count(*) ");
		String lower=sql.toLowerCase();
		int pos=lower.indexOf("from");
		if(pos>0){
			rt.append(sql.substring(pos));
		}else{
			log.error("invalid sql:"+sql);
			throw new Exception("invalid sql:"+sql);
		}
		return rt.toString();
		
	}
	

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	


}
