package com.ssj.test.io;

import java.io.IOException;

import org.junit.Test;

public class FileTest {
	//================================jdk6.0====================================
	@Test //文件过滤
	public void filterFile()
	{
		FileCtrl ft=new FileCtrl();
		ft.showFileName(1,"d:/pdf/txt",new MyFileFilter("^20150612\\w*\\.txt$"));
	}
	
	@Test //读行测试
	public void rwFileByLine(){
		IOCtrl ic=new IOCtrl();
		ic.rwFileByLine("d:/pdf/txt/20150612_VIPCB.txt", "d:/pdf/txt/20150612_VIPCB2.txt");
	}
	
	//================================jdk7.0====================================
	//===========目录流测试============
	@Test//DirectoryStream目录流测试
	public void levelOne(){
		DirectoryCtrl dc=new DirectoryCtrl();
		try {
			dc.levelOne("d:/pdf/txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test//FileVisitor目录流测试
	public void levelMore(){
		DirectoryCtrl dc=new DirectoryCtrl();
		try {
			dc.levelMore("d:/pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test//字节缓冲区IO
	public void rwByteBuffer(){
			IOCtrl io=new IOCtrl();
			io.rwByteBuffer("d:/pdf/txt/20150612_VIPCB.txt","d:/pdf/txt/20150612_VIPCB1.txt" );
	}
	
	@Test//文件通道IO
	public void rwChannel(){
		FileCtrl io=new FileCtrl();
		io.rwChannel("d:/pdf/txt/20150612_VIPCB.txt","d:/pdf/txt/20150612_VIPCB2.txt" );
	}

}
