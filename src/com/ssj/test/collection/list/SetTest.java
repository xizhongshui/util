package com.ssj.test.collection.list;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
	public void add()
	{
		User user1=new User();
	}
	
	public static void main(String[] args)
	{
		 Set<String> set = new HashSet<String>();  
         
	        set.add(new String("11"));  
	        set.add(new String("222"));//无序性,没有随机访问的get()方法
	        for(String value:set)
	        {
	        	System.out.println(value);
	        }
	}

}

class User
{
	private String name;
	public User(){}
	public User(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}