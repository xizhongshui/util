package com.ssj.test.thread.test;


/** ******************  类说明  *********************
 * @Description: 检测活利宝虚拟订单提现状态
 * ************************************************/
public class CheckInviteForHLBTask implements Runnable {
	private String name="";
	
	public CheckInviteForHLBTask(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("任务名称===="+name);
	}
}
