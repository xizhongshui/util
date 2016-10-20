package com.ssj.bean;

import java.util.List;

public class TreeNode 
{
	private String id;
	private String pId;
	private String name;
	private boolean checked=false;
	private List<TreeNode> children;
	
	public TreeNode()
	{}
	
	public TreeNode(String id,String pId,String name,List<TreeNode> children,boolean checked)
	{
		this.id=id;
		this.name=name;
		this.pId=pId;
		this.children=children;
		this.checked= checked;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String id) {
		pId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}
