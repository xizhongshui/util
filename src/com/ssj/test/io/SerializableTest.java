package com.ssj.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SerializableTest {
	
	//@Test
	public void saveSerializeObj()
	{
		File file=new File("E:/seriaObj.txt");
		User user1=new User("user1");
		User user2=new User("user2");
		List<User> users=new ArrayList<User>();
		users.add(user1);users.add(user2);
		
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("E:/seriaObj.txt"));//序列化输出流
			out.writeObject(user1);
			out.writeObject(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSerializeObj()
	{
		User user=null;
		List<User> users=null;
		try {
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:/seriaObj.txt"));//反序列化输入流
			user=(User)in.readObject();
			users=(List<User>)in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user!=null)
			System.out.println(user.getName());
		if(users!=null)
			System.out.println(users.size());
	}
	
}

class User implements Serializable
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
