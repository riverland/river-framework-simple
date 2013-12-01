package org.river.base.threads.impl;

import org.river.base.threads.type.DataEntity;

/**
 * <p>
 * 异步handle
 * 
 * @author river
 * @since Dec 1, 2013
 * @param <E>
 */
public abstract class AsyncQueueHandler<E> extends QueueHandlerAdaptor<E> {
	
	/**
	 * <p>
	 * 实际的业务逻辑,该业务逻辑异步执行
	 * @param data
	 */
	protected abstract void asyncHandle(E data);
	
	/**
	 * <p>
	 * 判断是否需要处理
	 * @param data
	 * @return
	 */
	protected abstract boolean needHandle(DataEntity<E> data);
	
	@Override
	public void handle(DataEntity<E> data) {
		if(!this.needHandle(data)){
			return ;
		}
		
		//TODO 该处向后可考虑用线程池处理
		Thread asyncHandle=new HandleThread(data.getData());
		asyncHandle.start();
		data.setData(null);		
	}
	
	

	@Override
	public Class<?> getDataType() {
		return null;
	}

	private class HandleThread extends Thread {
		
		private E data;	

		public HandleThread(E data) {
			super();
			this.data = data;
		}

		@Override
		public void run() {
			asyncHandle(data);
		}

	}

}
