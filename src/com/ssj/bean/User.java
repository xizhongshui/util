package com.ssj.bean;

import java.util.List;

public class User 
{
	private String name;
	private int age;
	private String sex;
	private List<Book> books;
	
	public User(){}
	public User(String name,int age){
			this.name=name;
			this.age=age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
	
	

}
