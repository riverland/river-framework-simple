package org.river.base.db.pagination;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * default implement of IPagination
 * @author river
 *
 * @param <E>
 */
public class Pagination<E> implements IPagination<E>,Serializable {
	private static final long serialVersionUID = 1L;

	//data holder list
	private List<E> items;
	
	//count for Pagination to page from 
	private int from;
	
	//page capacity
	private int scope;
	
	//total records count
	private long totalCount;

	public Pagination(List<E> items, int from, int scope, long totalCount) {
		super();
		this.items = items;
		this.from = from;
		this.scope = scope;
		this.totalCount = totalCount;
	}

	public int getFrom() {
		return this.from;
	}

	@SuppressWarnings("unchecked")
	public List<E> getItems() {		
		return items==null?Collections.EMPTY_LIST:this.items;
	}

	public int getScope() {
		return this.scope;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setFrom(int from) {
		this.from=from;
	}

	public void setItems(List<E> items) {
		this.items=items;
	}

	public void setScope(int scope) {
		this.scope=scope;
	}

	public void setTotalCount(long count) {
		this.totalCount=count;
	}

}
