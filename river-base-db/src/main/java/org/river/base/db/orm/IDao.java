package org.river.base.db.orm;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.river.base.entity.IFilter;
import org.river.base.entity.util.Sort;

import org.river.base.db.pagination.IPagination;


/**
 * <p> 
 * common interface for data access object
 * @author river
 * 2010/06/15
 */
public interface IDao<E,PK extends Serializable> {
	/**
	 * <p>
	 * get the database connection object of the data object
	 * @return
	 */
    public Connection connect(); 
    
    /**
     * <p>
     * get data source object 
     * @return
     */
    public DataSource getDataSource();
    
    /**
     * <p>
     * get the Entity object from database,not support lazy entity
     * @param pk
     * @return
     */
    public E get(PK pk);
    
    /**
     * <p>
     * load the Entity object from database,support lazy entity
     * @param pk
     * @return
     */
    public E load(PK pk);
    
    /**
     * persist the entity to database
     * @param entity
     */
    public void save(E entity);
    
    /**
     * update entity to database
     * @param entity
     */
    public void update(E entity);
    
    /**
     * update entity to database which apply to the filter
     * @param entity
     * @throws Exception 
     */
    public int update(Map<String,Object> params,IFilter filter) throws Exception;
    
    /**
     * update entity ignore empty properties
     * not support yet
     * @param entity
     */
    public void updateIgnoreEmpty(E entity);
    
    /**
     * save or update entity to database
     * @param entity
     */
    public void saveOrUpdate(E entity);
    
    /**
     * <p>
     * delete entity
     * @param entity
     */
    public void delete(E entity);
    
    /**
     * <p>
     * delete entity with specified pk
     * @param pk
     */
    public void delByPrimaryKey(PK pk);
    
    /**
     * <p>
     * delete all entities fit under filter
     * not support cascade delete filter
     * @param filter
     * @throws Exception 
     * @return the deleted row count
     */
    public int delete(IFilter filter) throws Exception;
    
    /**
     * <p>
     * delete all entities fit under filter
     * not support cascade delete filter
     * @param filter
     * @throws Exception 
     * @return the deleted row count
     */
    public int deleteWithCascadeFilter(IFilter filter) throws Exception;
    
    /**
     * <p>
     * get all of E instance from database
     * @param sort
     * @return
     */
    public List<E> all(Sort sort);
    
    /**
     * <p>
     * search  E instance with {@link IFilter},not support pagination
     * @param filter
     * @return
     * @throws Exception 
     */
    public List<E> search(IFilter filter) throws Exception;
    
    /**
     * search  E instance with {@link IFilter},not support pagination
     * @param filter
     * @param sort
     * @return
     * @throws Exception
     */
    public List<E> search(IFilter filter,Sort sort) throws Exception;
    
    /**
     * search all E instances,support pagination and sort 
     * @param from
     * @param scope
     * @param sort {@link Sort}
     * @return
     */
    public IPagination<E> search(int from,int scope,Sort sort);
    
    
    /**
     * search  E instance with {@link IFilter},support pagination and sort 
     * @param from
     * @param scope
     * @param sort {@link Sort}
     * @return
     * @throws Exception 
     */
    public IPagination<E> search(IFilter filter,int from,int scope,Sort sort) throws Exception;
    
    /**
     * get unique entity with IFilter
     * @param filter
     * @return
     * @throws Exception
     */
    public E unique(IFilter filter) throws Exception;
    
    /**
     * <p>
     * count entities number
     * @param filter
     * @return
     * @throws Exception 
     */
    public long count(IFilter filter) throws Exception;
    
    /**
     * <p>
     * count all entities number
     * @return
     */
    public long count();
    
    /**
     * flush all entity modifies
     */
    public void flush();
    
}
