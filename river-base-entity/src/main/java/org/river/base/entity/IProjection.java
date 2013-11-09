package org.river.base.entity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * filter entity from entity source(db,list,file,eg) with {@link IFilter}
 * </p>
 * 
 * @author river 2010/06/01
 */
public interface IProjection {
    /***
     * <p>check whether the entity satisfied with filter </p>
     * @param entity
     * @param filter
     * @return
     * @throws Exception 
     */
	public boolean fit(IEntity entity, IFilter filter) throws Exception;
	
	/**
	 * get the projection of entities under the filter
	 * @param entities
	 * @param filter
	 * @return
	 * @throws Exception 
	 */
	public List<IEntity> filter(Collection<IEntity> entities,IFilter filter) throws Exception;
	
	/**
	 * get the projection of  entities from  entity source,such as database,xml file, under the filter
	 * @param filter
	 * @return
	 */
	public List<IEntity> filter(IFilter filter);
	
	
}
