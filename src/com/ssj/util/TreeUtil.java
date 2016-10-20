package com.ssj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssj.bean.TreeNode;

public class TreeUtil 
{
	//List分组
	public  Map groupByList(List<TreeNode> nodeList)
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
	
	//递归	
	private  List<TreeNode>  recursionMap(TreeNode ndoe,Map<String,List<TreeNode>> treeGroupMap)
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
	
	public  TreeNode getTree(TreeNode startNode,List<TreeNode> nodeList)
	{
		Map<String,List<TreeNode>> treeGroupMap=this.groupByList(nodeList);
		List<TreeNode> reNodes=this.recursionMap(startNode,treeGroupMap);
		startNode.setChildren(reNodes);
		return startNode;
		
	}

}
