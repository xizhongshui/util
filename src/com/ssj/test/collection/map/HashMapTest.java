package com.ssj.test.collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.ssj.test.collection.map.struts.MyPoint;
import com.ssj.test.collection.map.struts.User;


public class HashMapTest {

	public static void main(String[] args) {

	}
	
	@Test//contains Method
	public void containsTest(){
		MyPoint point=new MyPoint(1, 1);   User user=new User(1,"a");
		MyPoint point2=new MyPoint(2, 2);  User user2=new User(2,"b");
		MyPoint point3=new MyPoint(3, 3);  User user3=new User(3,"c");
		
		HashMap map=new HashMap<MyPoint,User>();
		map.put(point, user);
		map.put(point2, user2); 
		//map.put(point3, user3);
		
		System.out.println(map.size());
		//System.out.println(map.containsValue(user3));
		//System.out.println(map.containsKey(point3));
		System.out.println(map.remove(point));
		
		Set<Entry<MyPoint,User>> entrySet=map.entrySet();
		for(Entry<MyPoint,User> entry:entrySet){
			System.out.println(entry.getKey().getX()+"  "+entry.getValue().getName());
		}
		
		
		
	}
////////////////////////////////模拟hashMap////////////////////////////////	
class MyEntry<K,V>{
	K key;
	V value;
	int hash;
	MyEntry<K,V> next;
	
}	
	class MyHashMap<K,V>{
		MyEntry<K,V> []table=null;
		
//模拟删除		
		private V remove(Object key){
			int keyHash=0;
			int index=0;
			MyEntry<K, V> prev=table[index];
			MyEntry<K, V> e=prev;
			
			while(e!=null){
				MyEntry<K, V> next=e.next;
				if(e.hash==keyHash && ( e.key==key || key.equals(e.key)) ){
					if(e==prev){
						table[index]=next;
						e.next=null;
					}
					else{
						prev=next;
						e.next=null;
					}
					  return e.value;
						
				}
				prev=e;
				e=next;
			}
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
