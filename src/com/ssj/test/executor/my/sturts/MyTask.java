package com.ssj.test.executor.my.sturts;

public class MyTask implements Runnable{

	private String task;
	public MyTask(){}
	public MyTask(String task){
		this.task=task;
	}
	@Override
	public void run() {
		System.out.println("开始执行任务：" + task);  
	}
	
}

