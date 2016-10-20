
package com.ssj.util.thread;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Hades
 *
 */
public enum ThreadFixedPoolManager{
	POOL_LOOP		(3, "loop-thread");
	
	private int 						count;		// 线程数量
	private String 						name;		// 线程池名称
	private ScheduledThreadPoolExecutor executor;	// 线程池执行器
	
	private ThreadFixedPoolManager(int count, String name){
		this.count = count;
		this.name = name;
		executor = new ScheduledThreadPoolExecutor(count);
		ThreadFactoryImpl factory = new ThreadFactoryImpl(executor, name);
		executor.setThreadFactory(factory);
		executor.setRejectedExecutionHandler(new ServerRejectedExecutionHandler());
		executor.prestartAllCoreThreads();
	}
	
	public int getCount(){
		return count;
	}
	
	public String getName(){
		return name;
	}
	
	public ScheduledThreadPoolExecutor getScheduledExecutor(){
		return this.executor;
	}
	
	public final ScheduledFuture<?> schedule(Runnable r, long delay){
		return  executor.schedule(r, delay, TimeUnit.MILLISECONDS);
	}
	
	public final ScheduledFuture<?> execute(Runnable r){
		return  executor.schedule(r, 0, TimeUnit.MILLISECONDS);
	}
	
	public final ScheduledFuture<?> scheduleTask(Runnable r,long delay, long period){
		return executor.scheduleAtFixedRate(r, delay, period, TimeUnit.MILLISECONDS);
	}
}
