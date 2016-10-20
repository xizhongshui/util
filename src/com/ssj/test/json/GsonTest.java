package com.ssj.test.json;

import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.ssj.bean.User;

public class GsonTest {
	
		@Test
		public void test(){
			Gson gson=new Gson();
			User user1=new User("name1",1);
//			String data=gson.toJson(user1);
			String data="data";
			String sign="sign";
			 String strJon="{\"data\":"+data+",\"sign\":\""+sign+"\"}";
			 System.out.println(strJon);
		}
		
		@Test
		public void testNull(){
			Map<String, String> saveOrderMap=null;
			 System.out.println(new Gson().toJson(saveOrderMap));
		}
}
