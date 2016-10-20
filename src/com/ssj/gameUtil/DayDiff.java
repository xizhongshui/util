package com.ssj.gameUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Tatha 求两个日期相差的天数,date2必须大于date1 网上抄来，先用着
 */
public class DayDiff {

	private String date1 = null;
	private String date2 = null;

	// 非闰年每个月天数
	private int[] month = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 0元素未用
	// 闰年每个月天数
	private int[] rmonth = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 0元素未用

	public DayDiff(String date1, String date2) {
		this.date1 = date1;
		this.date2 = date2;
	}

	public int getDaysBetween() {
		int total = 0; // 两个日期相差的天数
		int count = 0; // 计数器

		int y1 = Integer.valueOf(date1.substring(0, 4));
		int y2 = Integer.valueOf(date2.substring(0, 4));

		int m1 = Integer.valueOf(date1.substring(4, 6));
		int m2 = Integer.valueOf(date2.substring(4, 6));

		int d1 = Integer.valueOf(date1.substring(6, 8));
		int d2 = Integer.valueOf(date2.substring(6, 8));

		// 年数相差的日期
		for (count = y1; count < y2; count++) {
			if (isRunNian(count)) {
				total += 366;
			} else {
				total += 365;
			}
		}

		// date1 的当年天数
		int sum1 = 0;
		if (isRunNian(y1)) {
			for (count = 1; count < m1; count++) {
				sum1 += rmonth[count];
			}
		} else {
			for (count = 1; count < m1; count++) {
				sum1 += month[count];
			}
		}
		sum1 += d1; // 加上当月天数

		// date2 的当年天数
		int sum2 = 0;
		if (isRunNian(y1)) {
			for (count = 1; count < m2; count++) {
				sum2 += rmonth[count];
			}
		} else {
			for (count = 1; count < m2; count++) {
				sum2 += month[count];
			}
		}
		sum2 += d2; // 加上当月天数

		total = total - sum1 + sum2;

		return total;
	}

	// 判断是否为闰年
	private boolean isRunNian(int year) {
		if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) {
		
	}
	
	
	public static int getDayDiff(long oldTime,long newTime){
		int result = 0;
		
		if(oldTime > newTime){
			long tempTime = oldTime;
			oldTime = newTime;
			newTime = tempTime;
		}
		
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		Date newDay = new Date(newTime);
		Date oldDay = new Date(oldTime);
		
		DayDiff dd = new DayDiff(df.format(oldDay), df.format(newDay));

		result = dd.getDaysBetween();
		
		return result;
	}
}
