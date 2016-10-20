package com.ssj.test.collection.queue.src;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.ssj.test.collection.queue.struts.User;

public class QueueTest {

//BlockingQuue:ArrayBlockingQueue
	//@Test 
	public void ArrayBlockingQueueTest(){
		User user1=new User(1,"a");
		User user2=new User(2,"b");
		User user3=new User(3,"c");
		ArrayBlockingQueue<User> blockArray=new ArrayBlockingQueue<User>(1,true);
		blockArray.remove(user3);
		blockArray.add(user1);
		blockArray.add(user2);
		
	}
	
	@Test 
	public void PriorityQueueTest(){
		/*User user1=new User(1,"a");
		User user2=new User(2,"b");
		User user3=new User(3,"c");
		PriorityQueue<User> queue=new PriorityQueue<User>();*/
		
	}
	
}
