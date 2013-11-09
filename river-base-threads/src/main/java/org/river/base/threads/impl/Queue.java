package org.river.base.threads.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.river.base.threads.AbstractQueue;
import org.river.base.threads.IQueueEvent;
import org.river.base.threads.IQueueEvent.EventType;
import org.river.base.threads.IQueueListener;
import org.river.base.threads.WatchThread;
import org.river.base.threads.type.DataEntity;
import org.river.base.threads.type.Message;
import org.river.base.threads.type.QueueEvent;
/**
 * 消息队列的基本实现
 * 可以对{@code Queue} 进行拓展为自己需要的队列，比如优先级队列等
 * @author river
 *
 * @param <E>
 */
public class Queue<E> extends AbstractQueue<E> {
	/**默认队列大小*/
	private static final int QUEUE_SIZE_DEFAULT=1024;	
	/**默认数据超时时间：5分钟*/
	private static final long TIMEOUT_DEFAULT=60*5;
	/**默认队列超时检查时间,时间单位毫秒*/
	private static final long CHECK_INTERVAL_DEFAULT=3000;

	
	/**队列监听器列表*/
	private List<IQueueListener<E>> listeners;
	
	/**队列数据列表*/
	private BlockingQueue<DataEntity<E>> dataQueue;
	
	/**队列状态，初始*/
	@SuppressWarnings("unused")
	private QueueStatus status;
	
	/**队列检查线程*/
	private CheckThread checkThread;	
	
	
	
	public Queue() {
		super();
		this.queueSize=QUEUE_SIZE_DEFAULT;
		this.dataTimeout=TIMEOUT_DEFAULT;
		this.checkInterval=CHECK_INTERVAL_DEFAULT;
		this.status=QueueStatus.NEW;
		this.listeners=new LinkedList<IQueueListener<E>>();
		dataQueue=new LinkedBlockingQueue<DataEntity<E>>(this.queueSize);
	}


	public Queue(int queueSize, long dataTimeout, long checkInterval){
		super();
		this.queueSize=queueSize<=0?QUEUE_SIZE_DEFAULT:queueSize;
		this.dataTimeout=dataTimeout<=0?TIMEOUT_DEFAULT:dataTimeout;
		this.checkInterval=checkInterval<=0?CHECK_INTERVAL_DEFAULT:checkInterval;
		this.status=QueueStatus.NEW;
		this.listeners=new LinkedList<IQueueListener<E>>();
		dataQueue=new LinkedBlockingQueue<DataEntity<E>>(this.queueSize);
	}
	

	public void init() throws Exception {
		checkThread=new CheckThread();
		IQueueListener<E> last=null;
		for(IQueueListener<E> tmp:listeners){
			tmp.setListenQueue(this);
			if(last!=null){
				last.setNext(tmp);
			}
		}
		
		this.status=QueueStatus.INITED;
	}

	public void start() throws Exception {
		checkThread.start();
		status=QueueStatus.STARTED;
	}

	public void stop() throws Exception {
		checkThread.kill();
		status=QueueStatus.STOPPED;
	}
	


	/**
	 * TODO 当队列满载时的逻辑处理
	 */
	public void addData(E data) throws Exception {
		DataEntity<E> holder = new DataEntity<E>(data);
		this.dataQueue.add(holder);
	}


	public E getData() throws Exception {
		E data = null;
		DataEntity<E> holder = this.dataQueue.take();
		if (holder != null) {
			data = holder.getData();
		}
		return data;
	}
	
	


	public boolean remove(E data) {
		boolean removed=false;
		Iterator<DataEntity<E>> iter = this.dataQueue.iterator();
		while (iter.hasNext()) {
			DataEntity<E> holder = iter.next();
			if(holder.getData()==data){
				iter.remove();
				removed=true;
				break;
			}
		}
		return removed;
	}


    /* (non-Javadoc)
     * @see org.river.base.threads.IQueue#getData(long)
     */
    public E getData(long timeout) throws Exception {
        return this.getDataWithHolder(timeout).getData();
    }


    /* (non-Javadoc)
     * @see org.river.base.threads.IQueue#getDataWithHolder(long)
     */
    public DataEntity<E> getDataWithHolder(long timeout) throws Exception {        
        return this.dataQueue.poll(timeout, TimeUnit.MILLISECONDS);
    }

	public DataEntity<E> getDataWithHolder() throws Exception {
		return this.dataQueue.take();
	}

	public void addListener(IQueueListener<E> listener) {
		this.listeners.add(listener);
	}

	public int getCurrentSize() {
		return this.dataQueue.size();
	}

	/**
	 * TODO 现在只是触发超时事件
	 */
	public void fire(IQueueEvent event) {
		@SuppressWarnings("unchecked")
		Message<E> msg=(Message<E>) event.why();
		E data=null;
		if(msg!=null){
			data=msg.dataObj;
		}		
		
		IQueueListener<E> listener=null;
		if(listeners!=null&&listeners.size()>0){
			listener=listeners.get(0);
		}
		if(listener!=null){
			if(EventType.TIMEOUT.equals(event.what())){
				listener.onTimeout(data);
			}		
		}

	}
	
	/**
	 * 间隔时间检查
	 */
	protected void check(){
		doTimeoutCheck();
		//TODO 空队列检查
	}
	
	/**
	 * 队列数据超时检查
	 */
	protected void doTimeoutCheck(){
		Iterator<DataEntity<E>> iter = this.dataQueue.iterator();
		long current = System.currentTimeMillis();
		while (iter.hasNext()) {
			DataEntity<E> holder = iter.next();
			long update = holder.getUpdateTime().getTime();
			if (current - update > this.dataTimeout) {
				Message<E> msg = new Message<E>();
				msg.dataObj = holder.getData();
				IQueueEvent event = new QueueEvent(EventType.TIMEOUT, msg);
				this.fire(event);
			}
		}
	}
	
	
	
	/**
	 * <p>
	 * 队列检查线程
	 * @author river
	 * date 20120912
	 */
	class CheckThread extends WatchThread{

		public CheckThread() {
			super("[Queue:CheckThread]");
		}

		@Override
		public synchronized  void doRun() throws Exception {
			try {
			    Thread.sleep(checkInterval);
			} catch (Exception e) {
			}

			// 对消息进行定时检查
			check();
		}
		
	}





}
