package org.river.base.threads.impl;

import org.river.base.threads.IQueue;
import org.river.base.threads.WatchThread;
import org.river.base.threads.type.DataEntity;

/**
 * 队列处理管理器默认实现
 * 
 * @author river
 * 
 * @param <E>
 * @date 20120917
 */
public class DefaultQueueHandlerManager<E> extends QueueHandlerManager<E> {	
	
	/**
	 * 默认构造器
	 */
	public DefaultQueueHandlerManager() {
		super();
	}
	
	/**
	 * 构造器
	 * @param queue
	 */
	public DefaultQueueHandlerManager(IQueue<E> queue) {
		super(queue);
	}
	
	
	@Override
	protected Runnable getHandlerThread(DataEntity<E> data) {
		
		return new HandleThread(data);
	}
	

	@Override
	protected WatchThread getWatchThread() {
		return new QueueManagerWhatchThread();
	}
	

}
