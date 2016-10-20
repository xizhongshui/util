package com.ssj.util.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.bouncycastle.util.encoders.Base64;

public class SecurityCommonUtil {

	/** 生成密钥对 * */
	public  static KeyPair generateKeyPair(String Key_Algorithm,int KEY_SIZE) throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Key_Algorithm);
			keyPairGen.initialize(KEY_SIZE);
			KeyPair  keyPair=keyPairGen.generateKeyPair();
			SecurityCommonUtil.printKeyPairBase64(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/** 生成密钥对 * */
	public  static KeyPair generateKeyPair(String Key_Algorithm,Provider provider,int KEY_SIZE) throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Key_Algorithm, provider);
			keyPairGen.initialize(KEY_SIZE);
			KeyPair  keyPair=keyPairGen.generateKeyPair();
			SecurityCommonUtil.printKeyPairBase64(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	/** 生成密钥对 * */
	public  static KeyPair generateKeyPair(String Key_Algorithm,Provider provider,int KEY_SIZE,SecureRandom random) throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Key_Algorithm, provider);
			keyPairGen.initialize(KEY_SIZE,random);
			KeyPair  keyPair=keyPairGen.generateKeyPair();
			SecurityCommonUtil.printKeyPairBase64(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	//取得甲方公钥参数材料DHParameterSpec 生成乙方密钥对
	//DHParameterSpec paramSpec=basePuk.getParams();
	/** 生成密钥对 * */
	public  static KeyPair generateKeyPair(String Key_Algorithm,Provider provider,AlgorithmParameterSpec params) throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Key_Algorithm, provider);
			keyPairGen.initialize(params);
			KeyPair  keyPair=keyPairGen.generateKeyPair();
			SecurityCommonUtil.printKeyPairBase64(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static String key2Base64Str(Key key){
		byte[] keyBytes =key.getEncoded();
		byte[] base64Bytes=Base64.encode(keyBytes);
		return new String(base64Bytes);
	}
	
	public static String byte2Base64Str(byte[] bytes){
		byte[] base64Bytes=Base64.encode(bytes);
		return new String(base64Bytes);
	}
	
	public static void printKeyPairBase64(KeyPair  keyPair){
		PublicKey puk=keyPair.getPublic();
		PrivateKey prk=keyPair.getPrivate();
		System.out.println("【puk--->base64】"+SecurityCommonUtil.key2Base64Str(puk));
		System.out.println("【prk--->base64】"+SecurityCommonUtil.key2Base64Str(prk));
	}
	
	public static void printKeyBase64(String des,Key  key){
		System.out.println("【"+des+"--->base64】"+SecurityCommonUtil.key2Base64Str(key));
	}
	
	/**私钥材料生成私钥*/
	public static PrivateKey initPriKey(byte[] key,String KeyAlgorithm) throws Exception{
			PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec(key);
			KeyFactory kf=KeyFactory.getInstance(KeyAlgorithm);
			return kf.generatePrivate(spec);
	}
	
	/**公钥材料生成私钥*/
	public static PublicKey initPubKey(byte[] key,String KeyAlgorithm) throws Exception{
			X509EncodedKeySpec spec=new X509EncodedKeySpec(key);
			KeyFactory kf=KeyFactory.getInstance(KeyAlgorithm);
			return kf.generatePublic(spec);
	}
	
	/**加密*/
	public static byte[] encryptByKey(byte[] data,Key key) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher=Cipher.getInstance(key.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	
	/**解密 */
	public static byte[] decryptByKey(byte[] encryptData,Key key ) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher=Cipher.getInstance(key.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(encryptData);
	}
	
	/**由A公钥和B私钥构建本地对称加密密钥
	 * key_algorithm:密钥交换算法
	 * secret_algorithm:对称加密算法
	 * */
	public static SecretKey initeSecretKey(String key_algorithm,String secret_algorithm,PublicKey pukA,PrivateKey prkB) throws NoSuchAlgorithmException, InvalidKeyException{
		//实例化密钥契约
		KeyAgreement agreement=KeyAgreement.getInstance(key_algorithm);
		agreement.init(prkB);
		agreement.doPhase(pukA, true);
		
		return agreement.generateSecret(secret_algorithm);
	}
	
}
