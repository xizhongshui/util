package com.ssj.test.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.ssj.bean.Book;
import com.ssj.bean.User;

public class FastJsonTest {
		Logger logger=Logger.getLogger(FastJsonTest.class);
		@Test
		public void bean2string1(){
				User user1=new User("name1",1);
				String str=JSON.toJSONString(user1, false);
				logger.info("formateNo========"+str);
		}
		
		@Test
		public void bean2string2(){
				ArrayList< User> list=new ArrayList<User>();
				User user1=new User("name1",1);
				User user2=new User("name2",2);
				list.add(user1);
				list.add(user2);
				String str=JSON.toJSONString(list, false);
				logger.info("formateNo========"+str);
		}
		
		@Test
		public void bean2string3(){
				Map<String,User> map=new HashMap<String,User>();
				User user1=new User("name1",1);
				User user2=new User("name2",2);
				map.put("user1",user1 );
				map.put("user2", user2);
				String str=JSON.toJSONString(map, false);
				logger.info("formateNo========"+str);
		}
		
		@Test
		public void bean2string4(){
			   SerializeConfig config = new SerializeConfig(); 
				config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss")); 

				ArrayList< Book> list=new ArrayList<Book>();
				Book book1=new Book("book1",new Date());
				Book book2=new Book("book2",new Date());
				list.add(book1);
				list.add(book2);
				User user1=new User("name1",1);
				user1.setBooks(list);
				String str=JSON.toJSONString(user1,config);
				logger.info("formateNo========"+str);
		}
		
		@Test
		public void string2bean(){
			String data="data";
			String sign="sign";
				String str="{\"data\":{\"resp_code\":\"0000\",\"channel_id\":\"chuang6\",\"order_no\":\"CB-7222c8105179\",\"list1\":[{\"b_name\":\"name1\"},{\"b_name\":\"name1\"}]},\"sign\":\"sign\"}";
				JSONObject jobj=JSON.parseObject(str);
			
//				logger.info(""+jobj.getJSONObject("data").getString("channel_id"));
		}
		
}
