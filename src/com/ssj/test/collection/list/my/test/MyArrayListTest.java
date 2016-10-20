package com.ssj.test.collection.list.my.test;

import java.util.ArrayList;

import org.junit.Test;

import com.ssj.test.collection.iterator.my.MyIterator;
import com.ssj.test.collection.iterator.my.MyListIterator;
import com.ssj.test.collection.list.my.MyArrayList;
import com.ssj.test.collection.list.struts.User;

public class MyArrayListTest {
	
	//@Test
	public void myIteratorTest(){
		MyArrayList<User> myArrayList=new MyArrayList<User>();
		User user1=new User(1, "name1");
		User user2=new User(2, "name2");
		User user3=new User(3, "name3");
		User user4=new User(4, "name4");
		myArrayList.add(user1);
		myArrayList.add(user2);
		myArrayList.add(user3);
		myArrayList.add(user4);
		
		MyIterator<User> myIter=myArrayList.iterator();
		while(myIter.hasNext()){
			User user=myIter.next();
			if(user.getUserID()==1)
				myIter.remove();
		}
	}
	
	@Test
	public void myListIteratorTest(){
		MyArrayList<User> myArrayList=new MyArrayList<User>();
		User user1=new User(1, "name1");
		User user2=new User(2, "name2");
		User user3=new User(3, "name3");
		User user4=new User(4, "name4");
		myArrayList.add(user1);
		myArrayList.add(user2);
		myArrayList.add(user3);
		myArrayList.add(user4);
		
		MyListIterator<User> myIter=myArrayList.listIterator();
		myIter.next();
		myIter.previous();
		myIter.remove();
		for(int i=0;i<myArrayList.size();i++){
			System.out.println(myArrayList.get(i).getName());
		}
	}
	
	@Test
	public void testNull(){
			ArrayList<User> myArrayList=null;//wrong
			for(User user:myArrayList){
					user.getName();
			}
	}

}
