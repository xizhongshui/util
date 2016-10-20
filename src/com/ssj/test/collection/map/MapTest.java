package com.ssj.test.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class MapTest {
	
	//HashMap---EntrySet  
	@Test
	public void hashMapTest1()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("A", "1");map.put("B", "2");map.put("C", "3");map.put("D", "4");
		
		/*//值替换
		 * if(map.containsKey("A"))
		    map.put("A", "5");*/
		
		for(Entry<String,String> entry:map.entrySet())
		{
			/*//值替换
			 * if(entry.getKey().equals("A"))
				entry.setValue("b");*/
			System.out.println("key="+entry.getKey()+"   value="+entry.getValue());
		}
		
	}

}
