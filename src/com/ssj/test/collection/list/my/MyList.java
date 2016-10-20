package com.ssj.test.collection.list.my;

public interface MyList<E> {
	
	boolean add(E ele);
	E get(int index);
	E remove(int index);
	int size();
	
}
