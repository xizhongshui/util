package com.ssj.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;

public class ChangeType {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		DecimalFormat df = new DecimalFormat("0");
		/*
		//格式化科学计数法
		DecimalFormat df = new DecimalFormat("0.00");
		double d=1.3569135278E10;
		String valueStr=df.format(d);
		System.out.println(valueStr);//13569135278
		
		//格式化小数
		 df = new DecimalFormat("#00.00#");
		 System.out.println(df.format(33.3));//33.30*/
		//格式化百分数
		df = new DecimalFormat("#.00#%");
		System.out.println(df.format(0.33333));//33.333%
	}
	
	@Test
	public void test(){
		String src="-3.2"; 
		float a=-2.0f;
		System.out.println(Float.parseFloat(src));
	}
	
	@Test
	public void testBigdecimalObj(){
		BigDecimal inviteRate=new BigDecimal(1);
		int holding_period=12;
		BigDecimal buy_amount=new BigDecimal(10000);
		BigDecimal expectedYieldAmount =new BigDecimal(0);
		System.out.println("before expectedYieldAmount"+expectedYieldAmount.doubleValue());
		System.out.println("before inviteRate"+inviteRate.doubleValue());
		try {
			
			expectedYieldAmount=inviteRate.divide(new BigDecimal(3),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(12),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(holding_period)).multiply(buy_amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("after expectedYieldAmount"+expectedYieldAmount.doubleValue());
		System.out.println("after inviteRate"+inviteRate.doubleValue());
	}
	
	@Test
	public void testBigdecimalLoop(){
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal inviteRate=new BigDecimal(1);
		BigDecimal buy_amount=new BigDecimal(3);
		BigDecimal bs=new BigDecimal(3.3333);
		BigDecimal expectedYieldAmount =new BigDecimal(0);
		BigDecimal newVal =new BigDecimal(0);
		System.out.println("before expectedYieldAmount"+expectedYieldAmount.doubleValue());
		System.out.println("before inviteRate"+inviteRate.doubleValue());
		try {
			
			expectedYieldAmount=inviteRate.divide(buy_amount,2,BigDecimal.ROUND_HALF_UP).multiply(bs);
			newVal=expectedYieldAmount.setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("after expectedYieldAmount"+expectedYieldAmount.doubleValue());
		System.out.println("after inviteRate"+inviteRate.doubleValue());
		System.out.println("after newVal"+newVal.doubleValue());
	}
	
	@Test
	public void divTest(){
		BigDecimal  annualizedRate=new BigDecimal(6.5);
		BigDecimal  buy_amount=new BigDecimal(10000);
		int holding_period=2;
//		6.5 annualizedRate,String type_value,int 2->holding_period,BigDecimal 10000-> buy_amount
		double rate= annualizedRate.divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP)
													.divide(new BigDecimal(365),6,BigDecimal.ROUND_HALF_UP)
													.multiply(new BigDecimal(holding_period)).multiply(buy_amount).doubleValue();
		 System.out.println(rate);
	}
	
	@Test
	public void   formatDoubleStr(){
		double src=1236458954.23165;
		String dots=".00";
		DecimalFormat df1 = new DecimalFormat("####"+dots);
		String fomat= df1.format(src);
		int index=fomat.indexOf(".");
		if(index==0){
			fomat="0"+fomat;
		}
		System.out.println(fomat);
	}
}
