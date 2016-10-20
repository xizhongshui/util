package com.ssj.test.json;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.ssj.bean.OrderReqVo;
import com.ssj.util.TypeConvert;

public class TypeConvertTest {
	
	@Test
	public void map2Obj(){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("orderId",null);
		map.put("createTime","20140513");
		map.put("amount","50");
		OrderReqVo obj=new OrderReqVo();
		TypeConvert.mapSimple2Obj(map, OrderReqVo.class, obj);
		String str=JSONObject.fromObject(obj).toString();
	}

}
