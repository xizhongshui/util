package com.ssj.test.collection.iterator.my;

public interface MyListIterator<E> extends MyIterator<E> {
	boolean hasPrevious();
	E previous();
}
