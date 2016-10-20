package com.ssj.util.security.symmetry.ecdh;

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

/*	ECDH算法密钥长度共有3种：112、256和571位。
	3)ECDH算法的密钥实际上也是EC算法的密钥，初始化密钥对生成器时，使用“EC”代替“ECDH”也可以。ECDH算法对交
	换的密钥算法支持还不能像DH算法那样全面，作者尝试使用AES、DES和DESede算法构建本地密钥均告失败，
	但Blowfish、RC2和RC4都可以正常使用
 * */
public class ECDHCoder {
	public static final String Key_Algorithm="ECDH";
	public static final String Secret_Algorithm="RC4";
	//密钥长度   112---571
	public static final int KEY_SIZE = 256;
	
	@Before
	public void init(){
		Security.addProvider(new BouncyCastleProvider());
	}
	
	@Test
	public void test() throws UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		ECDH_A dha=new ECDH_A();
		ECDH_B dhb=new ECDH_B();
		String dhaMessage="this is dha 发送给dhb的消息";
		byte[] encryptData=SecurityCommonUtil.encryptByKey(dhaMessage.getBytes(), dha.getKey());
		byte[] decryptData=SecurityCommonUtil.decryptByKey(encryptData, dhb.getKey());
		System.out.println(new String(decryptData));
	}
}

