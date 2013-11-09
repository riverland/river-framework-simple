package org.river.base.db.orm.hibernate;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.river.base.entity.Entity;

/**
 * <p>
 * <b>base entity class for the orm entity</b>
 * <li>id</li>
 * <id>createTime</id>
 * <li>updateTime</li>
 * @author river
 * @date 20120913
 */
@MappedSuperclass
public abstract class BaseORMEntity extends Entity{
	public static final String DELETE_STATUS_YES="Y";
	
	public static final String DELETE_STATUS_NO="N";
	
	protected Long id;
	
	protected Date createTime=new Date();
	
	protected Date updateTime=createTime;
	
	protected String deleteStatus=DELETE_STATUS_NO;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@Basic
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Version
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Basic
	@Column(name="DEL_STATUS",length=8)
	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@SuppressWarnings("rawtypes")
	@Transient
	@Override
	public Class getType() {
		return this.getClass();
	}
	
	


}
