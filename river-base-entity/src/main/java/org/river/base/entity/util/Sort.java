package org.river.base.entity.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.river.base.entity.enumaration.SortType;



/**
 * Entity sorter
 * 
 * @author river
 * 2010/05/31
 */
public class Sort {

	private Map<String, Integer> sorters;

	private Sort() {
		sorters = new LinkedHashMap<String, Integer>();
	}
	
	public Map<String, Integer> getSorters() {
		return sorters;
	}

	public static Sort newInstance() {
		return new Sort();
	}

	public Sort asc(String propertyName) {
		sorters.put(propertyName, SortType.ASC.getValue());
		return this;
	}
	
	public Sort desc(String propertyName) {
		sorters.put(propertyName, SortType.DESC.getValue());
		return this;
	}
}
