package com.ssj.test.executor.my.sturts;

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Integer> {
	public TaskCallable(){}
	public Integer call() throws Exception {
		
		Random rand=new Random();
		while(true){
			int k=rand.nextInt(100);
			if(k==99){
				System.out.println("run return result");
				return Integer.valueOf(k);
			}
				
		}
	}

}
