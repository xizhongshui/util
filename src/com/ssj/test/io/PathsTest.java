package com.ssj.test.io;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class PathsTest {
		
	@Test// 创建Path
	public void createPath(){
		//方法 1
		Path path1=Paths.get("d:/pdf/txt","20150612_VIPCB.txt");
		//方法 2
		FileSystem fs=FileSystems.getDefault();
		Path path2=fs.getPath("d:/pdf/txt","20150612_VIPCB.txt");
		System.out.println(path1.compareTo(path2));//如果该参数等于这个抽象路径名，此方法返回零，负值和大于0的值，如果抽象路径名的字典顺序分别小于和大于参数。
		
		//方法 3
		File file=new File("d:/pdf/txt");
		Path path3=file.toPath();
	}
	@Test//path 属性
	public void atrrsPath(){
		Path path1=Paths.get("d:/pdf/txt/20150612_VIPCB.txt");
		System.out.println("getNameCount===="+path1.getNameCount());//不包含root即  d:\
		System.out.println("getFileName===="+path1.getFileName().toString());
		System.out.println("getName===="+path1.getName(0).toString());//pdf
		System.out.println("getParent===="+path1.getParent().toString());//d:\pdf\txt
		System.out.println("getRoot===="+path1.getRoot().toString());//d:\
	}
	@Test//连接路径
	public void joinPath(){
		Path path1=Paths.get("d:/pdf/txt");
		System.out.println("path1========"+path1.toString());
		/*resolve方法的作用相当于把当前路径当成父目录，而把参数中的路径当成子目录或是其中的文件，
		进行解析之后得到一个新路径*/
		Path path2=path1.resolve("20150612_VIPCB.txt");
		System.out.println("path2========"+path2.toString());//d:\pdf\txt\20150612_VIPCB.txt
		
//		resolveSibling把当前路径的父目录当成解析时的父目录
		Path path3=path1.resolveSibling("20150612_VIPCB.txt");
		System.out.println("path3========"+path3.toString());//d:\pdf\20150612_VIPCB.txt
		
//		relativize前路径相对于参数中给出的路径的相对路径
		Path path4=path3.relativize(path1);
		System.out.println("path4========"+path4.toString());
		
		//前闭后开区间
		Path path5=path2.subpath(0, 1);
		System.out.println("path5========"+path5.toString());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
