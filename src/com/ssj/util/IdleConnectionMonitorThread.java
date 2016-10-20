package com.ssj.util;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.log4j.Logger;

public class IdleConnectionMonitorThread implements Runnable{
			Logger logger=Logger.getLogger(IdleConnectionMonitorThread.class);
			private final PoolingHttpClientConnectionManager connMgr;
		    private volatile boolean shutdown;
		
		    public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
		        super();
		        this.connMgr = connMgr;
		    }
		
		    @Override
		    public void run() {
		        try {
		        			logger.info("唯一线程开启无效连接检测...........................................................................................");
				            while (!shutdown) {
				                synchronized (this) {
					                    wait(1000);
					                   PoolStats stats= connMgr.getTotalStats();
					                   logger.info("max Count==="+stats.getMax());
					                   logger.info("excute Count==="+stats.getLeased());
					                   logger.info("Available Count==="+stats.getAvailable());
					                   logger.info("pending Count==="+stats.getPending());
					                    // 关闭失效的连接
					                    connMgr.closeExpiredConnections();
					                    // 可选的, 关闭30秒内不活动的连接
					                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
				                }
				            }
		        } catch (InterruptedException ex) {
		            // terminate
		        }
		    }
		
		    public void shutdown() {
		    			logger.info("关闭无效连接检测...........................................................................................");
				        shutdown = true;
				        synchronized (this) {
				            notifyAll();
				        }
		    }

}
