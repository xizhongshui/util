package com.ssj.gameUtil;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Zlib {
	private static final Logger logger= LoggerFactory.getLogger(Zlib.class);

	private static int cachesize = 1024;
	private static Inflater decompresser = new Inflater();
	private static Deflater compresser = new Deflater();

	public  synchronized static byte[] compressBytes(byte input[]) {
		compresser.reset();
		compresser.setInput(input);
		compresser.finish();
		byte output[] = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		try {
			byte[] buf = new byte[cachesize];
			int got;
			while (!compresser.finished()) {
				got = compresser.deflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	public synchronized static byte[] decompressBytes(byte input[]) {
		byte output[] = new byte[0];
		ByteArrayOutputStream o=null;
		try {
		decompresser.reset();
		decompresser.setInput(input);
		o = new ByteArrayOutputStream(input.length);
		
			byte[] buf = new byte[cachesize];
			int got;
			while (!decompresser.finished()) {
				got = decompresser.inflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} catch (DataFormatException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(o != null){
					o.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	@Test
	public void compress(){
		String src="asdfeghigkllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll" +
				"=========================================================================================================" +
				"======================================================================================================"+
		"=========================================================================================================" +
				"=========================================================================================================" +
				"=========================================================================================================" +
				"=========================================================================================================" +
				"=========================================================================================================" ;
		StringBuffer sb=new StringBuffer();

//		for(int i=0;i<10;i++){
//			sb.append(src) ;
//		}
//		src=sb.toString();

		logger.info("### src.length : "+src.length());

		byte[]  src_byte=src.getBytes();
		logger.info("### src_byte size : "+src_byte.length);

		byte[]  com_byte=Zlib.compressBytes(src_byte);
		logger.info("### com_byte size : "+com_byte.length);

		String base64_encode=new String(Base64.encode(com_byte));
		logger.info("### base64_encode : "+base64_encode+", size:"+base64_encode.length());

		//解压
		byte[] decom_byte=Zlib.decompressBytes(Base64.decode(base64_encode));
		logger.info("### decom_byte size : "+decom_byte.length);

		String decom_src=new String(decom_byte);
		logger.info("### decom_src : "+decom_src+", size:"+decom_src.length());




	}
	
	
}
