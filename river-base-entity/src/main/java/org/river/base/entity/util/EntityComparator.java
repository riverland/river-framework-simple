package org.river.base.entity.util;

import java.util.Comparator;
import java.util.Iterator;

import org.river.base.entity.IEntity;


/**
 * A simple entity comparator for sort entities in collection. Example : if we
 * want sort orders list by orderId asc and orderName desc, you can write these
 * codes as following
 * 
 * Collections.sort(orders, new EntityComparator("orderId,orderName desc"));
 * 
 * @author river 2010/05/31
 */
public class EntityComparator implements Comparator<IEntity> {

	private Sort sort;

	/**
	 * Creates a new <code>EntityComparator</code> instance.
	 * 
	 * @param orderBy
	 *            an order <code>String</code> value
	 */
	public EntityComparator(Sort sort) {
		this.sort = sort;
	}

	/**
	 * Compares two entities and return the result.
	 * 
	 * @param entity1
	 *            a <code>EntityInterface</code> value
	 * @param entity2
	 *            a <code>EntityInterface</code> value
	 * @return a <code>int</code> value
	 */
	@SuppressWarnings("unchecked")
	public int compare(IEntity entity1, IEntity entity2) {
		// remove extra space from orign string
		Iterator iter = this.sort.getSorters().keySet().iterator();
		while (iter.hasNext()) {

			String fieldName = (String) iter.next();
			int result = compare(entity1, entity2, fieldName, this.sort
					.getSorters().get(fieldName));
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	/**
	 * Compares the order fields of two entities and return the result.
	 * 
	 * @param o1
	 *            a <code>EntityInterface</code> value
	 * @param o2
	 *            a <code>EntityInterface</code> value
	 * @param fieldName
	 *            a <code>String</code> value
	 * @param sortType
	 *            a <code>int</code> value
	 * @return a <code>int</code> value
	 */
	@SuppressWarnings("unchecked")
	public int compare(IEntity o1, IEntity o2,
			String fieldName, int sortType) {
		int rt = 0;
		try {
			if (o1 == null && o2 == null) {
				return 0;
			}
			if (o1 == null && o2 != null) {
				return -1 * sortType;
			}
			if (o1 != null && o2 == null) {
				return sortType;
			}
			if (o1.get(fieldName) == null && o2.get(fieldName) == null) {
				return 0;
			}
			if (o1.get(fieldName) == null && o2.get(fieldName) != null) {
				return -1 * sortType;
			}
			if (o1.get(fieldName) != null && o2.get(fieldName) == null) {
				return sortType;
			}
			rt = sortType
					* ((Comparable) o1.get(fieldName)).compareTo(o2
							.get(fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
}
