package com.ssj.util.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public enum ThreadPoolManager {
	Global_Pool(2,"global_pool");
	
	private String name;
	private int count;
	private ScheduledThreadPoolExecutor executor;
	private ThreadPoolManager(int count,String name){
		this.name=name;
		this.count=count;
		executor=new ScheduledThreadPoolExecutor(count, new ThreadFactoryImp(name));
		executor.setRejectedExecutionHandler(new ServerRejectedHandler());
	}
}
