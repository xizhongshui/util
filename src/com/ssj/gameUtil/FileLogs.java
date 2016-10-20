package com.ssj.gameUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileLogs {
	public static void writeInterrupt(String msg){
		try{
			FileWriter fw = new FileWriter("interrupt.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(msg);
			bw.newLine();
			bw.flush();
			fw.close();
			bw.close();
			fw = null;
			bw = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
