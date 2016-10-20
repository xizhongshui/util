package com.ssj.test.collection.list.my;

import java.util.Arrays;

import com.ssj.test.collection.iterator.my.MyIterable;
import com.ssj.test.collection.iterator.my.MyIterator;
import com.ssj.test.collection.iterator.my.MyListIterator;

public class MyArrayList<E> implements MyList<E>,MyIterable<E>{
	private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
	private int size=0;
	private Object[] elementData=null;
	
	public MyArrayList(int initCapacity){
		elementData=new Object[initCapacity];
	}
	
	public MyArrayList(){
		this(2);
	}
	
	private class Itr implements MyIterator<E>{
		 int cursor=0;
		 int lastRet=-1;
		
		/**判断当前索引是否越最大界*/
		@Override
		public boolean hasNext() {
			return cursor!=size;
		}
		
		/**返回当前值，游标下移*/
		@Override
		public E next() {
			E currValue=(E)elementData[cursor++];
			cursor=cursor>size? size:cursor;
			lastRet=cursor-1;
			return currValue;
		}
		
		/**删除游标左侧值,只能操作一次*/
		@Override
		public E remove() {
			if(lastRet<0)
				throw new IllegalStateException();
			E remveValue=(E)MyArrayList.this.remove(lastRet);
			lastRet=-1;
			return remveValue;
		}
		
	}
	
	private class ListItr extends Itr implements MyListIterator<E>{
		
		/**当前游标是否越最小边界*/
		@Override
		public boolean hasPrevious() {
			return cursor!=0;
		}

		/**返回当前游标左侧值*/
		@Override
		public E previous() {
			E value=(E)elementData[--cursor];
			lastRet=cursor;//确保删除时是当前值
			return value;
		}
		
	}
	
	
	@Override
	public MyIterator<E> iterator() {
		return new Itr();
	}
	
	public MyListIterator<E> listIterator() {
		return new ListItr();
	}

	@Override
	public boolean add(E ele) {
		ensureCapacity(size+1);
		elementData[size++]=ele;
		return false;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return (E)elementData[index];
	}
	
	private void checkIndex(int index){
		if(index>=size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	private String outOfBoundsMsg(int index){
		return "Index:"+index+", size:"+size;
	}
	
	private void ensureCapacity(int requiredCapacity){
		if(requiredCapacity>elementData.length)
			grow(requiredCapacity);
	}
	
	private void grow(int requiredCapacity){
		int oldCapacity=elementData.length;
		int newCapacity=oldCapacity+(oldCapacity>>1);
		if(newCapacity>MAX_ARRAY_SIZE)
			throw new OutOfMemoryError();
		elementData=Arrays.copyOf(elementData, newCapacity);
		
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E oldValue=(E)elementData[index];
		int movedNum= size-index-1;
		if(movedNum>0)
			System.arraycopy(elementData, index+1, elementData, index,movedNum);
		elementData[--size]=null;
		return oldValue;
	}

	@Override
	public int size() {
		return size;
	}

}
