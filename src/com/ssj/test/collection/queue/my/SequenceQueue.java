package com.ssj.test.collection.queue.my;

import java.util.NoSuchElementException;

public class SequenceQueue<T> {
	private Object[] elementData;
	private int defaultCapacity=1;
	private int front;
	private int rear;
	
	public SequenceQueue(){
		elementData=new Object[defaultCapacity];
	}
	
	public SequenceQueue(T element){
		this();
		elementData[0]=element;
		rear++;
	}
	
	public SequenceQueue(int capacity){
		elementData=new Object[capacity];
	}
	public boolean isFull(){
		if(rear>elementData.length-1)
			return true;
		return false;
	}
	
	public boolean isEmpty(){
		if(rear==front && elementData[front]==null)
			return true;
		return false;
			
	}
	
	public int getSize(){
		return rear-front;
	}
	
	public boolean add(T ele){
		if(isFull())
			throw new IllegalStateException("队列已满无法插入");
		elementData[rear++]=ele;
		return true;
	}
	
	public boolean offer(T ele){
		if(isFull())
			return false;
		elementData[rear++]=ele;
		return true;
	}
	
	public T remove(){
		if(isEmpty())
			throw new NoSuchElementException("队列为空");
		return (T)elementData[front++];
	}
	
	
	public T poll(){
		if(isEmpty())
			return null;
		return (T)elementData[front++];
	}
}
