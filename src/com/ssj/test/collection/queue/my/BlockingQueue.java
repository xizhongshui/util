package com.ssj.test.collection.queue.my;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
	private int defaultCapacity=5;
	private Object elementData[];
	private int front;
	private int rear;
	private int count;
	
	private Lock lock=new ReentrantLock();
	private Condition notFull=lock.newCondition();
	private Condition notEmpty=lock.newCondition();
	
	public BlockingQueue(){
		elementData=new Object[defaultCapacity];
	}
	
	public BlockingQueue(int capacity){
		elementData=new Object[capacity];
	}
	
	private int inc(int index){
		return (++index==elementData.length)?0:index;
	}
	
	@SuppressWarnings("unchecked")
	private T cast(Object ele){
		return (T)ele;
	}
	
	private T extract(){
		T result=cast(elementData[front]);
		elementData[front]=null;
		front=inc(front);
		--count;
		notFull.signalAll();
		return result;
	}
	
	private void insert(T ele){
		elementData[rear]=ele;
		rear=inc(rear);
		++count;
		notEmpty.signalAll();
	}
	public T get() throws InterruptedException{
		lock.lockInterruptibly();
		try{
			while(count==0)
				notEmpty.await();
			return extract();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void put(T ele) throws InterruptedException{
		lock.lockInterruptibly();
		try{
			while(count==elementData.length)
				notFull.await();
			insert(ele);
		}
		finally{
			lock.unlock();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
