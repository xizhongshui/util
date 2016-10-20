package com.ssj.util;

import java.util.ArrayList;
import java.util.List;

public class ListTest 
{
	public void list1(List<Integer> list)
	{
	/*	for(int k:list)
		{
			k=k+1;
		}*/
		for(int i=0;i<list.size();i++)
		{
		}
		for(int k:list)
		{
			System.out.println(k);
		}
	}
	public static void main(String[]args)
	{
		ListTest test=new ListTest();
		List list=new ArrayList<Integer>();
		list.add(1);list.add(2);list.add(3);list.add(4);
		test.list1(list);
	}

}
