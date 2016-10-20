package com.ssj.test.arithmetic;

import org.junit.Test;

public class Recursion {
	
	private int  recursioin1(int n,int i)
	{
		
		int secon=(int)Math.pow(2,i);
		i++;
		if(n*secon<1000)
			recursioin1(n,i);
		System.out.println(n*secon);
		return (n*secon);
	}
	
	private void   recursioin2(int n)//递归值
	{
		if(n<1024)
			 recursioin2(n*2);
		System.out.println(n);
	}
	
	
	private int recursioin3(int n,int k)
	{
		int value=n;
		if(k<8)
		{
			k++;
			value=recursioin3(n+2,k);
		}
		return value;
	}
	
	private int recursioin5(int n)//递归次数
	{
		if(n==1) return 10;
		else return recursioin5(n-1)*2;
	}
	
	@Test
	public void testRecursion1()
	{
		int vaule=this.recursioin5(3);
		System.out.println(vaule);
	}

}
