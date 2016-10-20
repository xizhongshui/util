package com.ssj.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;

public class FileUtil 
{
	//获取不带 . 的后缀
	public String getFileExtention(String fileName)
	{
		int dot=fileName.lastIndexOf(".");//test.gif
		String ext=fileName.substring(dot+1);
		System.out.println("getFileExtention()//////////////"+ext);
		return ext;//gif
	}
	
	//获取不带后缀的文件名称
	public  String getFileNameNoExtention(String fileName)
	{
		int dot=fileName.lastIndexOf(".");
		String name=fileName.substring(0,dot);
		 System.out.println("getFileNameNoExtention////////////////"+name);
		return name;
	}
	
	//创建新的文件名
	public String getNewFileName(String fileName)
	{
		String newFileName="";
		StringBuffer sb=new StringBuffer();
		
		GregorianCalendar cal=new GregorianCalendar();
		SimpleDateFormat  formatDate=new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate=formatDate.format(cal.getTime());//获取日期
		sb.append(strDate);
		
		Random random=new Random(); 
		for(int i=0;i<4;i++)
		{
			sb.append(String.valueOf(random.nextInt(10)));
		}
		
		sb.append("."+this.getFileExtention(fileName));
		
		newFileName=sb.toString();
		System.out.println("newFileName///////////////////////////"+newFileName);
		return newFileName;
	}
	
//  获取项目主目录路径  E:\\workLuna\\util
	public String  getHomePath() {
		return System.getProperty("user.dir");
	}

	public  static void main(String[]args)
	{
		FileUtil fileUtil=new FileUtil();
	}
}
