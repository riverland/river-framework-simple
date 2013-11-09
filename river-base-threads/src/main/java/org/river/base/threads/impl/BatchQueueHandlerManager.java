package org.river.base.threads.impl;

import java.util.ArrayList;
import java.util.List;

import org.river.base.threads.IQueueHandler;
import org.river.base.threads.WatchThread;
import org.river.base.threads.type.DataEntity;

/**
 * 批处理队列处理管理器默认实现
 * 
 * @author river
 * 
 * @param <E>
 * @date 20120917
 */
public class BatchQueueHandlerManager<E> extends QueueHandlerManager<E> {

	/**批处理大小*/
	private int batchSize=500;
	
	/**最近一次操作时间*/
	private long lastProcessTime=0;

	/**处理刷新时间*/
	private long flushInterval=1000;
	
	/**一批*/
	List<DataEntity<E>> batch=new ArrayList<DataEntity<E>>();
	
	@Override
	protected Runnable getHandlerThread(DataEntity<E> data) {
		return new HandleThread(data);
	}


	@Override
	protected WatchThread getWatchThread() {
		return new BatchWatchThread();
	}
	
	public synchronized void setLastProcessTime(long lastProcessTime) {
		this.lastProcessTime = lastProcessTime;
	}
	
	public long getFlushInterval() {
		return flushInterval;
	}

	public void setFlushInterval(long flushInterval) {
		this.flushInterval = flushInterval;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	


	
	/**
	 * 批处理监控器
	 * <li>队列大小达到批次大小的时候才开始处理</li>
	 * <li>当前时间-队列上次处理时间>flushInterval,则开始进行处理</li>
	 * @author river
	 * @date 20120917
	 */
	class BatchWatchThread extends QueueManagerWhatchThread{
		public BatchWatchThread() {
			super("[BatchQueueHandlerManager:BatchWatchThread]");
		}
		
		@Override
		public void doRun() throws Exception {
			long currentTime=System.currentTimeMillis();
			
			DataEntity<E> ent=queue.getDataWithHolder(flushInterval);
			if(ent!=null){
				batch.add(ent);
			}
			
			//按批次处理
			if(batch.size()>=batchSize||(currentTime-lastProcessTime>flushInterval&&batch.size()>0)){
				setLastProcessTime(currentTime);
				executor.execute(new BatchHandleThread(batch));
				batch=new ArrayList<DataEntity<E>>();
			}
		}
		
	}
	
	
	/**
	 * 消息批处理处理线程
	 * @author river
	 * @date 20120913
	 */
	class BatchHandleThread implements Runnable{
		private List<DataEntity<E>> data;		
		
		public BatchHandleThread(List<DataEntity<E>>  data) {
			this.data = data;
		}

		public void run() {
			// 调用队列数据的处理对象对数据进行处理
			for(IQueueHandler<E> handler:handlers){
				try {
					for(DataEntity<E> ent:data){
						handler.handle(ent,false);
						
						//如果处理失败,则重新放回队列
						if(!ent.isEmpty()){
							queue.addData(ent.getData());
						}
					}
					
				} catch (Exception e) {
					//do nothing
				} finally{
					handler.flush();
				}
			}
		}
		
	}
}
