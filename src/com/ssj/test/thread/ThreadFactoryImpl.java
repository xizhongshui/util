package com.ssj.test.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryImpl implements ThreadFactory {
	/**
	 * Thread group name
	 */
	private String name;
	
	/**
	 * Number of created threads
	 */
	private AtomicInteger threadNumber = new AtomicInteger(1);
	
	/**
	 * ThreadGroup for created threads
	 */
	private ThreadGroup group;

	
	/**
	 * owner
	 */
	private ThreadPoolExecutor executor;

	public ThreadFactoryImpl( ThreadPoolExecutor executor, String name) {
		this.executor = executor;
		this.name = name;
		group = new ThreadGroup(this.name);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r);
		t.setName(name + "-" + threadNumber.getAndIncrement());
		t.setUncaughtExceptionHandler(new ThreadUncaughtExceptionHandler());

		return t;
	}
	
	public ThreadGroup getThreadGroup(){
		return group;
	}
}
