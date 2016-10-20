package com.ssj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CalendarUtil {
	private static Logger logger=Logger.getLogger(CalendarUtil.class);
	public static void main(String[] args) 
	{
		CalendarUtil calUtil=new CalendarUtil();
		GregorianCalendar c1 = new GregorianCalendar(); 
		c1.set(2013, 4,20,0,0,0);
		Date date=c1.getTime();
		System.out.println("===============date============"+date);
		String dateStr1=DateUtil.dateToString(date, "yyyy-MM-dd hh:mm:ss");
		System.out.println("===============dateStr1============"+dateStr1);
		/*//获得属性
		System.out.println("===============c1.get(Calendar.YEAR)============"+c1.get(Calendar.YEAR));
		System.out.println("===============c1.get(Calendar.MONTH)============"+c1.get(Calendar.MONTH));//一月---0
		System.out.println("===============c1.get(Calendar.DATE)============"+c1.get(Calendar.DATE));
		
		System.out.println("===============c1.get(Calendar.DAY_OF_YEAR)============"+c1.get(Calendar.DAY_OF_YEAR));
		System.out.println("===============c1.get(Calendar.DAY_OF_MONTH)============"+c1.get(Calendar.DAY_OF_MONTH));
		System.out.println("===============c1.get(Calendar.DAY_OF_WEEK)============"+c1.get(Calendar.DAY_OF_WEEK));//星期天---1
		System.out.println("===============c1.get(Calendar.DAY_OF_WEEK_IN_MONTH)============"+c1.get(Calendar.DAY_OF_WEEK_IN_MONTH));//以7天时间段为记
		
		System.out.println("===============c1.get(Calendar.WEEK_OF_MONTH)============"+c1.get(Calendar.WEEK_OF_MONTH));//以星期排列为记
		System.out.println("===============c1.get(Calendar.WEEK_OF_YEAR)============"+c1.get(Calendar.WEEK_OF_YEAR));
		System.out.println("=========getActualMaximum(Calendar.DAY_OF_MONTH)当月天数============"+c1.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("=========getActualMaximum(Calendar.DAY_OF_YEAR)当年天数============"+c1.getActualMaximum(Calendar.DAY_OF_YEAR));*/
		
		/*System.out.println("===============获得所指日期在周的第一天（星期一）的日期============"+calUtil.getMondayOFWeek(c1, "yyyy-MM-dd"));
		System.out.println("===============获得本周任意一天的日期============"+calUtil.getDateInCurrentweek(3, c1, "yyyy-MM-dd"));
		System.out.println("===============获得相对于当前任意周任意一天的日期============"+calUtil.getDateInAnyweek(-1,3, c1, "yyyy-MM-dd"));
		System.out.println("===============获得相对于当前任意月第一天的日期============"+calUtil.getFirstDayInAnymonth(-5, c1, "yyyy-MM-dd"));
		System.out.println("===============获得相对于当前任意月最后一天的日期============"+calUtil.getLastDayInAnymonth(1, c1, "yyyy-MM-dd"));*/
		Sesson sesson=calUtil.getCurrentSessonValue(c1);
		
		System.out.println("===============获得相对于当前时间的本季度信息======当前季度======"+sesson.getCurrentSesson());
		System.out.println("===============获得相对于当前时间的本季度信息=======开始月====="+sesson.getStartMonth());
		System.out.println("===============获得相对于当前时间的本季度信息========第二月===="+sesson.getSecondMonth());
		System.out.println("===============获得相对于当前时间的本季度信息========结束月==="+sesson.getEndMonth());
//		System.out.println("===============获得相对于当前时间的本季度信息========第一天日期===="+sesson.getCurSessFirstDay().getTime());
//		System.out.println("===============获得相对于当前时间的本季度信息========最后一天日期==="+sesson.getCurSessLastDay().getTime());
		System.out.println("===============获得相对于当前时间的本季度信息========第一天日期===="+DateUtil.dateToString(sesson.getCurSessFirstDay().getTime(), "yyyy-MM-dd hh:mm:ss")  );
 		System.out.println("===============获得相对于当前时间的本季度信息========最后一天日期==="+DateUtil.dateToString(sesson.getCurSessLastDay().getTime(), "yyyy-MM-dd hh:mm:ss")  );
		
	}
	
	
	
	
	
	
	  /*获得当前日期与本周日相差的天数 */  
    private  int getMondayPlus(Calendar cd) {  
        // 按星期一为第一天
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1  
        switch(dayOfWeek)
        {
        	case 0:return -6; 
        	default :return 1 - dayOfWeek; 
        }
    }  
    
    //获得本周一的日期 
    public  String getMondayOFWeek(Calendar cd,String formatStr) {  
        int mondayPlus = this.getMondayPlus(cd);  
        System.out.println("===============getMondayOFWeek获得所指日期与所在周的第一天相差的天数============"+mondayPlus);
        cd.add(GregorianCalendar.DATE, mondayPlus);  
        Date date = cd.getTime();  
        return DateUtil.dateToString(date, formatStr);  
    } 
    
    /** 
     * TODO获得本周任意一天的日期
     * @param week 星期(1....7)
     * @param cd 当前日期
     * @param formatStr 格式化字符串
     * @return
     * 2013-2-18
     *
     */
    public  String getDateInCurrentweek(int week,Calendar cd,String formatStr) {  
    	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
    	int offSet=0;
    	if(dayOfWeek==0)
    		offSet=week-7;
    	else
    		offSet=week-dayOfWeek;
    	
        System.out.println("===============getDateInCurrentweek获得所指日期与所在周的第一天相差的天数============"+offSet);
        cd.add(GregorianCalendar.DATE, offSet);  
        Date date = cd.getTime();  
        return DateUtil.dateToString(date, formatStr);  
    } 
    
    /** 
     * TODO获得相对于当前时间任意周任意一天的日期
     * @param week_n 上N周(-n),下N周(n)
     * @param week 星期(1....7)
     * @param cd 当前日期
     * @param formatStr 格式化字符串
     * @return
     * 2013-2-19
     *
     */
    public  String getDateInAnyweek(int week_n, int week,Calendar cd,String formatStr) {  
    	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
    	int offSet=0;
    	if(dayOfWeek==0)
    		offSet=week-7+week_n*7;
    	else
    		offSet=week-dayOfWeek+week_n*7;
    	
        System.out.println("===============getDateInCurrentweek获得所指日期与所在周的第一天相差的天数============"+offSet);
        cd.add(GregorianCalendar.DATE, offSet);  
        Date date = cd.getTime();  
        return DateUtil.dateToString(date, formatStr);  
    } 
    
    /** 
     * TODO获得相对于当前时间任意月第一天的日期
     * @param moth_n 上N月(-n) 下N月(n)
     * @param cd 当前时间
     * @param formatStr 时间格式化字符串
     * @return
     * 2013-2-19
     *
     */
    public  String getFirstDayInAnymonth(int moth_n,Calendar cd,String formatStr) {  
    	cd.set(Calendar.DATE, 1);//当月第一天
    	cd.add(Calendar.MONTH, moth_n);
    	Date date = cd.getTime();  
        return DateUtil.dateToString(date, formatStr);  
    }
    
    /** 
     * TODO获得相对于当前时间任意月最后一天的日期
     * @param moth_n 上N月(-n) 下N月(n)
     * @param cd 当前时间
     * @param formatStr 时间格式化字符串
     * @return
     * 2013-2-19
     *
     */
    public  String getLastDayInAnymonth(int moth_n,Calendar cd,String formatStr) {  
    	cd.set(Calendar.DATE, 1);//当月第一天
    	cd.add(Calendar.MONTH, moth_n+1);
    	cd.add(Calendar.DATE, -1);
    	Date date = cd.getTime();  
        return DateUtil.dateToString(date, formatStr);  
    } 
    
    /** 
     * TODO获得当前季度信息
     * @param cd
     * @return
     * 2013-2-20
     *
     */
    public Sesson getCurrentSessonValue(Calendar cd)
    {
    	Sesson sesson=new Sesson();
    	int sessonTable[][]={{1,2,3},{4,5,6},{7,8,9,},{10,11,12}};//季度表
    	int currentSesson=1;
    	int startMonth=1;//季度开始月份
    	int endMonth=1;//季度结束月份
    	int currentMonth=cd.get(Calendar.MONTH)+1;
    	
    	if(currentMonth>=1 && currentMonth<=3 )
    	{
    		currentSesson=1;
    	}
    	
    	if(currentMonth>=4 && currentMonth<=6 )
    	{
    		currentSesson=2;
    	}
    	
    	if(currentMonth>=7 && currentMonth<=9 )
    	{
    		currentSesson=3;
    	}
    	
    	if(currentMonth>=10 && currentMonth<=12 )
    	{
    		currentSesson=4;
    	}
    	
    	sesson.setCurrentSesson(currentSesson);
    	startMonth=sessonTable[currentSesson-1][0];
    	endMonth=sessonTable[currentSesson-1][2];
    	System.out.println("===========开始月========"+startMonth);
    	System.out.println("===========结束月========"+endMonth);
    	sesson.setStartMonth(startMonth);
    	sesson.setEndMonth(endMonth);
    	sesson.setSecondMonth(endMonth-1);
    	
    	//本季度第一天
    	Calendar _cd1=(Calendar)cd.clone();
    	_cd1.set(Calendar.DATE,1);
    	_cd1.set(Calendar.MONTH,startMonth-1);
    	sesson.setCurSessFirstDay(_cd1);
    	
    	//本季度最后一天
    	Calendar _cd2=(Calendar)cd.clone();
    	_cd2.set(Calendar.DATE,1);
    	_cd2.set(Calendar.MONTH,endMonth);
    	_cd2.add(Calendar.DATE,-1);
    	sesson.setCurSessLastDay(_cd2);
    	return sesson;
    	
    }
    /**月份差
     * 年份差=较大时间的年-较小时间的年 
	if（较大时间的月>较小时间的月）{ 
	月份差=年份差*12+（较大时间的月-较小时间的月） 
	}else if（较大时间的月<较小时间的月）{ 
	月份差=（年份差-1）*12+（12-较小时间的月+较大时间的月） 
	}else{ 
	月份差=年份差*12 
	}
     * */	
	public static int intervalMonth(Calendar bigCal,Calendar smallCal){
		int smallCalMonth=smallCal.get(Calendar.MONTH);
		int bigCalMonth=bigCal.get(Calendar.MONTH);
		int intetvalYear=bigCal.get(Calendar.YEAR)-smallCal.get(Calendar.YEAR);
		int intetvalMonth=0;
		if(bigCalMonth>smallCalMonth){
			intetvalMonth=intetvalYear*12+(bigCalMonth-smallCalMonth);
		}else if(bigCalMonth<smallCalMonth){
			intetvalMonth=(intetvalYear-1)*12+(12-smallCalMonth+bigCalMonth);
		}else {
			intetvalMonth=intetvalYear*12;
		}
		return intetvalMonth;
	}
	
	/**天数差:当天为0
	 * @throws ParseException */
public static int intervalDays(Date bigDate,Date smallDate) {
		Date bDate =smallDate;
		Date eDate = bigDate;
		Calendar d1 = new GregorianCalendar();
		d1.setTime(bDate);
		d1.set(Calendar.HOUR, 0);
		d1.set(Calendar.MINUTE, 0);
		d1.set(Calendar.SECOND, 0);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(eDate);
		d2.set(Calendar.HOUR, 0);
		d2.set(Calendar.MINUTE, 0);
		d2.set(Calendar.SECOND, 0);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2){
			d1 = (Calendar) d1.clone();
			do{
					days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
					d1.add(Calendar.YEAR, 1);
			}    while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
}
	
	public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar){ 
	    int day = d1 - d2; 
	    int betweenYears = y1 - y2; 
	    List<Integer> d366 = new ArrayList<Integer>(); 

	    if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366){ 
	        System.out.println(calendar.getActualMaximum (Calendar.DAY_OF_YEAR)); 
	        day += 1; 
	    } 

	    for (int i = 0; i < betweenYears; i++) { 
	        // 当年 + 1 设置下一年中有多少天   
	        calendar.set(Calendar.YEAR, (calendar.get (Calendar.YEAR)) + 1); 
	        maxDays = calendar.getActualMaximum (Calendar.DAY_OF_YEAR); 
	        // 第一个 366 天不用 + 1 将所有366记录，先不进行 加入然后再少加一个   
	        if(maxDays != 366){ 
	            day += maxDays; 
	        }else{ 
	            d366.add(maxDays); 
	        } 
	        // 如果最后一个 maxDays 等于366 day - 1   
	        if(i == betweenYears-1 && betweenYears > 1 && maxDays == 366){ 
	            day -= 1; 
	        } 
	    } 

	    for(int i = 0; i < d366.size(); i++){ 
	        // 一个或一个以上的366天   
	        if(d366.size() >= 1){ 
	            day += d366.get(i); 
	        }  
	    }   
	    return day; 
	} 
	
	@Test
	public void addMonth(){
		Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2015);
			cal.set(Calendar.MONTH, 11);
			cal.set(Calendar.DAY_OF_MONTH, 31);
			cal.set(Calendar.HOUR_OF_DAY, 9);
			cal.set(Calendar.MINUTE, 52);
			cal.set(Calendar.SECOND, 13);
			
			Calendar cal2 =(Calendar) cal.clone();
			cal2.add(Calendar.MONTH,2);
			logger.info("cal==========="+DateUtil.dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
			logger.info("cal2==========="+DateUtil.dateToString(cal2.getTime(), "yyyy-MM-dd HH:mm:ss"));
	}

}

class Sesson
{
	private int currentSesson;//当前季度
	private int startMonth;//当前季度开始月
	private int secondMonth;//当前季度第二月
	private int endMonth;//当前季度结束月
	private Calendar curSessFirstDay;//当前季度第一天
	private Calendar curSessLastDay;//当前季度最后一天
	
	public Sesson(){};
	
	
	public int getCurrentSesson() {
		return currentSesson;
	}
	public void setCurrentSesson(int currentSesson) {
		this.currentSesson = currentSesson;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	public Calendar getCurSessFirstDay() {
		return curSessFirstDay;
	}
	public void setCurSessFirstDay(Calendar curSessFirstDay) {
		this.curSessFirstDay = curSessFirstDay;
	}
	public Calendar getCurSessLastDay() {
		return curSessLastDay;
	}
	public void setCurSessLastDay(Calendar curSessLastDay) {
		this.curSessLastDay = curSessLastDay;
	}


	public int getSecondMonth() {
		return secondMonth;
	}


	public void setSecondMonth(int secondMonth) {
		this.secondMonth = secondMonth;
	}

}







