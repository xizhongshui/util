package com.ssj.test.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

public class DirectoryCtrl {
	
	/*DirectoryStream目录流接口,支持过滤,不支持层级 */
	public void levelOne(String pathStr) throws IOException{
		Path pathSrc=Paths.get(pathStr);
		if(Files.isDirectory(pathSrc)){//判断是否为目录
			DirectoryStream<Path> ds=Files.newDirectoryStream(pathSrc,"20150612*.txt");//简单过滤机制
			Iterator<Path> iter=ds.iterator();
			while(iter.hasNext()){
				Path path=iter.next();
				System.out.println(path.toString());
			}
		}
	}
	/*FileVisitor层级遍历*/
	public void levelMore(String pathStr) throws IOException{
		MyVisitor mv=new  MyVisitor ();
		Path path=Files.walkFileTree(Paths.get(pathStr),mv );
		System.out.println(path.toString());
	}
	
	class MyVisitor extends SimpleFileVisitor<Path>{

		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			return super.preVisitDirectory(dir, attrs);
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			System.out.println("file==="+file.toString());
			return super.visitFile(file, attrs);
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc)
				throws IOException {
			return super.visitFileFailed(file, exc);
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc)
				throws IOException {
			System.out.println("dir==="+dir.toString());
			return super.postVisitDirectory(dir, exc);
		}
		
	}
}
