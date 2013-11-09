package org.river.base.threads;

import org.river.base.threads.type.DataEntity;


/**
 * 监听消息队列接口  
 * @author river
 * 
 * @param <E>
 * 
 * @date 20120912
 */
public interface IQueue<E> extends IQueueManager<E>,IQueueInfo<E>{

	/**
	 * <p>
	 * 在消息队列中增加一个新的消息。
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void addData(E data) throws Exception;
	
	/**
	 * <p>
	 * 从队列中取出数据。
	 * 
	 * @return 如果没有取到数据的话，则返回null，最好不要抛出例外。
	 * @throws Exception
	 */
	public E getData() throws Exception;
	
	   /**
     * <p>
     * 从队列中取出数据。
     * 
     * @return 如果没有取到数据的话，则返回null，最好不要抛出例外。
     * @throws Exception
     */
    public E getData(long timeout) throws Exception;
	
	/**
	 * <p>
	 * 从队列中取出数据。
	 * @return 返回数据保存在{@link DataEntity}中，如果没有取到数据的话，则返回null，最好不要抛出例外。
	 * @throws Exception
	 */
	public DataEntity<E> getDataWithHolder()throws Exception;
	
	   /**
     * <p>
     * 从队列中取出数据。
     * @return 返回数据保存在{@link DataEntity}中，如果没有取到数据的话，则返回null，最好不要抛出例外。
     * @throws Exception
     */
    public DataEntity<E> getDataWithHolder(long timeout)throws Exception;
	
	/**
	 * <p>
	 * 删除队列中数据
	 * @param data
	 * @return
	 */
	public boolean remove(E data);


	/**
	 * 队列状态枚举类型
	 * <li>NEW    : 新建</li>
	 * <li>INITED : 已初始化</li>
	 * <li>STARTED: 已启动</li>
	 * <li>STOPPED: 已停止</li>
	 * @author river
	 * @date 20120912
	 */
	public static enum QueueStatus{
		NEW,INITED,STARTED,STOPPED;
	}

}
