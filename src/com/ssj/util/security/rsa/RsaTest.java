package com.ssj.util.security.rsa;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.junit.Test;

import com.ssj.util.security.SecurityCommonUtil;

public class RsaTest {

	@Test
	 public void test(){
		  String src="hello";
		  RSAUtil.initKeyPair();
		  try {
			  byte[] encryptByte=RSAUtil.encrypt(src.getBytes("utf-8"));
			 String encryptBase64= SecurityCommonUtil. byte2Base64Str(encryptByte);
			 System.out.println("encryptBase64=="+encryptBase64);
			  byte[] decryptByte=RSAUtil.decrypt(encryptByte);
			  System.out.println("解密==="+new String(decryptByte));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加密失败================");
		}
	 }
	
	@Test
	 public void test2(){
		  String src="hello";
		  RSAUtil.initKeyPair();
		  try {
			  byte[] encryptByte=RSAUtil.encrypt2(src.getBytes("utf-8"));
			 String encryptBase64= SecurityCommonUtil. byte2Base64Str(encryptByte);
			 System.out.println("encryptBase64=="+encryptBase64);
			  byte[] decryptByte=RSAUtil.decrypt2(encryptByte);
			  System.out.println("解密==="+new String(decryptByte));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加密失败================");
		}
	 }
}
