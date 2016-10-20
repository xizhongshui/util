package com.ssj.test.collection.map.struts;

import java.util.HashMap;

/**
 * 重写equals()和hashCode()方法
*/public class MyPoint {
	private int x;
	private int y;
	
	//public MyPoint(){}
	public MyPoint(int x,int y){
		this.x=x;
		this.y=y;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}*/

/*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPoint other = (MyPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}*/
	
	
	public static void main(String[] args) {
		MyPoint point1=new MyPoint(1, 1);
		MyPoint point2=new MyPoint(1, 1);
		
		System.out.println(point1.equals(point2));
		System.out.println(point1.hashCode());
		System.out.println(point2.hashCode());
		
		HashMap map=null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
