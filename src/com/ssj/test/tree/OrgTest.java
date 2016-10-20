package com.ssj.test.tree;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class OrgTest {

		@Test
		public void createOrgCode(){
			OrgVo vo=new OrgVo();
			vo.setOrg_code("010045");
			vo.setParent_code("-1");
			vo.setLevel(2);
			vo.setCreateIndex(1);
			vo.setName("财宝科技");
			//==========新曾节点属性============
			 int newlevel=vo.getLevel()+1;
			 String parentCode=vo.getOrg_code();
			 String maxCode="";//查询当前节点下最大节点
			 if(StringUtils.isEmpty(maxCode)){
				 maxCode=parentCode;
			 }
			 String code2BitStr=maxCode.substring(newlevel, newlevel*2);
			 int code2BitInt=Integer.parseInt(code2BitStr);
			 System.out.println("code2BitInt="+code2BitInt);
			 int currCode2BitInt=code2BitInt+1;
			 String currCode2BitStr="";
			 if(currCode2BitInt<10){
				 currCode2BitStr="0"+currCode2BitInt;
			 }else{
				 currCode2BitStr=currCode2BitInt+"";
			 }
			 System.out.println("currCode2BitStr="+currCode2BitStr);
			 String currCode="";
			 if(newlevel==2){
				 currCode=maxCode.substring(0, 2)+currCode2BitStr+maxCode.substring(4, 6);
			 }else if(newlevel==3){
				 currCode=maxCode.substring(0, 4)+currCode2BitStr;
			 }
			 System.out.println("currCode="+currCode);
		}
		
		@Test
		public void loop(){
			int i=1;
			int curr=-1;
			boolean flag=true;
			int [] src={};
			for(;i<=2&& flag;i++){
				int k=0;
				for(;k<src.length;k++){
						curr=src[k];
						if(i==curr){
							break;
						}
				}
				if(k==src.length){
					flag=false;
				}
				System.out.println("k==="+k);
				System.out.println("i:"+i+"====curr"+curr);
			}
			if(i-1==curr){
				System.out.println("全部占用,不可用");
			}
		}
}
