package org.river.base.threads;

public interface IQueueInfo<E> {
	/**
	 * <p>
	 * 设置队列的大小。
	 * 
	 * @param size
	 */
	public void setQueueSize(int size);

	/**
	 * <p>
	 * 取得队列的大小。
	 * 
	 * @return
	 */
	public int getQueueSize();

	/**
	 * <p>
	 * 设置消息的超时时间，以秒为单位。
	 * 
	 * @param timeout
	 */
	public void setDataTimeout(long timeout);

	/**
	 * <p>
	 * 取得消息的超时时间。
	 * 
	 * @return
	 */
	public long getDataTimeout();

	/**
	 * <p>
	 * 取得读队列的间隔时间，在此间隔时间后，如果没有读到数据，会给予调用方执行的机会。
	 * 
	 * @return
	 */
	public long getCheckInterval();

	/**
	 * <p>
	 * 设置读数据的间隔时间。
	 * 
	 * @param checkInterval
	 */
	public void setCheckInterval(long checkInterval);

	/**
	 * <p>
	 * 取得队列中的当前的数据数量。
	 * 
	 * @return
	 */
	public int getCurrentSize();
}
