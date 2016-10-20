package com.ssj.util.thread;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryImp implements ThreadFactory {

	private String name;
	private ThreadGroup group;
	
	
	public ThreadFactoryImp(String name) {
		this.name = name;
		this.group = new ThreadGroup(name);
	}


	@Override
	public Thread newThread(Runnable r) {
		return new Thread(group, r);
	}

}
