package com.ssj.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class StringSplit 
{
	Logger logger=Logger.getLogger(StringSplit.class);
	 public static void main(String[] args)
	 {  
	        //依据正则式从父串中剔除  String replaceAll("regular","");
	          
	        String  sub ="";  
	        /* String  s="upload/1.txt;upload/2.txt;upload/3.txt;upload/4.txt";
	        System.out.println("s:"+s);  
	        sub = s.replaceAll( ";upload/1.txt|upload/1.txt;","");
	        System.out.println("sub:"+sub);  
	        
	        s="upload/1.txt;upload/2.txt;upload/3.txt;upload/4.txt";
	        sub = s.replaceAll( ";upload/3.txt|upload/3.txt;","");//replaceAll( ",122.jpg|122.jpg,","");  
	        System.out.println("sub:"+sub); 
	        */
	        /*
	         String  s="upload/2.txt?";
	        sub = s.replaceAll( "\\?upload/2.txt|upload/2.txt\\?","");
	        System.out.println("sub:"+sub); 
	        */
	        String  s="upload/2.txt?";
	        s.split("\\?");
	        sub = s.replaceAll( "\\?upload/2.txt|upload/2.txt\\?","");
	        System.out.println("sub:"+sub);  
	        
	        
	         
		 //检索是否存在
		 /*List<String> phones=new ArrayList<String>();
		 phones.add("13938135986");
		 phones.add("13598520599");
		 phones.add("13511111111");
		 phones.add("13513810086");
		 phones.add("13569190527");
		 phones.add("13513810068");
		 int i=phones.indexOf("13511111111");//
		 int k=phones.indexOf("00000000000");//
		 System.out.println("i："+i+"k:"+k);
		 */
		 
		 //是否以某字符串开始Boolean startWith("");Boolean startWith("",int index);
		 /*
		 String fileName="gglys001";
		 System.out.println(fileName.startsWith("gly",1));//true
		 */
		 
		 //是否以否字符串结束Boolean endsWith("");
		 /*
		 String fileName="temp.gif";
		 System.out.println(fileName.endsWith(".gif"));//true
		 */
		 
		 //顺序索引int indexOf("");int indexOf("",int index);
		 /*
		 String fileName="temp.gif";
		 int index=fileName.indexOf("gif");
		 System.out.println(index);//5
		 */
		 
		 //逆序索引int lastIndexOf("")
		 String fileName="head.temp.gif";
		 int dot=fileName.lastIndexOf(".");
		 System.out.println(dot);//9
		 //获取字串String subString(int index);
		 System.out.println(fileName.substring(9));//.gif
		 
		 //regex正则匹配Boolean matches("regex");
		 /*
		 String s="001003";
		 System.out.println(s.matches("001[\\d]*"));//true
		 */
		 
		 //是否包含
		 /*
		 String s="1.txt,2.txt,3.txt";
		 System.out.println(s.contains("2.txt"));//true
		 System.out.println(s.indexOf("2.txt"));//true
		 */
		 /*
		 //分离字符串
		 String s="1.txt,2.txt,3.txt";
		 String []strArr=s.split(",");
		 System.out.println(s);
		 for(String sub : strArr)
		 {
			 System.out.println(sub);
		 }
		 */
		 
 
	 }  
	 
	 @Test
	 public void encodedTest() throws UnsupportedEncodingException
	 {
		 String source="源字符串";
		//先将字符串以ISO-8859-1进行编码
		 String tempStr = new String(source.getBytes("UTF-16"), "ISO-8859-1");
		 //由于中文平台一个中文占两上字节，所以以ISO-8859-1进行编码时，输出为10个字符的乱码
		 System.out.println(tempStr);
		 //再获取该字符串的ISO-8859-1字节，以GBK解码，
		 tempStr=new String(tempStr.getBytes("ISO-8859-1"),"UTF-16");
		 //还原
		 System.out.println(tempStr); 
	 }	 
	 
	 @Test
	 public void testSlip(){
		 	String src="//www//webcode//pdf/2015/6/3/5aa0f1839231461aad0a0d80f45930af.pdf";
		 	int index=src.indexOf("pdf/");
		 	System.out.println("index:"+index);
		 	System.out.println(src.substring(index+4, src.length()));
	 }
	 
	 @Test
	 public void convert(){
	 }
	 @Test
	 public void createRandom(){
		 String base = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";    
		    Random random = new Random();     
		    StringBuffer sb = new StringBuffer();     
		    for (int i = 0; i <16; i++) {     
		        int number = random.nextInt(base.length());     
		        sb.append(base.charAt(number));     
		    }     
		    System.out.println(sb.toString());
	 }
	 
		/**格式化小数*/
	 @Test
		public    void formatFloat(){
		 float src=1.003f;
			DecimalFormat df1 = new DecimalFormat("####");
			String fomat= df1.format(src);
			int index=fomat.indexOf(".");
			if(index==0){
				fomat="0"+fomat;
			}
			System.out.println(index);
			System.out.println(fomat);
		}
		
	 @Test
	 /**dotLen->小数点长度*/
	 public void formatFloatDot(){
		 float src=1.3333f;
	      BigDecimal   b   =   new   BigDecimal(src);  
	      float   result   =   b.setScale(0,   BigDecimal.ROUND_HALF_UP).floatValue();  
	      System.out.println(result);
	 }
	 
	 @Test
	 public void level(){
		 String baseStr="001000000";
		 String insertStr="002";
		 String currCode=baseStr.substring(0, 6)+insertStr;
		 System.out.println(currCode);
	 }
	 @Test
	 public void hiddeDes(){
		 String src="12345678";
		 int len=src.length();
		 int firstShow=4;
		 int endShow=4;
		 if(len<firstShow+endShow){
			 
		 }
		 
		 String res1=src.substring(0, firstShow);
		 String res2=src.substring(len-endShow, len);
		 System.out.println("res1=="+res1);
		 System.out.println("res2=="+res2);
	 }
	 
	 @Test
	 public void len(){
//		 String src="http://123.56.86.235:8180/lc-agreement/oss/index?key=8302f0e6-03bf-4239-ab5f-2536cdbe83b6|ZONGBU-101513|447720|廖慧茹|住宅装修|0.15|24|24|90003980|2|2015-06-09 00:00:00.000|2017-06-09 00:00:00.000|楼易贷|6110824|0|B4107|||||32035|17628|ee19153f-7563-4e23-bd22-773b4163e11f|332501198205020020|10000.00|2015-07-09 00:00:00.000|P2014073000518301|0.91";
		 String src="";
		 String [] srces=src.split("\\|");
		 int len=srces.length;
		 System.out.println(len);
	 }
	 
	 
	 @Test
	 public void sqlInj(){
		 String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|chr|mid|master|truncate|" +  
	                "declare|sitename|net user|xp_cmdshell|or|like|create|" +  
	                "table|from|grant|use|group_concat|column_name|" +  
	                "information_schema.columns|table_schema|union|where|char" ;
		 String str="97RhPqb3#";
		 System.out.println("str--->"+str);
		 String strLower= StringUtils.lowerCase(str);
		 System.out.println("strLower--->"+strLower);
		 String[] badStrs = badStr.split("\\|");  
	        for (int i = 0; i < badStrs.length; i++) {  
	            if (strLower.indexOf(badStrs[i]) >= 0) {  
	            	System.out.println(badStrs[i]);
	            	return;
	            }  
	        } 
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