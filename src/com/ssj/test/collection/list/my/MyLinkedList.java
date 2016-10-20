package com.ssj.test.collection.list.my;

import com.ssj.test.collection.iterator.my.MyIterable;
import com.ssj.test.collection.iterator.my.MyIterator;

public class MyLinkedList<E> implements MyList<E>, MyIterable<E> {

	private int size=0;
	private Node<E> first=null;
	private Node<E> last=null;
	
	public E getFirst(){
		return first==null?null:first.item;
	}
	
	public E getLast(){
		return last==null?null:last.item;
	}
	
	private void linkLast(E ele){
		final Node<E> l=last;
		Node<E> newNode=new Node<E>(ele, l, null);
		last=newNode;
		if(l==null){
			first=newNode;
		}
		else{
			l.next=newNode;
		}
		size++;
	}
	
	private void linkFirst(E ele){
		final Node<E> f=first;
		Node<E> newNode=new Node<E>(ele, null, f);
		first=newNode;
		if(f==null){
			last=newNode;
		}
		else{
			f.pre=newNode;
		}
		size++;
	}
	
	private void checkIndex(int index){
		if(index<0 || index>size-1)
			throw new IndexOutOfBoundsException();
	}
	
	private Node<E> getNode(int index){
		checkIndex(index);
		if(index<(size>>1)){
			Node<E> rem=first;
			for(int i=0;i<index;i++){
				rem=rem.next;
			}
			return rem;
		}
		else{
			Node<E> rem=last;
			for(int i=size-1;i>index;i--){
				rem=rem.pre;
			}
			return rem;
		}
	}
	
	private E unLink(Node<E> rem){
		final Node<E> pre=rem.pre;
		final Node<E> next=rem.next;
		if(pre!=null){
			pre.next=next;
		}
		else{
			next.pre=null;
			first=next;
		}
		
		if(next!=null){
			next.pre=pre;
		}
		else{
			pre.next=null;
			last=pre;
		}
		
		size--;
		return rem.item;
		
	}
	
	
	@Override
	public MyIterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E ele) {
		linkLast(ele);
		return true;
	}
	
	public boolean addFirst(E ele){
		linkFirst(ele);
		return true;
	}

	@Override
	public E get(int index) {
		return getNode(index).item;	}

	@Override
	public E remove(int index) {
		return unLink(getNode(index));
	}
	
	public boolean remove(Object obj){
		if(obj==null){
			for(Node<E> x=first;x!=null;x=x.next){
				if(x.item==null){
					unLink(x);
					return true;
				}
			}
		}
		else{
			for(Node<E> x=first;x!=null;x=x.next){
				if(obj.equals(x.item)){
					unLink(x);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}
	
	private class Node<E>{
		E item;
		Node<E> pre;
		Node<E> next;
		public Node(E item, Node<E> pre, Node<E> next) {
			super();
			this.item = item;
			this.pre = pre;
			this.next = next;
		}
		
	}

}
