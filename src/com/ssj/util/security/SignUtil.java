package com.ssj.util.security;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class SignUtil {

	 public static String genSignData(Map<String, String> map)
	    {
		 StrComparable comparator=new StrComparable();
	        StringBuffer content = new StringBuffer();

	        // 按照key做首字母升序排列
	        List<String> keys = new ArrayList<String>(map.keySet());
	        System.out.println("size===="+keys.size());
	        Collections.sort(keys, comparator);
	        for (int i = 0; i < keys.size(); i++)
	        {
	            String key = (String) keys.get(i);
	            String value = map.get(key);
	            // 空串不参与签名
	            // 空串不参与签名
	            if (StringUtils.isEmpty(value))
	            {
	                continue;
	            }
	            content.append((i == 0 ? "9fbank-account2_|"+value+"|":value+"|"));
	        }
	        content.append("_wapPay");
	        return content.toString();
	    }
	 
	 @Test
	 public void testSigin(){
		 HashMap<String,String> map=new HashMap<String, String>();
		
//		 map.put("cca","cca");
//		 map.put("ab","ab");
//		 map.put("ccb","ccb");
		 map.put("bc","ba");
		 map.put("ba","bc");
//		 map.put("ac","ac");
		 String src=genSignData(map);
		 System.out.println(src);
	 }
	 
}

class StrComparable implements Comparator<String>{
	 @Override  
	    public int compare(String o1, String o2) {  
	        return Collator.getInstance(Locale.ENGLISH).compare(o1, o2);
	    }
}
