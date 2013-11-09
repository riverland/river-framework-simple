package org.river.base.db.orm;

import java.util.Map;

/**
 * <p>
 * sql template interface.
 * sql build method for the SQL builder such as {@link HibernateDao}
 * @author river
 * @date 20121129
 */
public interface ISqlTemplate {
	
	/**
	 * <p>
	 * build the update set string
	 * @param params the key-value set to be updated
	 * @return
	 */
	public String updateSetString(Map<String,Object> params);
}
