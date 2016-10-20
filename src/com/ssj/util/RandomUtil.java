package com.ssj.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class RandomUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Random random=new Random();
		for(int i=0;i<50;i++)
			System.out.println(random.nextInt(100)+1);
	}
	
	@Test
	public void randomFor4(){
		for(int i=0;i<50;i++){
			double base=Math.random();
			double result=base*9000+1000;
			System.out.println((int)result);
		}
		
		/*Random random=new Random();
		for(int i=0;i<50;i++){
			float base=random.nextFloat()*10000;
			System.out.println((int)base);
		}*/
		
	}
	@Test
	public void randomNoRepeate(){
		List<Integer> baseList=new ArrayList<Integer>();
		baseList.add(1);baseList.add(2);baseList.add(3);
		baseList.add(4);baseList.add(5);baseList.add(6);
		baseList.add(7);baseList.add(8);baseList.add(9);
		baseList.add(10);baseList.add(11);baseList.add(12);
		baseList.add(13);baseList.add(14);baseList.add(15);
		baseList.add(16);baseList.add(17);baseList.add(18);
		baseList.add(19);baseList.add(20);baseList.add(21);
		baseList.add(22);baseList.add(23);
		Random random=new Random();
		for(int i=0;i<7;i++){
			int index=random.nextInt(baseList.size());
			String key=baseList.get(index)+"";
			baseList.remove(index);
		}
	}

}
