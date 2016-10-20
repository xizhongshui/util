package com.ssj.test.collection.queue.my;

import java.util.NoSuchElementException;

public class LoopQueue<T> {
	private Object elementData[];
	private int defaultCapacity=10;
	private int front ;
	private int rear;
	
	public LoopQueue(){
		elementData=new Object[defaultCapacity];
	}
	
	public LoopQueue(int capacity){
		elementData=new Object[capacity];
	}
	
	public boolean isFull(){
		if(rear==front && elementData[rear]!=null)
			return true;
		return false;
	}
	
	public boolean isEmpty(){
		if(rear==front && elementData[front]==null)
			return true;
		return false;
	}
	
	public int getSize(){
		if(isEmpty())
			return 0;
		
		return rear>front?rear-front:elementData.length - (front - rear);
	}
	
	
	public boolean add(T ele){
		if(isFull())
			throw new IllegalStateException("队列已满");
		elementData[rear++]=ele;
		rear=rear==elementData.length?0:rear;
		return true;
	}
	
	public T remove(){
		if(isEmpty())
			throw new NoSuchElementException("队列为空");
		T ele=(T)elementData[front++];
		front=front==elementData.length?0:front;
		return ele;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
