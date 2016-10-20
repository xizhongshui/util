package com.ssj.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
public class DateUtil {
	
	//Date 到 String formatStr可以是任意合法格式
	public static String dateToString(Date date,String formatStr)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(date);
	}
	
	//Date 到 String: formatStr可以是任意合法格式
	public static String dateToString(Timestamp timestamp,String formatStr)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(timestamp);
	}
	
	// String到 Date: string和formatStr格式必须一致
	public static Date stringToDate(String dateStr,String formatStr) throws ParseException
	{
		Date date=null;
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			date= formatter.parse(dateStr);
		return date;
	}
	
	public static String converCalendarToString(Calendar cal, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	
/*	public static void main(String []args)
	{
		Date now=new Date();
		Timestamp timestamp=new Timestamp(now.getTime());
		System.out.println("===============now============"+now);
		System.out.println("===============timestamp============"+timestamp);
		String dateStrLong=DateUtil.dateToString(now, "yyyy-MM-dd hh:mm:ss");
		String dateStrShort=DateUtil.dateToString(now, "yyyy-MM-dd");
		System.out.println("===============dateStrShort============"+dateStrShort);
		String dateStrS="2013-02-06";
		Date strToDateS=DateUtil.stringToDate(dateStrS, "yyyy-MM-dd");
		System.out.println("===============strToDateS============"+strToDateS);
		String dateStrL="2013-02-06 15:26:24";
		Date strToDateL=DateUtil.stringToDate(dateStrL, "yyyy-MM-dd hh:mm:ss");
		System.out.println("===============strToDateL============"+strToDateL);
		Date now2=new Date(now.getTime()+1000*60*60*24);
		dateStrLong=DateUtil.dateToString(now2, "yyyy-MM-dd hh:mm:ss");
		System.out.println("===============dateStrLong============"+dateStrLong);
		
		System.out.println("===============now年============"+now.getDay());
		System.out.println("===============now月============"+dateStrLong);
		System.out.println("===============now周============"+dateStrLong);
		System.out.println("===============now星期============"+dateStrLong);
	}*/

	/**重置短时间*/
	public static  void resetShortCal(Calendar cal,int hour,int minute,int second){
		cal.set(Calendar.HOUR_OF_DAY,hour);
		cal.set(Calendar.MINUTE,minute);
		cal.set(Calendar.SECOND, second);
	}
	
	public static Calendar baseCalAndDateTimeCalendar(Calendar base,Date time){
		Calendar newCal=(Calendar)base.clone();
		if(time!=null){
			newCal.setTime(time);
		}
		return newCal;
	}
	
	public static String printCal(Calendar cal){
		 return  DateUtil.converCalendarToString(cal, "yyyy-MM-dd HH:mm:ss");
	}
	@Test
	public void test(){
		try {
			stringToDate("20150701","yyyyMMdd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}