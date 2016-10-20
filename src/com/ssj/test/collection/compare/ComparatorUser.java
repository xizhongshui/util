package com.ssj.test.collection.compare;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorUser  {
	private int age;
	private String name;
	
	public ComparatorUser(){}
	
	public ComparatorUser(int age,String name){
		this.age=age;
		this.name=name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		UserComparator comparator=new UserComparator();
		ComparatorUser[] userArray=new ComparatorUser[]{
				new ComparatorUser(2,"2"),new ComparatorUser(1,"1"),new ComparatorUser(3,"3")
		};
		for(ComparatorUser user:userArray)
		{
			System.out.println("userName=//////////"+user.getName());
		}
		System.out.println("*******************************************");
		Arrays.sort(userArray,comparator);//传入比较器
		for(ComparatorUser user:userArray)
		{
			System.out.println("userName=//////////"+user.getName());
		}

	}
}
class UserComparator implements Comparator {//建立user的比较器
	public int compare(Object user1, Object user2) {
		return ((ComparatorUser)user1).getAge()-((ComparatorUser)user2).getAge();
	}
}
