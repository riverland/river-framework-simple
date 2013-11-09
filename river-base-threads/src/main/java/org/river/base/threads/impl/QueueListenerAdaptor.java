package org.river.base.threads.impl;

import org.river.base.threads.IQueue;
import org.river.base.threads.IQueueEvent;
import org.river.base.threads.IQueueListener;
/**
 * 队列监听者接口适配
 * @author river
 * @param E
 * @date 20120913
 */
public class QueueListenerAdaptor<E> implements IQueueListener<E> {

	protected IQueue<E> listenQueue;
	
	private IQueueListener<E> next;
	
	public void onQueueStart(IQueueEvent event) {
		IQueueListener<E> next=next();
		if(next!=null){
			next.onQueueStart(event);
		}
	}

	public void onQueueStop(IQueueEvent event) {
		IQueueListener<E> next=next();
		if(next!=null){
			next.onQueueStop(event);
		}
	}

	public void onTimeout(E data) {
		IQueueListener<E> next=next();
		if(next!=null){
			next.onTimeout(data);
		}
	}

	public void onQueueEmpty(IQueueEvent event) {
		IQueueListener<E> next=next();
		if(next!=null){
			next.onQueueEmpty(event);
		}
	}

	public void onQueueBuzy(IQueueEvent event) {
		IQueueListener<E> next=next();
		if(next!=null){
			next.onQueueBuzy(event);
		}
	}

	public void setListenQueue(IQueue<E> queue) {
		this.listenQueue =	queue;
	}

	public IQueueListener<E> next() {
		return this.next;
	}

	public void setNext(IQueueListener<E> listener) {
		this.next=listener;
	}

}
