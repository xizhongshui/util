package com.ssj.test.executor.my.sturts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	public static void main(String[] args) {
		TaskCallable task=new TaskCallable();
		
		ExecutorService executor=Executors.newScheduledThreadPool(3);
		try {
			Future<Integer> futrue=executor.submit(task);
			futrue.get(2,TimeUnit.SECONDS);
			futrue.cancel(true);
			executor.shutdown();
			//int result=task.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main ...............");
	}

}
