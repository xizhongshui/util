package com.ssj.test.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.ssj.bean.User;
import com.ssj.test.json.bean.YieldVo;



public class JsonLibTest {
	/* 
     *  普通类型、List、Collection等都是用JSONArray解析
     *  
     *  Map、自定义类型是用JSONObject解析
     *  可以将Map理解成一个对象，里面的key/value对可以理解成对象的属性/属性值
     *  即{key1:value1,key2,value2......}
     *  
     *字符串json转换成json， 根据情况是用JSONArray或JSONObject
     * 1.JSONObject是一个name:values集合，通过它的get(key)方法取得的是key后对应的value部分(字符串)
     *         通过它的getJSONObject(key)可以取到一个JSONObject，--> 转换成map,
     *         通过它的getJSONArray(key) 可以取到一个JSONArray ，
     */
	
	/*普通类型*/
	@Test
	public void obj2Json1(){
		int[] arry={1,2,3,5};
		JSONArray json=JSONArray.fromObject(arry);
		System.out.println(json);
	}
	
	/*List 类型*/
	@Test
	public void obj2Json2(){
		List<User> ss=new ArrayList<User>();
		User user1=new User();
		user1.setName("aaa");
		
		User user2=new User();
		user2.setName("bbb");
		
		ss.add(user1);
		ss.add(user2);
		JSONArray json=JSONArray.fromObject(ss);
		System.out.println(json);
	}
	
	/*自定义数据类型*/
	@Test
	public void obj2Json3(){
		User user2=new User();
		user2.setName("bbb");
		JSONObject json=JSONObject.fromObject(user2);
		System.out.println(json);		
	}
	
	/*Map类型	
    "statusCode":"200", 
		"message":"操作成功", 
		"navTabId":"", 
		"rel":"",
		"callbackType":"closeCurrent",
		"forwardUrl":""*/
	@Test
	public void obj2Json4(){
		/*json配置文件过滤属性
		 * JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		 * jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		 * jsonConfig.setExcludes(new String[]{"roles","parent","children"});  //此处是亮点，只要将所需忽略字段加到数组中即可，在上述案例中，所要忽略的是“libs”，那么将其添到数组中即可，在实际测试中，我发现在所返回数组中，存在大量无用属性，如“multipartRequestHandler”，“servletWrapper”，那么也可以将这两个加到忽略数组中.
		*JSONArray json=JSONArray.fromObject(functions,jsonConfig);//加载配置文件*/		

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("statusCode", 200);
		map.put("navTabId", "page41");
		map.put("message", "操作成功");
		map.put("callbackType", "closeCurrent");
		map.put("rel", "");
		map.put("forwardUrl", "");
		JSONObject json=JSONObject.fromObject(map);
		System.out.println(json);
	}
	
	@Test
	public void string2Json4(){
		String jsonStr="[{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,6.5,\"4339\",\"\",169,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},3,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},0],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":6.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":169},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":3},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":0}]},{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,7.5,\"4339\",\"\",170,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},4,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},2],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":7.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":170},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":4},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":2}]},{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,8.5,\"4339\",\"\",171,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},6,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},4],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":8.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":171},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":6},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":4}]},{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,9.5,\"4339\",\"\",172,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},8,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},6],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":9.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":172},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":8},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":6}]},{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,10.5,\"4339\",\"\",173,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},10,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},8],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":10.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":173},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":10},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":8}]},{\"attrNames\":[\"price_type\",\"max_investamt\",\"min_investamt\",\"expect_profit\",\"product_id\",\"remark\",\"price_id\",\"free_month\",\"create_time\",\"max_term\",\"modify_time\",\"min_term\"],\"attrValues\":[\"1\",0,0,11.5,\"4339\",\"\",174,0,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},12,{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115},10],\"attrsEntrySet\":[{\"key\":\"price_type\",\"value\":\"1\"},{\"key\":\"max_investamt\",\"value\":0},{\"key\":\"min_investamt\",\"value\":0},{\"key\":\"expect_profit\",\"value\":11.5},{\"key\":\"product_id\",\"value\":\"4339\"},{\"key\":\"remark\",\"value\":\"\"},{\"key\":\"price_id\",\"value\":174},{\"key\":\"free_month\",\"value\":0},{\"key\":\"create_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"max_term\",\"value\":12},{\"key\":\"modify_time\",\"value\":{\"date\":24,\"day\":4,\"hours\":14,\"minutes\":9,\"month\":8,\"nanos\":0,\"seconds\":6,\"time\":1443074946000,\"timezoneOffset\":-480,\"year\":115}},{\"key\":\"min_term\",\"value\":10}]}]";
		JSONArray arrayJson=JSONArray.fromObject(jsonStr);
		JSONObject itemJson=arrayJson.getJSONObject(0);
		JSONArray attrNamesArray= itemJson.getJSONArray("attrNames");
		System.out.println((String)attrNamesArray.get(0));
		List<YieldVo> list=null;
		for(YieldVo item:list){
			System.out.println(JSONObject.fromObject(item).toString());
		}
	}
}
