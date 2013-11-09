package org.river.base.threads;
/**
 * 队列监听者接口
 * @author river
 * @param E
 * @date 20120913
 */
public interface IQueueListener<E> {	
	
	/**
	 * <p>
	 * 对队列启动事件进行监听
	 * @param event
	 */
	public void onQueueStart(IQueueEvent event);
	
	/**
	 * <p>
	 * 对队列停止事件进行监听
	 * @param event
	 */
	public void onQueueStop(IQueueEvent event);
	
	/**
	 * <p>
	 * 对数据超时事件进行监听
	 * @param data
	 */
	public void onTimeout(E data);
	
	/**
	 * <p>
	 * 对队列空载事件进行监听
	 * @param event
	 */
	public void onQueueEmpty(IQueueEvent event);
	
	/**
	 * <p>
	 * 对队列满载事件进行监听
	 * @param event
	 */
	public void onQueueBuzy(IQueueEvent event);
	
	/**
	 * <p>
	 * 设置要监听的队列
	 * @param queue
	 */
	public void setListenQueue(IQueue<E> queue);
	
	/**
	 * <p>
	 * 下个监听
	 * @return
	 */
	public IQueueListener<E> next();
	
	/**
	 * <p>
	 * 设置下个监听
	 */
	public void setNext(IQueueListener<E> listener);
	
	
}
