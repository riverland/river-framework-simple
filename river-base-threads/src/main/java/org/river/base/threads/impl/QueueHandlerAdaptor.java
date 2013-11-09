package org.river.base.threads.impl;

import org.river.base.threads.IQueueHandler;
import org.river.base.threads.type.DataEntity;

/**
 * 适配，以防止实现所有方法
 * @author river
 *
 * @param <E>
 */
public abstract class QueueHandlerAdaptor<E> implements IQueueHandler<E> {

	public void handle(DataEntity<E> data) {

	}

	public void handle(DataEntity<E> data, boolean flush) {

	}

	public void flush() {
		
	}
	
	
}
