package org.river.base.threads.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.river.base.threads.IQueue;
import org.river.base.threads.IQueueHandler;
import org.river.base.threads.IQueueHandlerManager;
import org.river.base.threads.WatchThread;
import org.river.base.threads.type.DataEntity;

/**
 * 队列处理管理器基础实现
 * 
 * @author river
 * 
 * @param <E>
 * @date 20120913
 */
public abstract class QueueHandlerManager<E> implements IQueueHandlerManager<E> {

	/** 数据队列 */
	protected IQueue<E> queue;

	/** 队列中数据处理器对象的列表 */
	protected List<IQueueHandler<E>> handlers;

	/** 队列数据处理监听线程 */
	protected WatchThread watchThread;
	
	/**管理器状态*/
	protected ManagerStatus managerStatus;


	/** 处理线程池配置 */
	protected int coreHandlerPoolSize = 30;
	protected int maxHandlerPoolSize = 50;
	protected int handleThreadKeepAliveTime = 30;
	protected int handlerPoolQueueSize=2000;


	/**
	 * <p>
	 * 构造器
	 */
	public QueueHandlerManager() {
		super();
		this.handlers = new LinkedList<IQueueHandler<E>>();
	}

	/**
	 * 构造器
	 * 
	 * @param queue
	 */
	public QueueHandlerManager(IQueue<E> queue) {
		this();
		if (queue == null) {
			queue=new Queue<E>();
		} else {
			this.queue = queue;
		}
		this.managerStatus=ManagerStatus.NEW;
	}
	
	/**
	 * <p>
	 * 获取处理线程
	 * 主要为子类实现
	 * @return
	 */
	protected abstract Runnable  getHandlerThread(DataEntity<E> data);
	

	/**
	 * <p>
	 * 获取监控线程
	 * @return
	 */
	protected abstract  WatchThread getWatchThread() ;

	
	public void setQueue(IQueue<E> queue) {
		this.queue = queue;
	}

	public void addQueueHandler(IQueueHandler<E> handler) {
		this.handlers.add(handler);
	}

	public void addQueueHandlers(List<? extends IQueueHandler<E>> handlers) {
		this.handlers.addAll(handlers);
	}

	public void init() throws Exception {
		watchThread=getWatchThread();
		queue.init();
		this.managerStatus=ManagerStatus.INITED;
	}

	public void start() throws Exception {
		queue.start();
		watchThread.start();
		this.managerStatus=ManagerStatus.STARTED;
	}

	public void stop() throws Exception {
		queue.stop();
		watchThread.kill();
		this.managerStatus=ManagerStatus.STOPPED;
	}


	public void addData(E data) throws Exception {
		queue.addData(data);
	}

	public List<IQueueHandler<E>> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<IQueueHandler<E>> handlers) {
		this.handlers = handlers;
	}


	public int getCoreHandlerPoolSize() {
		return coreHandlerPoolSize;
	}

	public void setCoreHandlerPoolSize(int coreHandlerPoolSize) {
		this.coreHandlerPoolSize = coreHandlerPoolSize;
	}

	public int getMaxHandlerPoolSize() {
		return maxHandlerPoolSize;
	}

	public void setMaxHandlerPoolSize(int maxHandlerPoolSize) {
		this.maxHandlerPoolSize = maxHandlerPoolSize;
	}

	public int getHandleThreadKeepAliveTime() {
		return handleThreadKeepAliveTime;
	}

	public void setHandleThreadKeepAliveTime(int handleThreadKeepAliveTime) {
		this.handleThreadKeepAliveTime = handleThreadKeepAliveTime;
	}

	public IQueue<E> getQueue() {
		return queue;
	}
	

	public int getHandlerPoolQueueSize() {
		return handlerPoolQueueSize;
	}

	public void setHandlerPoolQueueSize(int handlerPoolQueueSize) {
		this.handlerPoolQueueSize = handlerPoolQueueSize;
	}
	

	public ManagerStatus getManagerStatus() {
		return managerStatus;
	}

	/**
	 * 队列处理管理器监听线程
	 * 
	 * @author river
	 * @date 20120912
	 */
	class QueueManagerWhatchThread extends WatchThread {
		protected ExecutorService executor;

		public QueueManagerWhatchThread() {
			this("[QueueHandlerManager:QueueManagerWhatchThread]");			
		}
		

		public QueueManagerWhatchThread(String name) {
			super(name);
			BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(
					handlerPoolQueueSize);
			this.executor = new ThreadPoolExecutor(coreHandlerPoolSize, maxHandlerPoolSize, handleThreadKeepAliveTime,
					TimeUnit.SECONDS, workQueue);
		}


		public void doRun() throws Exception {
			try {
				// 尝试从队列中读数据
				DataEntity<E> dataHolder=queue.getDataWithHolder(3000);
				if (dataHolder == null||dataHolder.isEmpty()) {
					// 没有取到数据，则返回
					return;
				}
				
				this.executor.execute(getHandlerThread(dataHolder));

				
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * 消息处理线程
	 * @author river
	 * @date 20120913
	 */
	class HandleThread implements Runnable{
		private DataEntity<E> data;		
		
		public HandleThread(DataEntity<E> data) {
			super();
			this.data = data;
		}

		public void run() {
			// 调用队列数据的处理对象对数据进行处理
			for(IQueueHandler<E> handler:handlers){
				handler.handle(data);
			}			

			//如果处理失败,则重新放回队列
			if(!data.isEmpty()){
				try {
					queue.addData(data.getData());
				} catch (Exception e) {
					// do nothing
				}
			}
		}
		
	}
	

	
	/**
	 * 状态枚举类型
	 * <li>NEW    : 新建</li>
	 * <li>INITED : 已初始化</li>
	 * <li>STARTED: 已启动</li>
	 * <li>STOPPED: 已停止</li>
	 * @author river
	 * @date 20120912
	 */
	public static enum ManagerStatus{
		NEW,INITED,STARTED,STOPPED;
	}

}
