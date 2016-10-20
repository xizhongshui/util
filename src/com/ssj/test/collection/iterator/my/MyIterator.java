package com.ssj.test.collection.iterator.my;

public interface MyIterator<E> {
	boolean hasNext();
	E next();
	E remove();

}
