package com.ssj.test.collection.iterator.src;


import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Test;
/**List中的Iterator
 * hasNext() 用于判断当前cursor是否越界
 * next() 	 返回当前cursor所指的值,并将cursor下移
 * hasPrevious() 用于判断cursor-1是否越界
 * previous() 返回当前cursor左侧的值,并将cursor上移
*/
public class IteratorTest {
	@Test
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

}
