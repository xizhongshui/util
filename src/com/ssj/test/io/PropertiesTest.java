
/**
 * @Project		util
 * @File		PropertiesTest.java
 * @Package		com.ssj.test.io
 * @Version		V1.0
 * @Date		2016年2月21日 下午9:26:57
 * @Author		shenshuangjie
 */
	
package com.ssj.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.Test;


public class PropertiesTest {
	
	public void initFileDatas(String path,Properties pro) throws IOException{
			File file=new File(path);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(file.exists()){
					InputStream in=new FileInputStream(file);
					 pro.load(in);
			}
	}

	@Test
	public void store() throws FileNotFoundException{
		String path="d://dir//java.properties";
		File file=new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		Properties pro=new Properties();
		if(file.exists()){
			InputStream in=new FileInputStream(file);
			try {
				 pro.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		OutputStream out=null;
		try {
				out = new FileOutputStream(file);
				pro.setProperty("1", "哈哈");
				String key="2";
				System.out.println("key:"+key+",value:"+pro.get(key));
				pro.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try { out.close(); } catch (IOException e) { e.printStackTrace(); }
			}
		}
		
	}
}
