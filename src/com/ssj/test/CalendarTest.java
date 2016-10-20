package com.ssj.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Test;

import com.mysql.jdbc.Constants;
import com.ssj.util.CalendarUtil;
import com.ssj.util.DateUtil;

public class CalendarTest 
{
	@Test
	public void huoLiBao () 
	{
		
			Calendar buyCal=Calendar.getInstance();
			//购买时间 reset 00:00:00
			String buyTimeStr="";
			buyCal.set(2013, 5,20,0,0,0);
			 buyTimeStr=DateUtil.converCalendarToString(buyCal, "yyyy-MM-dd HH:mm:ss");
			 System.out.println("购买时间===="+buyTimeStr);
			 
			 //结束时间reset 23:59:59
			 Calendar endCal=(Calendar)buyCal.clone();
			 endCal.add(Calendar.MONTH, 12);
			 endCal.set(Calendar.HOUR_OF_DAY,23 );
			 endCal.set(Calendar.MINUTE,59 );
			 endCal.set(Calendar.SECOND, 59);
			 String endCalStr=DateUtil.converCalendarToString(endCal, "yyyy-MM-dd HH:mm:ss");
			 System.out.println("结束时间===="+endCalStr);
			 
			 Calendar nowCal=Calendar.getInstance();
			nowCal.set(2014,5,20,0,0,0);
			String nowCalStr=DateUtil.converCalendarToString(nowCal, "yyyy-MM-dd HH:mm:ss");
			System.out.println("now时间===="+nowCalStr);
			
			int month_quJian=2;
			if(nowCal.after(endCal)){
				month_quJian =CalendarUtil.intervalMonth(endCal,buyCal);
			}else{
				 for(int i=1;i<=6;i++){
					 
					 Calendar pointCal=(Calendar)buyCal.clone();
					 pointCal.add(Calendar.MONTH, month_quJian);
					 pointCal.set(Calendar.HOUR_OF_DAY,0 );
					 pointCal.set(Calendar.MINUTE,0 );
					 pointCal.set(Calendar.SECOND, 0);
					 String pointCalStr=DateUtil.converCalendarToString(pointCal, "yyyy-MM-dd HH:mm:ss");
					 System.out.println("持有月区间点=="+month_quJian+"=="+pointCalStr);
					 if(nowCal.before(pointCal) ){
						 break;
					 }
					 month_quJian+=2;
					 //修正
					 month_quJian=month_quJian>12?12:month_quJian;
				 }
			}
			System.out.println("month_quJian===="+month_quJian);
	}
	
