package com.ssj.gameUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.regex.*;


public class KeyWords {
	
	private static final ArrayList<String> keyList = new ArrayList<String>();
	
	public static void initKeys(){
		try {
			File file = new File("configs/badwords.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				if(line.length() > 1){
					String tmp = "";
					for(int i = 0;i<line.length();i++){
						if(i != 0 ){
							tmp += ".*";
							
						}
						tmp += String.valueOf(line.charAt(i));						
					}
				}else{
					keyList.add(line);
				}
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
