package com.ssj.test.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class FileCtrl 
{
	//创建目录
	public void createDirection(String path) throws Exception
	{
		File dir=new File(path);
		if(!dir.exists())
		{
			if(!dir.mkdirs())
				throw new Exception("目录不存在，创建失败！"); 
			else
				System.out.println("createDirection()///////////////目录创建成功//////////////");
		}
	}
	
	//创建文件
	public void createFile(String path) throws Exception
	{
		File file=new File(path);
		if(!file.getParentFile().exists())
		{
			if(!file.getParentFile().mkdirs())
				throw new Exception("目录不存在，创建失败！"); 
			else
				System.out.println("createDirection()///////////////目录创建成功//////////////");
		}
		if(!file.exists())
		{
			if(!file.createNewFile())
				throw new Exception("文件创建失败！"); 
			else
				System.out.println("createDirection()///////////////文件创建成功//////////////");
		}
	}
	
	//递归删除文件或目录
	public boolean del(String path)
	{
		File file=new File(path);
		if(file.exists())
		{
			if(file.isFile())
			{
				file.delete();
			}
			if(file.isDirectory())
			{
				for(File fchildren:file.listFiles())
				{
					del(fchildren.getPath());
				}
				file.delete();
			}
			return true;
		}
		else
		return false;
	}
	
	//递归显示文件名
	public void showFileName(int i,String path)
	{
		File file=new File(path);
		if(file.exists())
		{
			for(int k=0;k<i;k++)
				System.out.print("***");
			System.out.println(file.getName());
			if(file.isDirectory())
			{
				i++;
				for(File fchildren:file.listFiles())
				{
					showFileName(i,fchildren.getPath());
				}
			}
		}
	}
	
	public void showFileName(int i,String path,FileFilter filer)
	{
		File file=new File(path);
		if(file.exists())
		{
			for(int k=0;k<i;k++)
				System.out.print("***");
			System.out.println(file.getName());
			if(file.isDirectory())
			{
				i++;
				for(File fchildren:file.listFiles(filer))
				{
					showFileName(i,fchildren.getPath(),filer);
				}
			}
		}
	}
	
	//-----------------------------FileUtils-----------------------------------------
	//读取、写入文件
	public List<String> rFileWithFUtils(String path,String targetPath,String encoding)
	{
		File file=new File(path);
		List<String> lines=null;
		StringBuffer sb=new StringBuffer();
		try {
			lines=FileUtils.readLines(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String line:lines)
		{
			sb.append(line+"\r\n");
			System.out.println(line);
			
		}
		
		File targetFile=new File(targetPath);
		if(!targetFile.exists())
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				FileUtils.writeLines(targetFile, lines);
				FileUtils.writeStringToFile(file, sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lines;
	}
	
	/**文件通道*/
	public void rwChannel(String pathSrc,String pathDst){
		try(	FileChannel srcChannel=FileChannel.open(Paths.get(pathSrc), StandardOpenOption.READ);
				FileChannel dstChannel=FileChannel.open(Paths.get(pathDst), StandardOpenOption.CREATE,StandardOpenOption.WRITE)
		) {
			srcChannel.transferTo(0, srcChannel.size(), dstChannel);
//			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public static void  main(String[]args)
	{
		FileCtrl ft=new FileCtrl();
		
		/* //创建目录
		  try {
			ft.createDirection("d:/fileTest");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("createDirection()///////////////目录创建失败//////////////");
		}*/
		
		
		//创建文件
	/*	 try {
			ft.createFile("d:/fileTest/F.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("createFile()///////////////文件创建失败//////////////");
		}*/
		
		/*
		 * //File 属性
		System.out.println("separator 操作系统分隔符="+File.separator);//内容大小
		File file=new File("d:/fileTest/F.txt");
		System.out.println("getAbsolutePath()///////////////"+file.getAbsolutePath()); //d:\fileTest\F.txt
		System.out.println("getPath()/////////////////////"+file.getPath());//d:\fileTest\F.txt
		System.out.println("getParent()/////////////////////"+file.getParent());//d:\fileTest
		System.out.println("getName()/////////////////////"+file.getName());//F.txt
		System.out.println("isDirectory()/////////////////////"+file.isDirectory());
		System.out.println("isFile()/////////////////////"+file.isFile());
		System.out.println("length()/////////////////////"+file.length());//内容大小
		 */	
		//ft.del("d:/fileTest");
		
		//ft.showFileName(1,"d:/fileTest");
		
		ft.rFileWithFUtils("d:/fileTest/F.txt","d:/fileTest/F2.txt","utf-8");
		
	}
	

	
}

class MyFileFilter implements FileFilter
{
	private String regex;
	
	public MyFileFilter(){}
	public MyFileFilter(String regex)
	{this.regex=regex;}
	
	public boolean accept(File file) {
		String fileName=file.getName();
		
		Boolean result=Pattern.matches(regex, fileName);
		return result;
		
		/*Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(fileName);
		return matcher.matches();*/
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}













