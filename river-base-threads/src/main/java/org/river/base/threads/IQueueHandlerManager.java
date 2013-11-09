package org.river.base.threads;

import java.util.List;

/**
 * <B>队列处理器对象管理器的接口</B>
 * <li>此接口对队列的处理器进行管理，主要是对队列的数据进行监听处理，
 *     如果有数据则调用队列处理的处理接口进行相应的处理。
 * </li>
 * 
 * @author river
 * @param <E>
 * @date 20120913
 */
public interface IQueueHandlerManager <E>{
	/**
	 * <p>
	 * 设置队列对象。
	 * 
	 * @param queue
	 */
	public void setQueue(IQueue<E> queue);

	/**
	 * <p>
	 * 增加一个队列的处理器对象。
	 * 
	 * @param handler
	 */
	public void addQueueHandler(IQueueHandler<E> handler);

	/**
	 * <p>
	 * 增加多个队列的处理对象。
	 * 
	 * @param handlers
	 */
	public void addQueueHandlers(List<? extends IQueueHandler<E>> handlers);
	
	/**
	 * <p>
	 * 获取所有注册的队列处理器
	 * @return
	 */
	public List<IQueueHandler<E>> getHandlers();
	
	/**
	 * <p>
	 * 设置拦截器队列
	 * @param handlers
	 */
	public void setHandlers(List<IQueueHandler<E>> handlers);
	
	/**
	 * <p>
	 * 获取管理的队列
	 * @return
	 */
	public IQueue<E> getQueue();
	
	/**
	 * 添加数据
	 * @param data
	 * @throws Exception 
	 */
	public void addData(E data) throws Exception;

	/**
	 * <p>
	 * 初始化队列处理对象的管理器。
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;

	/**
	 * <p>
	 * 启动该对象。
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception;

	/**
	 * <p>
	 * 停止该对象。
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception;
}
