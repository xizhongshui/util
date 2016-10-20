
/**
 * @Project		util
 * @File		UUIdStrutColle.java
 * @Package		com.ssj.test.collection.queue
 * @Version		V1.0
 * @Date		2016年2月21日 下午5:09:28
 * @Author		shenshuangjie
 */
	
package com.ssj.test.collection.queue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class UUIdStrutColle {
	private int capacity=0;//所有Map集合的总容量
	private int partCapacity=0;//每个Map集合的容量
	private int currSize=0;//当前所有Map集合元素的总个数
	private LinkedList<Map<String,Long>> linkedList=new LinkedList<Map<String,Long>>();
	
	public UUIdStrutColle(int capacity, int partCapacity) {
		if(partCapacity>capacity){
			partCapacity=capacity;
		}
		this.capacity = capacity;
		this.partCapacity = partCapacity;
	}
	
	public UUIdStrutColle() {
	}

	//添加元素
	public void put(String key,Long value){
		Map<String,Long> targetMap=getKeyOfMap(key);
		if(targetMap!=null){
			targetMap.put(key, value);
		}else{
			if(currSize==capacity){
				 remove();
			}
			 targetMap=searchNotFullMap();
			 if(targetMap!=null){
				 targetMap.put(key, value);
			 }else{
				 targetMap=new HashMap<String,Long>();
				 linkedList.offer(targetMap);
				 targetMap.put(key, value);
			 }
			 currSize++;
		}
	}
	
	public void print(){//  capacity  partCapacity  currSize
		System.out.println("capacity--->"+capacity);
		System.out.println("partCapacity--->"+partCapacity);
		System.out.println("mapSize--->"+linkedList.size());
//		System.out.println("--->"+);
		System.out.println("currSize--->"+currSize);
		Map<String,Long> map=null;
		Iterator<Map<String,Long>> itr=linkedList.iterator();
		while(itr.hasNext()){
			map=itr.next();
			printMap(map);
		}
	}
	
	private void printMap(Map<String,Long> map){
		Set<Entry<String,Long>> set=map.entrySet();
		Iterator<Entry<String,Long>> iter=set.iterator();
		while(iter.hasNext()){
			Entry<String,Long> entry=iter.next();
			System.out.println("key:"+entry.getKey()+",val:"+entry.getValue());
		}
	}
	
	public Map<String,Long> getKeyOfMap(String key){
		Iterator<Map<String,Long>> itr=linkedList.iterator();
		while(itr.hasNext()){
			Map<String,Long> map=itr.next();
			if(map.containsKey(key)){
				return map;
			}
		}
		return null;
	}
	
	public Long get(String key){
		Map<String,Long> map= getKeyOfMap(key);
		if(map!=null){
			return map.get(key);
		}
		return null;
	}
	
	public Map<String,Long> remove(){
		Map<String,Long> firstEle=linkedList.poll();
		if(firstEle!=null){
			currSize=currSize-firstEle.size();
		}
		return firstEle;
	}
	
	public Map<String,Long> searchNotFullMap(){
		Iterator<Map<String,Long>> itr=linkedList.iterator();
		while(itr.hasNext()){
			Map<String,Long> map=itr.next();
			if(map.size()<partCapacity){
				return map;
			}
		}
		return null;
	}
	
}
