package com.ssj.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

/** ******************  类说明  *********************
 * @ClassName  : 
 * @Description: 简单类型转换
 * ************************************************/
public class TypeConvert {
	public static void map2Obj(Map<String,String[]>map,Class clazz,Object obj){
		if(map==null || map.size()<=0){return ;}
		Field []fields=clazz.getDeclaredFields();  //访问私有字段
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName=field.getName();
			if(map.containsKey(fieldName)){
				String []values=map.get(fieldName);
				String value=values!=null?values[0]:"";
				try {
					field.set(obj, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void mapSimple2Obj(Map<String,String>map,Class clazz,Object obj){
		if(map==null || map.size()<=0){return ;}
		Field []fields=clazz.getDeclaredFields();  //访问私有字段
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName=field.getName();
			if(map.containsKey(fieldName)){
				String value=map.get(fieldName);
					         value=value!=null?value:"";
				try {
					field.set(obj, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void str2int(){
		String province_code="1";
		Integer province_code_=Integer.parseInt(province_code);
	}
	
	@Test
	public void dotsTest(){
		float res=0.0f;
		res=6.5f/100;
		System.out.println(res);
	}
	
		private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";  
        private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";  
        private static final double MAX_VALUE = 9999999999999.99D;  
        public static String change(double v) {  
         if (v < 0 || v > MAX_VALUE){  
             return "参数非法!";  
         }  
         long l = Math.round(v * 100);  
         if (l == 0){  
             return "零元整";  
         }  
         String strValue = l + "";  
         // i用来控制数  
         int i = 0;  
         // j用来控制单位  
         int j = UNIT.length() - strValue.length();  
         String rs = "";  
         boolean isZero = false;  
         for (; i < strValue.length(); i++, j++) {  
          char ch = strValue.charAt(i);  
          if (ch == '0') {  
           isZero = true;  
           if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {  
            rs = rs + UNIT.charAt(j);  
            isZero = false;  
           }  
          } else {  
           if (isZero) {  
            rs = rs + "零";  
            isZero = false;  
           }  
           rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);  
          }  
         }  
         if (!rs.endsWith("分")) {  
          rs = rs + "整";  
         }  
         rs = rs.replaceAll("亿万", "亿");  
         return rs;  
        } 
        
        @Test
        public void small2BigMoney(){
        	System.out.println(change(100200.23f));
        	
        }
        
        @Test
        public void urlEncode(){
        	String url="http://caibao.tunnel.mobi/caibao/ht/2015/6/8/a34c7d07282d424798ff54b4c402c59b-me.pdf";
        	try {
				String result=URLEncoder.encode(url, "UTF-8");
				System.out.println(result);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        @Test	
        public void abs(){
     	   float a=0.01f;
     	   float b=0.01f;
     	   System.out.println(Math.abs(a-b));
        }
}
