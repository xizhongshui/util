package com.ssj.test.regx;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Regx {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*Boolean r=Pattern.matches("001[\\d]*", "001001003");//*{0,}、+{1,}、?{0,1}
		System.out.print("r:"+r);//true*/
		
		/*Boolean r=Pattern.matches("image/bmp|image/png|image/gif|image/jpeg", "image/jpe");
		System.out.print("r:"+r);//false*/
		
		/*Boolean r=Pattern.matches("^gly[\\w]*","gly");
		System.out.println("r:"+r);*/
		
		//Email  zhangshna@163.com  zhangshna.Mr@163.com  zhangshna@163.com.cn
//		Boolean r=Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$","zhangshna@163.com.cn");
	/*	String target="table";
		Boolean r=Pattern.matches(".*\\b"+target+"\\b.*",target+" wang");// sql 注入
		System.out.println("r:"+r);*/
		
		 String source = "<input type='hidden' name='token' value='1181181291548098067' \\/> <input type='hidden' name='token' value='1181181291548098067' \\/>";  
	        List<String> list = match(source, "input", "value");  
	        System.out.println(list);  
	}
	
	@Test
	public void patternMatch()
	{
		/*String source="a.java  b.java  d.java";
		Pattern pattern=Pattern.compile("\\b\\w+\\.java\\b");//创建模式匹配器
		Matcher matcher=pattern.matcher(source);
		System.out.println(matcher.groupCount());
		while(matcher.find())//迭代
		{
			System.out.println(matcher.group());
		}*/
		
		
		String source="add - 副本 (3).java";
		//sPattern pattern=Pattern.compile("^\\.+\\.java$");//创建模式匹配器
		//Matcher matcher=pattern.matcher(source);
		Boolean result=Pattern.matches("a.*", source);
		System.out.println(result);
		
		
		/*Boolean result=Pattern.matches("\\d+(\\.java)$","123.java");//创建模式匹配器
		System.out.println(result);*/
	}
	
	@Test
	public void test2(){
		Boolean result=Pattern.matches("^\\d{11}$","123.java");//创建模式匹配器
		System.out.println(result);
	}
	@Test //数字和字母组合
	public void test3(){
		Boolean result=Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$","lllllllllllllll1");//创建模式匹配器
		System.out.println(result);
	}
	
	public static List<String> match(String source, String element, String attr) {  
        List<String> result = new ArrayList<String>();  
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";  
        Matcher m = Pattern.compile(reg).matcher(source);  
        while (m.find()) {  
            String r = m.group(1);  
            result.add(r);  
        }  
        return result;  
    }  
     
	 @Test
	 public void replaceStr(){
		 String [] values=new String[]{"申双杰","V45天","10.20%"};
		 String src="尊敬的VIP财宝用户xxx您好,恭喜您成功买入VIP财宝\"xxx\",享受xxx预期收益~xxx";
		 String placeHolder="xxx";
		System.out.println("源字符串-->"+src);
		 
		 Pattern p = Pattern.compile(placeHolder);
		 Matcher matcher = p.matcher(src);
		 StringBuffer sb = new StringBuffer();
		 for(int i=0;i<values.length;i++){
			 matcher.find();
			matcher.appendReplacement(sb, values[i]);
		 }
		 matcher.find();
		matcher.appendReplacement(sb, "");
			System.out.println("替换字符串-->"+sb.toString());
	 }
	

}
