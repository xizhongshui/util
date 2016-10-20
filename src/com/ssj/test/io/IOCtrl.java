package com.ssj.test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOCtrl 
{
	//字节流 文件的读取和写入
	public boolean rwFileByByte(String sourcePath,String newPath)
	{
		File sourceFile=new File(sourcePath);
		File targetFile=new File(newPath);
		InputStream in=null;
		OutputStream out=null;
		if(!sourceFile.exists()||!sourceFile.isFile())
		{
			System.out.println("源文件不存在");
			return false;
		}
		
		if(!sourceFile.getParentFile().exists())
		{
			if(!sourceFile.getParentFile().mkdirs())
				return false;
		}
		if(!sourceFile.exists())
			try {
				sourceFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		try {
			in=new BufferedInputStream(new FileInputStream(sourceFile));
			out=new BufferedOutputStream(new FileOutputStream(targetFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		//文件长度
		int fileSize=(int)sourceFile.length();
		byte[] bt=new byte[1024];
		try {
			int len;
			int xhcs=0;
			while( (len=in.read(bt))!=-1)
			{
				xhcs++;
				System.out.println("xhcs/////////"+xhcs);
				out.write(bt, 0, len);
			}
			in.read(bt);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	//字符流 文件的读取和写入
	public boolean rwFileByChar(String sourcePath,String newPath)
	{
		File sourceFile=new File(sourcePath);
		File targetFile=new File(newPath);
		Reader in=null;
		Writer out=null;
		if(!sourceFile.exists()||!sourceFile.isFile())
		{
			System.out.println("源文件不存在");
			return false;
		}
		
		if(!sourceFile.getParentFile().exists())
		{
			if(!sourceFile.getParentFile().mkdirs())
				return false;
		}
		if(!sourceFile.exists())
			try {
				sourceFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		try {
			in=new InputStreamReader(new FileInputStream(sourceFile));
			out=new OutputStreamWriter(new FileOutputStream(targetFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		//文件长度
		int fileSize=(int)sourceFile.length();
		char[] cha=new char[1];
		try {
			int len=-1;
			int xhcs=0;
			while( (len=in.read(cha))!=-1)
			{
				xhcs++;
				System.out.println("xhcs/////////"+xhcs);
				out.write(cha, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	//行读取
	public boolean rwFileByLine(String sourcePath,String newPath)
	{
		File sourceFile=new File(sourcePath);
		File targetFile=new File(newPath);
		Reader in=null;
		Writer out=null;
		BufferedReader br =null;
		BufferedWriter bw =null;
		if(!sourceFile.exists()||!sourceFile.isFile())
		{
			System.out.println("源文件不存在");
			return false;
		}
		
		if(!sourceFile.getParentFile().exists())
		{
			if(!sourceFile.getParentFile().mkdirs())
				return false;
		}
		if(!sourceFile.exists())
			try {
				sourceFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		try {
			in=new InputStreamReader(new FileInputStream(sourceFile));
			br=new BufferedReader(in);
			out=new OutputStreamWriter(new FileOutputStream(targetFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		//文件长度
		int fileSize=(int)sourceFile.length();
		try {
			String line="";
			bw=new BufferedWriter(out);
			while((line=br.readLine())!=null)
			{
				System.out.println("xhcs/////////"+line);
				bw.write(line+"\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			out.close();
			in.close();
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**字节缓冲区IO*/
	public void rwByteBuffer(String pathSrc,String pathDst){
			try(FileChannel srcChannel=FileChannel.open(Paths.get(pathSrc), StandardOpenOption.READ);
				   FileChannel dstChannel=FileChannel.open(Paths.get(pathDst), StandardOpenOption.CREATE,StandardOpenOption.WRITE)
					) {
					ByteBuffer buff=ByteBuffer.allocate(512*1024);
					while(srcChannel.read(buff)>0 || buff.position()!=0){
						buff.flip();
						dstChannel.write(buff);
						buff.clear();
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
