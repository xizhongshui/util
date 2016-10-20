package com.ssj.test.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import com.ssj.bean.TreeNode;
public class ListTest 
{
	private static List<TreeNode> nodeList;
	
	 static 
	{
		nodeList=new ArrayList<TreeNode>();
		nodeList.add(new TreeNode("A","0","a",null,false));
		/*nodeList.add(new TreeNode("A_1","A","a_1",null));
		nodeList.add(new TreeNode("A_2","A","a_2",null));
		nodeList.add(new TreeNode("A_1_1","A_1","a_1_1",null));
		nodeList.add(new TreeNode("A_1_2","A_1","a_1_2",null));*/
		nodeList.add(new TreeNode("B","0","b",null,false));
		/*nodeList.add(new TreeNode("B_1","B","b_1",null));
		nodeList.add(new TreeNode("C","0","c",null,false));
		nodeList.add(new TreeNode("D","0","d",null,false));*/
	}
	//List分组
	public Map groupByList()
	{
		Map<String,List<TreeNode>> treeGroupMap=new HashMap<String,List<TreeNode>>() ;
		
		for(TreeNode node:nodeList)
		{
			
			if(treeGroupMap.containsKey(node.getPId()))
			{
				List<TreeNode> nodesInMap=treeGroupMap.get(node.getPId());
				nodesInMap.add(node);
				treeGroupMap.put(node.getPId(), nodesInMap);
				
				
			}
			else
			{
				List<TreeNode> nodesInMap=new ArrayList<TreeNode>();
				nodesInMap.add(node);
				treeGroupMap.put(node.getPId(), nodesInMap);
			}
		}
		return treeGroupMap;
	}
	
	private List<TreeNode>  recursionMap(TreeNode ndoe,Map<String,List<TreeNode>> treeGroupMap)
	{
		String pid=ndoe.getId();
		List<TreeNode>  resultNodes=new ArrayList<TreeNode>();
		if(treeGroupMap.containsKey(pid))
		{
			List<TreeNode>  nodes=treeGroupMap.get(pid);
			if(nodes!=null)
			{
				for(TreeNode node_:nodes)
				{
					if(treeGroupMap.containsKey(node_.getId()))
					{
						List<TreeNode>  ns=recursionMap( node_,treeGroupMap);
						node_.setChildren(ns);
						
					}
					resultNodes.add(node_);
				}
			}
			
		}
		return resultNodes;
	}
	
	
	//@Test
	public void createJsonTree()
	{
		ListTest listTest=new ListTest();
		TreeNode node=new TreeNode("0","null","gen",null,false);
		Map<String,List<TreeNode>> treeGroupMap=listTest.groupByList();
		
		List<TreeNode> reNodes=listTest.recursionMap(node,treeGroupMap);
		//Collections.synchronizedList(reNodes);//同步转换
		
		/*Iterator  iter=reNodes.iterator();
		TreeNode node_=null;
		while(iter.hasNext())
		{
			node_=(TreeNode)iter.next();
			System.out.println(node_.getName());
		}*/
		node.setChildren(reNodes);
		JSONObject json=JSONObject.fromObject(node);
		//JSONArray json=JSONArray.fromObject(reNodes);
		System.out.println(json);
	}
	
	//@Test
	public  void reDuplicate()//list去重
	{
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);list.add(1);list.add(3);list.add(1);list.add(2);list.add(5);
		for(int val:list)
			System.out.print(val+",");
		if(list!=null)
		{
			for(int i=0;i<list.size();i++)
			{
				int k=0;
				for(k=i+1;k<list.size();k++)
				{
					if(list.get(i)==list.get(k))
					{ list.remove(k);}
				}
			}
		}
		System.out.println("*****************************************");
		for(int val:list)
			System.out.print(val+",");
		
	}
	
	//@Test
	public  void reDuplicate2()//list去重
	{
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);list.add(1);list.add(3);list.add(1);list.add(2);list.add(5);
		for(int val:list)
			System.out.print(val+",");
		Set<Integer> set=new HashSet<Integer>(list);
		System.out.println("*****************************************");
		for(int val:set)
			System.out.print(val+",");
		
	}
	
	@Test
	public  void reDuplicate3()//list去重
	{
		List<Integer> list=new ArrayList<Integer>();
		List<Integer> list_=new ArrayList<Integer>();
		list.add(1);list.add(1);list.add(3);list.add(1);list.add(2);list.add(5);
		for(int val:list)
			System.out.print(val+",");
		System.out.println();
		if(list!=null)
		{
			for(int i=0;i<list.size();i++)
			{
				int o=list.get(i);
				System.out.println(o+"//////"+list_.contains(o));
				if(!list_.contains(o))
					list_.add(o);
			}
			
		}
		
		System.out.println("*****************************************");
		for(int val:list_)
			System.out.print(val+",");
		list=null;list=null;
	}
	
	@Test
	public void list2array(){
			ArrayList<Object> list=new ArrayList<Object>();
			list.add("name");
			Object [] args=list.toArray();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
