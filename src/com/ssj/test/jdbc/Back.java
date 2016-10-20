package com.ssj.test.jdbc;

import java.io.IOException;

import org.junit.Test;

public class Back {

	@Test
	public  void backup() {   
		   String user = "root"; // 数据库帐号   
		   String password = "root"; // 登陆密码   
		   String database = "ecshop"; // 需要备份的数据库名   
		   String filepath = "d:\\ecshop.sql"; // 备份的路径地址   
		  
		   String stmt1 = "mysqldump " + database +" -h 127.0.0.1 "+ " -u " + user + " -p" +   
		       password + " --default-character-set=utf8 --result-file=" + filepath;   
		    
		   try {   
		    Runtime.getRuntime().exec(stmt1);   
		    System.out.println("数据已导出到文件" + filepath + "中");   
		   } catch (IOException e) {   
		    e.printStackTrace();   
		   }   
		  
		}  
}
