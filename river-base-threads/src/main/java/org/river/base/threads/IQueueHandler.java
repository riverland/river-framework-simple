package org.river.base.threads;

import org.river.base.threads.type.DataEntity;

/**
 * 队列处理器接口
 * @author river
 *
 * @param <E>
 * @date 20120913
 */
public interface IQueueHandler<E> {
	/**
	 * <p>
	 * 处理队列中数据的接口。
	 * 此接口不抛出任何异常，否则将异常将被忽略
	 * @param message
	 */
	public void handle(DataEntity<E> data);
	
	/**
	 * <p>
	 * 处理队列中数据的接口。做批处理或异步时实现该接口
	 * 此接口不抛出任何异常，否则将异常将被忽略
	 * @param data
	 * @param flush 处理业务是否立即执行
	 */
	public void handle(DataEntity<E> data,boolean flush);
	
	/**
	 * <p>
	 * 处理所有未进行的handle调用，做批处理或异步时实现该接口
	 */
	public void flush();
	
	/**
	 * <p>
	 * 获取处理类类型
	 * @return
	 */
	public Class<?> getDataType();
}
