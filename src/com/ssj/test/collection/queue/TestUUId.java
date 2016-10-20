
/**
 * @Project		util
 * @File		TestUUId.java
 * @Package		com.ssj.test.collection.queue
 * @Version		V1.0
 * @Date		2016年2月21日 下午6:14:36
 * @Author		shenshuangjie
 */
	
package com.ssj.test.collection.queue;

import java.util.Map;

import org.junit.Test;


/**
 * @Description	TODO
 * @ClassName	TestUUId
 * @Date		2016年2月21日 下午6:14:36
 * @Author		shenshuangjie
 */

public class TestUUId {
	
	@Test
	public void  test(){
		UUIdStrutColle uuidStrut=new UUIdStrutColle(5, 2);
		uuidStrut.put("1", 1l);
		uuidStrut.put("2", 2l);
		uuidStrut.put("3", 3l);
		uuidStrut.put("4", 4l);
		uuidStrut.put("5", 5l);
		uuidStrut.print();
		System.out.println("================================");
	/*	uuidStrut.put("6", 6l);
		uuidStrut.put("7", 7l);
		uuidStrut.put("8", 8l);
		uuidStrut.put("1", 1l);
		uuidStrut.print();
		System.out.println("================================");
		String key="6";
		Long val=uuidStrut.get(key);
		System.out.println("key:"+key+",val:"+val);*/
		
		Map<String,Long> map=uuidStrut.getKeyOfMap("5");
		map.put("5", 6l);
		uuidStrut.print();
	}

}
