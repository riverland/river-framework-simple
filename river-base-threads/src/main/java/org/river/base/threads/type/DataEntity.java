package org.river.base.threads.type;

import java.util.Date;

/**
 * <p>
 * 队列数据载体
 * @author river
 * @date 20120913
 */
public class DataEntity<E> {
	private E data;
	
	private Date packTime;
	
	private Date updateTime;	
	
	public DataEntity(E data) {
		super();
		this.data = data;
		this.packTime=new Date();
		this.updateTime=this.packTime;
	}
	
	public boolean isEmpty(){
		return data==null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Date getPackTime() {
		return packTime;
	}

	public void setPackTime(Date packTime) {
		this.packTime = packTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
