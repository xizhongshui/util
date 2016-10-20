package com.ssj.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.cage.Cage;
import com.github.cage.GCage;

public class Test {

	public static void main(String[] args) {

		Random rand = new Random();
		int sum = 100;
		int s = 0;
		int m = 0;
		int k = 0;
		int l = 0;
		int p = 0;
		int total=0;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < sum; i++) {
			total++;
			int r = rand.nextInt(4500)+500;
			if (r>=500 && r<1000) {
				if (s <= 60) {
					list.add(r);
					s++;
				}
			}
			
			if (r>=1000 && r<2000 ) {
				if (m <= 20) {
					list.add(r);
					m++;
				}
			}
			
			if (r>=2000 && r<3000) {
				if (k <= 10) {
					list.add(r);
					k++;
				}
			}
			
			if (r>=3000 && r<4000) {
				if (l <= 5) {
					list.add(r);
					l++;
				}
			}
			
			if (r>=4000 && r<=5000) {
				if (p <= 5) {
					list.add(r);
					p++;
				}
			}
		}
		
		
		/*for (int i = 0; i < 100; i++) {
			int pp = rand.nextInt(100);
			System.out.print(list.get(pp) + ",");
		}*/
		
		System.out.println("list:"+list);
		System.out.println("500~1000:"+s);
		System.out.println("1000~2000:"+m);
		System.out.println("2000~3000:"+k);
		System.out.println("3000~4000:"+l);
		System.out.println("4000~5000:"+p);
		System.out.println("total===="+total);
		
	}
	
    public static String converContinueStatutsToCenter(String localStatus){
    	String result="";
    	switch(localStatus){
        	case "A01": result="B03"; break;
        	default: result=localStatus; break;
    	}
    	return result;
    }
    
    /**转换订单中心续投状态到本地序*/
    public static String converContinueStatutsToLocal(String centerStatus){
    	String result="";
    	switch(centerStatus){
        	case "B03": result="A01"; break;
        	default: result=centerStatus; break;
    	}
    	return result;
    }
	
	@org.junit.Test
	public void test(){
		/*String local="C01";
		String result=Test.converContinueStatutsToCenter(local);
		System.out.println(result);*/
		
		String local="32_T|32_T|32_T|32_T|32_T|32_T|32_T|32_T|32_T|32_T|32_T|";
		System.out.println(local.length());
	}
	
	@org.junit.Test
	public void testYZM(){
		Cage cage = new GCage();
		OutputStream os=null;
	    try {
	    	 os= new FileOutputStream("e://captcha.jpg", false);
	    	 String text=cage.getTokenGenerator().next();
	    	 cage.draw(text, os);
	    } catch (IOException e) {
		} finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    }
	}
	
	@org.junit.Test
	public void validateCode(){
			String []src={"a","b","c","d","e","f","g","h","i","g","k","l"
									,"m","n","o","p","q","r","s","t","u","v","w","x"
									,"y","z","0","1","2","3","4","5","6","7","8","9" };//"abcdefghigklmnopqrstuvwxyz1234567890";
			StringBuffer sb=new StringBuffer();
			Random ran=new Random();
			for(int i=0;i<=3;i++){
					int site=ran.nextInt(36);
					sb.append(src[site]);
			}
		    Cage cage = new GCage();
		    OutputStream os =null;
		    try {
			    	os= new FileOutputStream("e://captcha.jpg", false);
			    	cage.draw(sb.toString(), os);
		    } catch (IOException e) {
					e.printStackTrace();
			} finally {
				      try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    }
	}
	
	

}
