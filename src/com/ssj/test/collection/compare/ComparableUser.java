package com.ssj.test.collection.compare;

import java.util.Arrays;

public class ComparableUser implements Comparable {//   在javaBean实现comparable接口,并指定比较字段
	private int age;
	private String name;
	
	public ComparableUser(){}
	
	public ComparableUser(int age,String name){
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

	public int compareTo(Object o) {
		return this.age-((ComparableUser)o).getAge();//指定比较对象
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComparableUser[] userArray=new ComparableUser[]{
				new ComparableUser(2,"2"),new ComparableUser(1,"1"),new ComparableUser(3,"3")
		};
		for(ComparableUser user:userArray)
		{
			System.out.println("userName=//////////"+user.getName());
		}
		System.out.println("*******************************************");
		Arrays.sort(userArray);
		for(ComparableUser user:userArray)
		{
			System.out.println("userName=//////////"+user.getName());
		}

	}
}
