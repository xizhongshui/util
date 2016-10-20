package com.ssj.util.security.certificate;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class CertificateTest {

		private String password="123456";
		private String alias="ssj";
		private String certificatePath="e:\\ssj.cer";
		private String keyStorePath="e:\\ssj.keystore";
		
		//公钥加密---私钥解密
		@Test
		public void pukEncryptPrkDescryptTest() throws UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
			String input="公钥加密私钥解密--->pukEncryptPrkDescrypt";
			byte[]encryptData=CertificateCoder.encryptByPublicKey(input.getBytes(), certificatePath);
			byte[]descryptData=CertificateCoder.decryptByPrivateKey(encryptData, keyStorePath, alias, password);
			System.out.println("【解码】"+new String(descryptData));
		}
		
		//签名验证
		@Test
		public void signVerifyTest() throws UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, SignatureException, IOException{
			String input="签名验证--->signVerifyTest";
			byte[]sign =CertificateCoder.sign(input.getBytes(), keyStorePath, alias, password);
			String input1="签名验证--->signVerifyTest1";
			boolean result=CertificateCoder.verify(input1.getBytes(), sign, keyStorePath, alias, password);
			System.out.println("【签名验证结果】"+result);
		}
}
