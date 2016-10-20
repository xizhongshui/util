package com.ssj.test.executor.my;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.ssj.test.executor.my.sturts.MyTask;

public class ThreadPoolExcutorTest {
	
	
	public static void main(String[] args) {
		ArrayBlockingQueue worksQueue=new ArrayBlockingQueue(2,true);
		ThreadPoolExecutor executor=new ThreadPoolExecutor(2,4,3,TimeUnit.SECONDS,worksQueue,new ThreadPoolExecutor.DiscardPolicy());
		for(int i=0;i<10;i++){
			String task="task ------"+i;
			System.out.println("提交任务  ---"+task);
			executor.execute(new MyTask(task));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
