package org.river.base.threads.type;

import java.util.Date;

import org.river.base.threads.IQueueEvent;
/**
 * 队列事件实现实体
 * @author river
 * @date 20120913
 */
public class QueueEvent implements IQueueEvent {
	
	private EventType eventType;
	
	private Date eventTime;
	
	private Message<?> msg;
	

	public QueueEvent(EventType eventType,Message<?> msg) {
		super();
		this.eventType = eventType;
		this.eventTime = new Date();
		this.msg = msg;
	}

	public EventType what() {
		return this.eventType;
	}

	public Date when() {
		return this.eventTime;
	}

	public Message<?> why() {
		return this.msg;
	}

}
