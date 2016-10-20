package com.ssj.gameUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间,日期
 * 
 * @author Hades
 * 
 */
public class Time {

	public static final long FullDayMillis = 1000*60*60*24l;//一整天的毫秒数
	public static final long OneHourMillis = 1000*60*60l;//一小时的毫秒数
	public static final long OneMinuteMillis = 1000*60l;//一分钟的毫秒数
	public static final long HalfHourMillis = OneHourMillis/2l;//一分钟的毫秒数
	
	/**
	 * 获得当天的0点的毫秒数
	 * @return
	 */
	public static long getTodayBeginMillis(){
		Calendar cal = Calendar.getInstance();
		
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
		return cal.getTimeInMillis();
	}

	
	/**
	 * 获取当天的日期，精确到小时(几点)
	 * @return
	 */
	public static long getTodayDateIncludeHour(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		return year*1000000+month*10000+day*100+hour;
	}
	
	/**
	 * 获得某一年的第几周
	 */
	
	public static long getTodayWeek(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		
		return year*100+week;
	}
	
	public static String getTodayBegin(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return getTimeStr(cal.getTimeInMillis());
	}
	
	public static String getTodayEnd(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return getTimeStr(cal.getTimeInMillis());
	}
	
	public static Date getDate(String str){
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return df.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDate(String ts,String format){
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.parse(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDateString(String ts,String format){
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(df.parse(ts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取当天的日期，精确到小时(几点)
	 * @return
	 */
	public static long getTodayDate(int hour){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
//		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		return year*1000000+month*10000+day*100+hour;
	}
	
	/**
	 * 获取当天的日期，精确到天
	 * @return
	 */
	public static long getDayLong(){		
		return getDayLong(Calendar.getInstance()) ;
	}
	
	public static long getYesterdayLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return getDayLong(calendar);
	}
	
	/**
	 * 获得精确到天的long型
	 * @param cal
	 * @return
	 */
	public static long getDayLong(Calendar cal){
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
	
		return year*10000+month*100+day;
	}
	
	/**
	 * 通过日期字符串获得日期,精确到天
	 * @param s
	 * @return
	 */
	public static long getDayLong(String s){
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = df.parse(s);			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			return getDayLong(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * 判断给定的time和当前时间是否是同一天
	 * @param time
	 * @return
	 */
	public static boolean dayChange(long time) {
		long currentTime = System.currentTimeMillis();
		return dayChange(currentTime, time);
	}
	
	
	public static boolean timeBeforeHour(long time,int hourInDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		if(h < hourInDay){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param time
	 * @param hourInDay
	 * @return
	 */
	public static boolean needToClearLucky(long time,int hourInDay){
		boolean dayChange = dayChange(time);
		//需求为每天清零
		if(hourInDay == 0){
			return dayChange;
		}
		else{
			long now = System.currentTimeMillis();
			boolean nowAfter = !timeBeforeHour(now, hourInDay);
			//如果隔天
			if(dayChange){
				//相隔天数
				int intervalDay = intervalDay(now, time);
				//如果相隔天数大于1，肯定清除；否则看当前时间是否大于设定的时间
				if(intervalDay > 1){
					return true;
				}
				return nowAfter;
			}
			//同一天
			else{
				boolean logoutBefore =  timeBeforeHour(time, hourInDay);
				//如果上次下线时间比设定时间早，并且当前时间比设定时间晚，清除；否则不清
				if(logoutBefore && nowAfter){
					return true;
				}
				return false;
			}
		}
	}
	
	public static boolean hourChange(long time1,long time2){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(time1);
		c2.setTimeInMillis(time2);
		int h1 = c1.get(Calendar.HOUR_OF_DAY);
		int h2 = c2.get(Calendar.HOUR_OF_DAY);
//		System.out.println("..." + h1 + ", " + h2);
		if(h1 != h2){
			return true;
		}
		
		int d1 = c1.get(Calendar.DAY_OF_YEAR);
		int d2 = c2.get(Calendar.DAY_OF_YEAR);
		if(d1 != d2){
			return true;
		}
		
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c1.get(Calendar.YEAR);
		if(y1 != y2){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断间隔多少天, time1 > time2  返回0或正值, time1 < time2 返回0或负值
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int intervalDay(long time1, long time2){
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(time1);
		c2.setTimeInMillis(time2);
		
		
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int d1 = c1.get(Calendar.DAY_OF_YEAR);
		int d2 = c2.get(Calendar.DAY_OF_YEAR);
		int intervalYear = y1 - y2;
		
		if (intervalYear != 0){
			if(intervalYear > 0){
				int interval = c2.getActualMaximum(Calendar.DAY_OF_YEAR) - d2 + d1;
				for(int i = 1; i < intervalYear; ++i){
					c2.add(Calendar.YEAR, 1);
					interval += c2.getActualMaximum(Calendar.DAY_OF_YEAR);
				}
				return interval;
			}else{
				int interval = c1.getActualMaximum(Calendar.DAY_OF_YEAR) - d1 + d2;
				for(int i = 1; i < Math.abs(intervalYear); ++i){
					c1.add(Calendar.YEAR, 1);
					interval += c1.getActualMaximum(Calendar.DAY_OF_YEAR);
				}
				return -interval;
			}
		}else{
			return d1 - d2;
		}
	}

	
	public static String getTodayCalendar(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	public static int getRemineTime(){
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		
		int count = 0;
		if(23 - h > 0){
			count = 60 * 60 * (23 - h);
		}
		if(59 - m > 0){
			count += 60 * (59 - m);
		}
		count += 60 - s;
		
		return count;
	}
	
	public static String getTimeStr(long time){
		Date date= new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	public static String getTimeStr(long time,String formatStr){
		Date date= new Date(time);
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	
	public static boolean isNextDay(long l1, long l2){
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(l1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(l2);
		
		c1.add(Calendar.DATE, 1);
//		if(c1.get(Calendar.YEAR) == c2.)
		return false;

	}
	
	public static boolean isSuccessiveForDay(long l1, long l2)
	{
		long min = l1;
		long max = l2;
		if(l1 > l2)
		{
			min = l2;
			max = l1;
		}
		long maxOneDayFor = 24 * 60 * 60 * 1000;
		long maxYesterday = max - maxOneDayFor;
		
		if(maxYesterday <= min) return true;
		
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(min);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(maxYesterday);
		
		if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
		{
			return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
		}
		
		return false;
	}
	
	
	
	public static boolean dayChange(long l1, long l2){
//		TimeZone tz = TimeZone.getTimeZone("GMT+14:00");        // 获得时区
		Calendar c1 = Calendar.getInstance();       // 设置时区                       
//		c1.setTimeZone(tz);		
		c1.setTimeInMillis(l1);
		
		Calendar c2 = Calendar.getInstance();
//		c2.setTimeZone(tz);		
		c2.setTimeInMillis(l2);
		boolean dayChanged = false;
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		if (y1 != y2){
			dayChanged = true;
		}else{
			int d1 = c1.get(Calendar.DAY_OF_YEAR);
			int d2 = c2.get(Calendar.DAY_OF_YEAR);
			if (d1 != d2){
				dayChanged = true;
			}
		}
		c1 = null;
		c2 = null;
		return dayChanged;
	}

	
	/**
	 * 判断制定的时间time是否在制定时间范围timeRange内
	 * @param timeRange	4个字节分别存储startHour startMinute endHour endMinute
	 * @param time
	 * @return
	 */
	public static boolean inTimeRange(int timeRange, long time){
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(time);
		int hour = c1.get(Calendar.HOUR_OF_DAY);
		int minute = c1.get(Calendar.MINUTE);
		int reslult = hour * 60 + minute;
		
		int s_hour = (timeRange >> 24) & 0x00FF;
		int s_minute = (timeRange >> 16) & 0x00FF;
		int s_result = s_hour * 60 + s_minute;
		
		
		int e_hour = (timeRange >> 8) & 0x00FF;
		int e_minute = timeRange & 0x00FF;
		int e_result = e_hour * 60 + e_minute;
		
		if(s_result <= reslult && reslult <= e_result){
			return true;
		}
		return false;
	}
	
	/**
	 * 间隔毫秒数转换为字符串时间,最高单位为天,精确到分
	 * @param millis
	 * @return
	 */
	public static String millisToString(long millis){
		StringBuffer sb = new StringBuffer();
		int days = (int)(millis / FullDayMillis);//天
		int hours = (int)((millis - days*FullDayMillis)/OneHourMillis);
		int mins = (int)((millis - days*FullDayMillis - hours* OneHourMillis)/OneMinuteMillis);//分
		
		if(days > 0){
			sb.append(days).append("天");
		}
		if(hours > 0 || days > 0){
			sb.append(hours).append("小时");
		}
		sb.append(mins).append("分");
		return sb.toString();
	}
	
	/**
	 * 获得当前月一号所在的那个星期的星期天的时间
	 * @return
	 */
	public static long getSunday(){
		Calendar calendar = Calendar.getInstance();//当前时间
//		calendar.set(Calendar.MONTH, 6);//本月一号	
		calendar.set(Calendar.DAY_OF_MONTH, 1);//本月一号	
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//		if(dayOfWeek != 1){
			calendar.add(Calendar.DATE, 1-dayOfWeek);
//		}
//		else{
//			calendar.add(Calendar.DATE, -7);
//		}
//		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
//		System.out.println(calendar.getTimeInMillis());
//		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		
		return calendar.getTimeInMillis();
	}
	
	public static int getDayOfMonth(){
		Calendar calendar = Calendar.getInstance();//当前时间
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getMonth(){
		Calendar calendar = Calendar.getInstance();//当前时间
		return calendar.get(Calendar.MONTH);
	}
	
	public static long getTimeByDay(int day){
		Calendar calendar = Calendar.getInstance();//当前时间		
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTimeInMillis();
	}
	
	public static long getToday(){
		Date date= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Long.parseLong(format.format(date));
	}
	
	public static long getTimeStringToMills(String timeStr){
		Date date = getDate(timeStr);
		if(date == null){
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	/**
	* *@author shen
	* TODO 包含起始/终止时间当天(0708 11:00--0709 10:00为两天),支持跨年计算
	*/
	public static  int daysBetween(long start,long end) 
    {   
		if(start<=0 || end<=0){
			return 0;
		}
        Calendar cal = Calendar.getInstance(); 
        cal.setTimeInMillis(start);
        
        Calendar ca2 = Calendar.getInstance(); 
        ca2.setTimeInMillis(end);
        
        int day1=cal.get(Calendar.DAY_OF_YEAR);
        int day1_=cal.getActualMaximum(Calendar.DAY_OF_YEAR)-day1;
        int year1=cal.get(Calendar.YEAR);
        int day2=ca2.get(Calendar.DAY_OF_YEAR);
        int year2=ca2.get(Calendar.YEAR);
        int dayoff=0;
        if(cal.before(ca2)){
        	if(year1==year2){
        		dayoff=day2-day1+1;
        	}else{
        		 Calendar temp = Calendar.getInstance(); 
        		 temp.set(Calendar.YEAR, year1);
        		for(int i=1;i<year2-year1;i++){
        			temp.add(Calendar.YEAR, 1);
        			dayoff+=temp.getActualMaximum(Calendar.DAY_OF_YEAR);
        		}
        		dayoff+=(day2+day1_)+1;
        	}
        }
        return dayoff;
    }   

	public static Date getNowDate(){
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/**
	* *@author shen
	* TODO 获取N年M月最后一周星期X的日期
	* @param month
	* @param week  星期X
	 */
	public static Calendar getDateForWeekInLastWeek(int year,int month,int week) {
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, 1);
	    cal.add(Calendar.DATE, -1);
	    int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	    int offSet=0;
	    if(dayOfWeek>5){
	    	offSet=week-dayOfWeek;
	    }else{
	    	offSet=-((7-week+1)+(dayOfWeek-1));
	    }
	    cal.add(Calendar.DATE, offSet);
	    return cal;
    } 
	
	/**
	* *@author shen
	* TODO N年M月第X周星期Y的日期
	* @param year
	* @param month
	* @param week_n 第N个星期
	* @param week   星期X
	 */
	public static  Calendar getDateForInAnyweek(int year,int month,int week_n,int week) {//yyyy-MM-dd hh:mm:ss  
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	    if(dayOfWeek!=1){
	    	week_n+=1;
	    }
	    cal.set(Calendar.WEEK_OF_MONTH,week_n);      //将日历设到该月的第二个7天
	    cal.set(Calendar.DAY_OF_WEEK,week);//将日历设为星期week
	    return cal;
    } 
	
	
	public static int remainTime(String endStr){
//		String endStr = this.getEndTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long endTime = 0l;
		try {
			Date date = df.parse(endStr);
			endTime = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar nc = Calendar.getInstance();
		long nowTime = nc.getTimeInMillis();
		int result = (int)(endTime-nowTime)/(1000*3600*24);
		return result < 0 ? 0 : result;
	}
	
	/**
	 * date转换：剩余天数
	 * @param endStr
	 * @return
	 */
	public static int remainDay(Date date){
		long endTime = 0l;
		try {
			endTime = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar nc = Calendar.getInstance();
		long nowTime = nc.getTimeInMillis();
		int result = (int)(endTime-nowTime)/(1000*3600*24);
		return result < 0 ? 0 : result;
	}
	

	/**
	 * date转换：活动总共多少天
	 * @param endStr
	 * @return
	 */
	public static int totalDay(Date startDate,Date endDate){
		long startTime = 0l;
		long endTime = 0l;
		try {
			startTime = startDate.getTime();
			endTime = endDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = (int)(endTime-startTime)/(1000*3600*24);
		return result < 0 ? 0 : result;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		
//		getSunday();
		
//		Calendar calendar = Calendar.getInstance();//当前时间	
//		calendar.add(Calendar.MONTH, -1);//本月一号		
//		calendar.set(Calendar.DAY_OF_MONTH, 1);//本月一号		
////		calendar.add(Calendar.DATE, 1-calendar.get(Calendar.DAY_OF_WEEK));
//		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//		if(dayOfWeek == 1){
//			
//		}else{
//			calendar.add(Calendar.DAY_OF_WEEK, 1-dayOfWeek);
//		}
		
//		Date date = Time.getDate("20131202235900", "yyyyMMddHHmmss");
//		
//		System.out.println(Time.getTimeStr(date.getTime()));
		
	
		
//		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		
		
//		TimeZone tz = TimeZone.getTimeZone("GMT-7:00");      
//		Calendar calendar = Calendar.getInstance();//当前时间
	/*	calendar.setTimeZone(tz);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(hour + "," + day + "," + getYesterdayLong());*/
		
		
//		System.out.println(getDayLong(calendar));
		
//		System.out.println(""+getTimeStr(1387868628361l));
		
//		long now = System.currentTimeMillis();
//		String s  = "2013-11-01 02:12:50";
//		
//		long time = getTimeStringToMills(s);
//		
//		System.out.println(now + "," + time + "," + (now - time));
		
		//long time = 1375939425953l;
//		long time = System.currentTimeMillis() - 16 * 60 * 60 * 1000;
//		boolean change = dayChange(time, System.currentTimeMillis(), 14);
//		boolean change2 = dayChange(time, System.currentTimeMillis());
//		System.out.println("是否过过天了 = " + change + ","+change2);
		
//		int day = remainTime("2013-12-28 23:59:59");
//		System.out.println("=========" +day +  "天");
	}
}
