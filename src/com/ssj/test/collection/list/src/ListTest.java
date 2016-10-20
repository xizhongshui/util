package com.ssj.test.collection.list.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import org.junit.Test;

import com.ssj.test.collection.list.struts.User;

public class ListTest {
	//@Test
	public void testListIterator(){
		ArrayList<String> list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		ListIterator<String> it=list.listIterator();
		if(it.hasNext()){
			System.out.println(it.next());
			System.out.println(it.previous());
		}
	}
	
	public void LinkedListTest(){
		LinkedList<User> ll=new LinkedList<User>();
	}

}
