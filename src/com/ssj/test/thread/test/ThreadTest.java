package com.ssj.test.thread.test;

import com.ssj.test.thread.ThreadFixedPoolManager;

public class ThreadTest {
	
	private void startCheckInviteForHLBTask(CheckInviteForHLBTask task,long delay,long priod){
		ThreadFixedPoolManager.POOL_LOOP.scheduleTask(task, delay,priod);
	}
	
	public static void main(String[] args) {
		ThreadTest test=new ThreadTest();
		CheckInviteForHLBTask task1=new CheckInviteForHLBTask("1");
		CheckInviteForHLBTask task2=new CheckInviteForHLBTask("2");
		CheckInviteForHLBTask task3=new CheckInviteForHLBTask("3");
		test.startCheckInviteForHLBTask(task1,1000*3,1000*1);
		test.startCheckInviteForHLBTask(task2,1000*3,1000*2);
		test.startCheckInviteForHLBTask(task3,1000*5,1000*2);
	}
}
