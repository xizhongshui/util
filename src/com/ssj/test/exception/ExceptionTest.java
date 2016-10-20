package com.ssj.test.exception;

import org.junit.Test;

public class ExceptionTest {
	
	@Test
	public void testReturn(){
		try {
			int k=1/0;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("has exception--------------------");
	}
	
	@org.junit.Test
	public void finalyTest(){
		String flag="1";
		try{
				addException();
				if("1".equals(flag)){
					System.out.println("return 执行=========");
					return;
				}
		}catch(Exception e){
			
		}finally{
			try {
				addException();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("return 后执行finally？");
		}
	}
	
	@org.junit.Test
	public void finalyTarTest(){
		String flag="1";
		try{
				addException();
				if("1".equals(flag)){
					System.out.println("return 执行=========");
					return;
				}
		}catch(Exception e){
			
		}finally{
			finallyTar();
		}
	}
	
	public void addException() throws Exception{
		System.out.println("添加异常=========");
		throw new Exception();
	}
	
	/**finally 打包*/
	public void finallyTar(){
		try {
			addException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("return 后执行finally？");
	}

}
