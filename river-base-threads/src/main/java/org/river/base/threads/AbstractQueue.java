package org.river.base.threads;

/**
 * <P>
 * 队列抽象实现
 * @author river
 *
 * @param <E>
 */
public abstract class AbstractQueue<E> implements IQueue<E> {
	/**队列大小*/
	protected int queueSize;
	
	/**数据超时时间*/
	protected long dataTimeout;
	
	/**检察时间间隔*/
	protected long checkInterval;	
	
	/**
	 * 默认构造器
	 */
	public AbstractQueue() {
	}

	/**
	 * 带参数构造器
	 * @param queueSize 队列最大长度
	 * @param dataTimeout 队列中数据超时时间
	 * @param checkInterval 检查时间间隔
	 */
	public AbstractQueue(int queueSize, long dataTimeout, long checkInterval) {
		this.queueSize = queueSize;
		this.dataTimeout = dataTimeout;
		this.checkInterval = checkInterval;
	}

	public void setQueueSize(int size) {
		this.queueSize=size;
	}

	public int getQueueSize() {
		return this.queueSize;
	}

	public void setDataTimeout(long timeout) {
		this.dataTimeout=timeout;
	}

	public long getDataTimeout() {
		return this.dataTimeout;
	}

	public long getCheckInterval() {
		return this.checkInterval;
	}

	public void setCheckInterval(long checkInterval) {
		this.checkInterval=checkInterval;
	}
	
}
