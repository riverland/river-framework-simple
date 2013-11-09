package org.river.base.threads;

import java.util.Date;

import org.river.base.threads.type.Message;

/**
 * 列表事件
 * @author river
 * @date20120913
 */
public interface IQueueEvent {
	
	
	/**
	 * 事件描述信息
	 * @return
	 */
	public EventType what();
	
	/**
	 * 事件发生事件
	 * @return
	 */
	public Date  when();
	
	/**
	 * 事件信息
	 * @return
	 */
	public Message<?> why();	
	
	/**
	 * 队列事件枚举
	 * @author river
	 * @date 20120912
	 */
	public static enum EventType{
		TIMEOUT,START,STOP,EMPTY,BUZY;
	}
}
