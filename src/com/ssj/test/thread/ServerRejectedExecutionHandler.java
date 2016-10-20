package com.ssj.test.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;


/**
 * 拒接执行任务时的处理
 * @author Hades
 *
 */
public final class ServerRejectedExecutionHandler implements RejectedExecutionHandler
{
	private static final Logger	logger	= Logger.getLogger(ServerRejectedExecutionHandler.class);
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
	{
		if(executor.isShutdown())
			return;

		logger.warn(r + " from " + executor, new RejectedExecutionException());
/*
		if(Thread.currentThread().getPriority() > Thread.NORM_PRIORITY)
			new Thread(r).start();
		else*/
			return;
	}
}
