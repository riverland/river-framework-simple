package org.river.base.threads;

/**
 * 队列管理接口
 * @author river
 * @date 20120913
 */
public interface IQueueManager<E> {


	/**
	 * <p>
	 * 队列初始化。
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;

	/**
	 * <p>
	 * 启动队列。
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception;

	/**
	 * <p>
	 * 停止队列的运行。
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception;

	
	/**
	 * <p>
	 * 加入一个队列侦听器。
	 * 
	 * @param listener
	 */
	public void addListener(IQueueListener<E> listener);
	
	/**
	 * 触发队列时间
	 * @param event
	 */
	public void fire(IQueueEvent event);
}
