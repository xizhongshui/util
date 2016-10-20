package com.ssj.test.collection.queue.my;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class QueueTest {

	//@Test //SequenceQueue  add
	public void SequenceQueueTest(){
		SequenceQueue<String> sq=new SequenceQueue<String>();
		sq.add("add");
		sq.offer("add2");
		System.out.println("size:"+sq.getSize());
		System.out.println(sq.remove());
		System.out.println(sq.poll());
		System.out.println("size:"+sq.getSize());
		System.out.println();
	}
	
	@Test //BlockingQueue
	public void BlockingQueueTest(){
		class Producer implements Runnable {
			private BlockingQueue<Integer> container;
			private int i;
			Producer(BlockingQueue<Integer> container,int i){
				this.container=container;
				this.i=i;
			}
			@Override
			public void run() {
				try {
					Thread.sleep(2);
					container.put(i);
					System.out.println("生产者 -------"+i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		class Consumer implements Runnable {
			private BlockingQueue<Integer> container;
			Consumer(BlockingQueue<Integer> container){
				this.container=container;
			}
			@Override
			public void run() {
				try {
					//Thread.sleep(1);
					int result=container.get();
					System.out.println("消费者 -------"+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		ExecutorService exe=Executors.newCachedThreadPool();
		BlockingQueue<Integer> container=new BlockingQueue<Integer>();
		for(int i=0;i<10;i++){
			exe.execute(new Producer(container,i));
		}
		
		for(int i=0;i<5;i++){
			exe.execute(new Consumer(container));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
