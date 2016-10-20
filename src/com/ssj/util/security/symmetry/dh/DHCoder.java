package com.ssj.util.security.symmetry.dh;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

import com.ssj.util.security.SecurityCommonUtil;

/** ******************  类说明  *********************
 * @Description: DH算法
 * 1、第一个密钥协商算法,仅用于密钥交换,没有加密和解密算法
 * 2、基于有限域上的离散对数难题
 * ************************************************/
public class DHCoder { 
	public static final String Key_Algorithm="DH";
	public static final String Secret_Algorithm="AES";
	//密钥长度64的倍数  512---1024
	public static final int KEY_SIZE = 512;
	
	@Before
	public void init(){
		Security.addProvider(new BouncyCastleProvider());
	}
	//==========================Test======================
	@Test
	public void test() throws UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		DH_A dha=new DH_A();
		DH_B dhb=new DH_B();
		String dhaMessage="this is dha 发送给dhb的消息";
		byte[] encryptData=SecurityCommonUtil.encryptByKey(dhaMessage.getBytes(), dha.getKey());
		byte[] decryptData=SecurityCommonUtil.decryptByKey(encryptData, dhb.getKey());
		System.out.println(new String(decryptData));
	}
	
}