	@Test
	public void inverteDay(){
		Calendar smallCal=Calendar.getInstance();
		smallCal.set(2016, 5,5,12,0,0);
		 Calendar bigCal=Calendar.getInstance();
		 bigCal.set(2017,5,5,12,0,0);
		 String smallCalStr=DateUtil.converCalendarToString(smallCal, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("smallCalStr时间点==="+smallCalStr);
		 String bigCalStr=DateUtil.converCalendarToString(bigCal, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("bigCalStr时间点==="+bigCalStr);
		 
		int days=CalendarUtil.intervalDays(bigCal.getTime(), smallCal.getTime());
		System.out.println("days========="+days);
	}
	
	@Test
	public void inverteMonth(){
		Calendar smallCal=Calendar.getInstance();
		smallCal.set(2014, 12,31,0,0,0);
		 Calendar bigCal=Calendar.getInstance();
		 bigCal.set(2015, 1,28,0,0,0);
		 String smallCalStr=DateUtil.converCalendarToString(smallCal, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("smallCalStr时间点==="+smallCalStr);
		 String bigCalStr=DateUtil.converCalendarToString(bigCal, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("bigCalStr时间点==="+bigCalStr);
		 
		int month=CalendarUtil.intervalMonth(bigCal, smallCal);
		System.out.println("months========="+month);
	}
	
	@Test
	public void canChangeXT(){
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.set(2014, 5,20,13,10,56);
		Calendar endCal_=Calendar.getInstance();
		endCal_.set(2014, 9,20,13,10,56);
		Date buyTime=buyCal_.getTime();
		Date endTime=endCal_.getTime();
		
		
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("flag", true);
		
		Calendar startCal=Calendar.getInstance();
				startCal.setTime(buyTime);
				DateUtil.resetShortCal(startCal, 0, 0, 0);//购买时间 reset 00:00:00
		String startCalStr=DateUtil.converCalendarToString(startCal, "yyyy-MM-dd HH:mm:ss");
		System.out.println("购买时间====="+startCalStr);
		
		Calendar endCal=Calendar.getInstance();
			endCal.setTime(endTime);
			DateUtil.resetShortCal(endCal, 0, 0, 0);
		String endCalStr=DateUtil.converCalendarToString(endCal, "yyyy-MM-dd HH:mm:ss");
		System.out.println("当前结束时间====="+endCalStr);
			
		Calendar secondMmaxLastendCal=Calendar.getInstance();//倒数第二长个时间点
		secondMmaxLastendCal.setTime(buyTime);
		secondMmaxLastendCal.add(Calendar.MONTH, 10);
		DateUtil.resetShortCal(secondMmaxLastendCal, 0, 0, 0);
		secondMmaxLastendCal.add(Calendar.DATE, -5);
		String secondMmaxLastendCalStr=DateUtil.converCalendarToString(secondMmaxLastendCal, "yyyy-MM-dd HH:mm:ss");
		System.out.println("倒数第二结束时间点====="+secondMmaxLastendCalStr);
		
		Calendar startFreezeCal=(Calendar)endCal.clone();//倒数第二个时间点
		startFreezeCal.add(Calendar.DATE, -5);
		DateUtil.resetShortCal(startFreezeCal, 0, 0, 0);
		String startFreezeCalStr=DateUtil.converCalendarToString(startFreezeCal, "yyyy-MM-dd HH:mm:ss");
		System.out.println("开始冻结时间点====="+startFreezeCalStr);
				
		Calendar nowCal=Calendar.getInstance();
		 DateUtil.resetShortCal(nowCal, 0, 0, 0);//现在时间 reset 00:00:00
		 String nowCalStr=DateUtil.converCalendarToString(nowCal, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("现在时间====="+nowCalStr);
	}
	@Test
	public void addDays(){
	/*	2015-05-29 16:36:41
		2016-05-28 16:36:41*/
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.set(2015, 5,9,9,29,10);
		 String buyCal_Str=DateUtil.converCalendarToString(buyCal_, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("现在时间====="+buyCal_Str);
		 
		Calendar endCal_=(Calendar)buyCal_.clone();
		endCal_.add(Calendar.DATE, 90);
		 String endCal_Str=DateUtil.converCalendarToString(endCal_, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("结束时间====="+endCal_Str);
	}
	
	@Test
	public void addMonth(){
	/*	2015-05-15 17:10:16
		2015-08-13 05:10:16*/
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.set(2015, 5,14,8,29,10);//2015, 5,8,8,29,10
		 String buyCal_Str=DateUtil.converCalendarToString(buyCal_, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("现在时间====="+buyCal_Str);
		 
		Calendar endCal_=(Calendar)buyCal_.clone();
		endCal_.add(Calendar.MONTH,-10);
		 String endCal_Str=DateUtil.converCalendarToString(endCal_, "yyyy-MM-dd HH:mm:ss");
		 System.out.println("现在时间====="+endCal_Str);
	}
	
	@Test
	public void lilv(){
		float rate=7.4f;  //  61--->1629.452  150.41096   72--->1923.2877   177.53424
		int holding_period=45;
		float buy_amount=120000.00f;
		float result= rate*holding_period*buy_amount/100.0f/365.0f;
		System.out.println("liLv:"+result);
	}
	
	@Test
	public void hlbEndPonit(){
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.set(2014,7,16,8,29,10);//2015, 5,8,8,29,10
		Date buyTime=buyCal_.getTime();
		int inventMonth=12;//持有月
		Calendar buyCal=baseCalAndDateTimeCalendar(Calendar.getInstance(),buyTime);
		 System.out.println("购买时间====="+printCal(buyCal));
		 HashMap<String, Integer> buyCalMap=parseCal(buyCal);
		 int buyCalY=buyCalMap.get("Y");
		 int buyCalM=buyCalMap.get("M");
		 int buyCalD=buyCalMap.get("D");
		 int buyCalH=buyCalMap.get("H");
		 int buyCalm=buyCalMap.get("m");
		 int buyCalS=buyCalMap.get("S");
		 
		 int endCalY=buyCalY;
		 int endCalM=buyCalM+inventMonth;
		 int endD=1;
		 if(endCalM>11){
			 endCalM=(endCalM-12);
			 endCalY+=1;
		 }
		 System.out.println("未修正结束时月份====="+(endCalM+1));
		 Calendar endCalendar=baseCalLongTimeNewCal(buyCal,endCalY,endCalM,endD,buyCalH,buyCalm,buyCalS);
		 fixEndPonit(endCalendar, buyCalD);
		 
	}
	
	private static  Calendar baseCalLongTimeNewCal(Calendar base,
								Integer year,Integer month,Integer day,
								Integer hour,Integer minute,Integer second){
		Calendar cal=(Calendar)base.clone();
		if(year!=null){
			cal.set(Calendar.YEAR,year);
		}
		if(month!=null){
			cal.set(Calendar.MONTH,month);
		}
		if(day!=null){
			cal.set(Calendar.DATE,day);
		}
		if(hour!=null){
			cal.set(Calendar.HOUR_OF_DAY,hour);
		}
		if(minute!=null){
			cal.set(Calendar.MINUTE,minute);
		}
		if(second!=null){
			cal.set(Calendar.SECOND, second);
		}
		return cal;
	}
	
	private static HashMap<String, Integer> parseCal(Calendar cal){
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("Y", cal.get(Calendar.YEAR));
		map.put("M", cal.get(Calendar.MONTH));
		map.put("D",cal.get(Calendar.DATE) );
		map.put("H",cal.get(Calendar.HOUR)  );
		map.put("m", cal.get(Calendar.MINUTE) );
		map.put("S", cal.get(Calendar.SECOND) );
		System.out.println("Y:"+map.get("Y")+"M:"+(map.get("M")+1)+"D:"+map.get("D")+"H:"+map.get("H")+"m:"+map.get("m")+"S:"+map.get("S"));
		 return map;
	}
	/**cal到期日当月1号    buyDay 购买日日期号 1、28 、31...*/
	private static Calendar  fixEndPonit(Calendar cal,int buyDay){
			Calendar fixCal=baseCalAndDateTimeCalendar(cal,null);
			System.out.println("未修正后最终到期时间====="+printCal(fixCal));
			int daysOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//当月总天数
			System.out.println("cal到期日当月总天数======="+daysOfMonth);
			if(daysOfMonth<buyDay){
				fixCal.set(Calendar.MONTH, fixCal.get(Calendar.MONTH)+1);
				fixCal.add(Calendar.DATE, -1);
			}else{
				fixCal.set(Calendar.DATE, buyDay);
			}
			 System.out.println("修正后最终到期时间====="+printCal(fixCal));
			return fixCal;
	}
	
	
	private static Calendar baseCalAndDateTimeCalendar(Calendar base,Date time){
		Calendar newCal=(Calendar)base.clone();
		if(time!=null){
			newCal.setTime(time);
		}
		return newCal;
	}
	private static String printCal(Calendar cal){
		 return  DateUtil.converCalendarToString(cal, "yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void sysnCalHMS(){
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.set(2014,7,16,15,29,10);
		Calendar enCal_=Calendar.getInstance();
		enCal_.set(2014,7,16,8,31,12);
		
		String buyCal_Str=DateUtil.converCalendarToString(buyCal_, "yyyy-MM-dd HH:mm:ss");
		String enCalStr=DateUtil.converCalendarToString(enCal_, "yyyy-MM-dd HH:mm:ss");
		System.out.println("buyCal_Str--->"+buyCal_Str);
		System.out.println("enCalStr--->"+enCalStr);
		enCal_.set(Calendar.HOUR_OF_DAY,buyCal_.get(Calendar.HOUR_OF_DAY));
		enCal_.set(Calendar.MINUTE,buyCal_.get(Calendar.MINUTE));
		enCal_.set(Calendar.SECOND,buyCal_.get(Calendar.SECOND));
		String enCalStr2=DateUtil.converCalendarToString(enCal_, "yyyy-MM-dd HH:mm:ss");
		System.out.println("enCalStr2--->"+enCalStr2);
	}
	
	@Test
	public void sysnDateHMS(){
		Date buyDate=new Date();
		Calendar buyCal_=Calendar.getInstance();
		buyCal_.setTime(buyDate);
		Calendar enCal_=Calendar.getInstance();
		enCal_.set(2014,7,16,8,31,12);
		
		String buyCal_Str=DateUtil.converCalendarToString(buyCal_, "yyyy-MM-dd HH:mm:ss");
		String enCalStr=DateUtil.converCalendarToString(enCal_, "yyyy-MM-dd HH:mm:ss");
		System.out.println("buyCal_Str--->"+buyCal_Str);
		System.out.println("enCalStr--->"+enCalStr);
		enCal_.set(Calendar.HOUR_OF_DAY,buyCal_.get(Calendar.HOUR_OF_DAY));
		enCal_.set(Calendar.MINUTE,buyCal_.get(Calendar.MINUTE));
		enCal_.set(Calendar.SECOND,buyCal_.get(Calendar.SECOND));
		String enCalStr2=DateUtil.converCalendarToString(enCal_, "yyyy-MM-dd HH:mm:ss");
		System.out.println("enCalStr2--->"+enCalStr2);
	}
	
}
