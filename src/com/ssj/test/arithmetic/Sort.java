package com.ssj.test.arithmetic;


import org.junit.Test;

public class Sort {
	
	//@Test
	public void selectionSort()
	{
		int [] source={1,7,6,8,0,4};
		for(int i=0;i<source.length;i++)
		{
			int temp_;
			int j=i;
			for(int k=i+1;k<source.length;k++)
			{
				if(source[j]>source[k])
				{
					j=k;
				}
			}
			temp_=source[i];
			source[i]=source[j];
			source[j]=temp_;
		}
		for(int val:source)
			System.out.println(val);
	}
	
	//@Test
	private  void bubbleSort(int [] source)
	{
		for(int i=0;i<source.length;i++)
		{
			for(int k=i+1;k<source.length;k++)
			{
				int temp_;
				if(source[i]<source[k])
				{
					temp_=source[i];
					source[i]=source[k];
					source[k]=temp_;
				}
			}
		}
		for(int val:source)
			System.out.println(val);
	}
	
	//@Test
	public void insertSort()
	{
		int [] source={1,7,6,8,0,4};
		
		for(int i=1;i<source.length;i++)
		{
			int temp_=source[i];
			int k=0;
			for(  k=i;k>0;k--)
			{
				if(source[k-1]>temp_)
					source[k]=source[k-1];
				else break;
			}
			source[k]=temp_;//最小的数一直前置
		}
		for(int val:source)
			System.out.println(val);
	}
	
	//@Test
	public void reverseArray()//翻转数组
	{
		int [] source={1,7,6,8,0,4,5};
		for(int val:source)
			System.out.print(" "+val);
		
		for(int i=0;i<(source.length/2);i++)
		{
			int temp_=source[i];
			source[i]=source[source.length-i-1];
			source[source.length-i-1]=temp_;
		}
		System.out.println();
		for(int val:source)
			System.out.print(" "+val);
	}
	
	
	private void swap(int a[],int before,int after)//交换
	{
		a[after]=a[after]^a[before];
		a[before]=a[after]^a[before];
		a[after]=a[after]^a[before];
		
	}
	
	private void sortMiddleValue(int a[],int first,int middle,int last)//三数排序
	{
		if(a[first]>a[middle])
			this.swap(a, first, middle);
		if(a[first]>a[last])
			this.swap(a, first, last);
		if(a[middle]>a[last])
			this.swap(a, middle, last);
	}
	
	//@Test
	public void swapTest()
	{
		int [] source={12,11};
		for(int val:source)
			System.out.print(" "+val);
		this.swap(source, 0,0);
		
		System.out.println("/////////////////////////////////////");
		for(int val:source)
			System.out.print(" "+val);
	}
	
	
	private int getPivot(int begin,int end)
	{
		return (begin+end)>>1;
	}
	
	private int pivotArray(int a[],int begin,int end)
	{
		int pivot=this.getPivot(begin, end);
		int temp_=a[pivot];
		a[pivot]=a[end];
		while(begin!=end)
		{
			while(a[begin]<temp_ && begin<end)
				begin++;
			if(begin<end)
			{
				a[end]=a[begin];
				end--;
			}
			
			while(a[end]>=temp_ && end>begin)
				end--;
			if(end>begin)
			{
				a[begin]=a[end];
				begin++;
			}
		}
		a[begin]=temp_;
		return begin;
	}
	
	private void quickSort(int[] array, int begin, int end)
	{
		if (end - begin < 1)
			return;
		int pivot = this.pivotArray(array, begin, end);
		quickSort(array,begin,pivot);
		quickSort(array,pivot+1,end);
	}
	
	@Test
	public void quickSortTest()
	{
		//int [] array={7,1,7,6,5,8,8,0,4,9};
		int [] array={25,11,5,9,7,72,8,1,13,6};
		this.quickSort(array, 0, array.length-1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
