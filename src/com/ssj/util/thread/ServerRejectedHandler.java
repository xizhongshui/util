package com.ssj.util.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerRejectedHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		if(executor.isShutdown()){
			return;
		}
		if(Thread.currentThread().getPriority()>Thread.NORM_PRIORITY){
			new Thread(r).start();
		}else{
			r.run();
		}
	}

}
