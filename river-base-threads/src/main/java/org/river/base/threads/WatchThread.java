package org.river.base.threads;
/**
 * <p>
 * 监听线程抽象类
 * @author river
 * @date 20120912
 */
public abstract class WatchThread extends Thread {
	
	/*保存所有线程的线程组*/
	public static final ThreadGroup threadGroup = new ThreadGroup("watch-thread-group");
	
	/*线程是否存活的标志*/
	protected boolean alive;
	
	/*线程的状态*/
	private String status;
	
	/**
	 * <p>构造方法。
	 * 
	 * @param name 线程的名称
	 */
	public WatchThread(String name) {
		super(threadGroup, name);
		this.alive = true;
		this.status = "initialized";
	}
	
	/**
	 * <p>
	 * 停止线程的运行。执行此方法后，线程将会从线程体中退出，此线程将不能再被使用。如果还需要
	 * 使用相应线程的功能，需要重新new一个新的线程对象。
	 */
	public void kill() {
		this.alive = false;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * <p>
	 * 只有此线程有子类才可以设置自己的状态信息。
	 * @param status
	 */
	protected void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 线程方法
	 */
	public final void run() {
		this.status = "running";		
		while( this.alive ) {
			try {
				doRun();
			} catch(Exception ex) {
				ex.printStackTrace();
			} catch(Throwable t) {
				
			}
 		}
	}
	
	/**
	 * 定义线程业务逻辑
	 * @throws Exception
	 */
	public abstract void doRun() throws Exception; 
	
	/**
	 * 监听线程状态枚举
	 * <li>NONSTART：线程未开启</li>
	 * <li>RUNNING ：线程运行中</li>
	 * <li>KILLED  ：线程死亡</li>
	 * @author river
	 * @date 20120913
	 */
	public static enum WatchThreadStatus{
		NONSTART("not-start"),RUNNING("running"),KILLED("killed");
		private String value;
		
		public String getValue(){
			return this.value;
		}

		private WatchThreadStatus(String value) {
			this.value = value;
		}		
	}
}
