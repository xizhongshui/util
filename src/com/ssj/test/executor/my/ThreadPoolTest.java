package com.ssj.test.executor.my;

import org.junit.Test;

import com.ssj.test.executor.my.sturts.MyTask;
import com.ssj.util.thread.ThreadFixedPoolManager;


public class ThreadPoolTest {
	
	@Test
	public void start(){
		MyTask task=new MyTask();
			ThreadFixedPoolManager.POOL_LOOP.scheduleTask(task,1000,2000);
	}
	public static void main(String[] args) {
		MyTask task=new MyTask();
		ThreadFixedPoolManager.POOL_LOOP.scheduleTask(task,1000,2000);
	}
	
}
