package com.ssj.util.thread;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.Logger;

/**
 * 处理线程中的未捕获异常
 * @author Hades
 *
 */
public class ThreadUncaughtExceptionHandler implements UncaughtExceptionHandler
{
	/**
	 * Logger for this class.
	 */
	private static final Logger	log	= Logger.getLogger(ThreadUncaughtExceptionHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e)
	{
		log.error("Critical Error - Thread: " + t.getName() + " terminated abnormaly: " + e, e);
		if(e instanceof OutOfMemoryError)
		{
			// TODO try get some memory or restart
			log.error("Out of memory! You should get more memory!");
		}
		// TODO! some threads should be "restarted" on error
	}
}
