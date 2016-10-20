package com.ssj.util.security.certificate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CertificateCoder {
	
	public static final String Cert_Type="X.509";
	
	/** 加载密钥库 */
	public static KeyStore getKeyStore(String keyStorePath,String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
			//实例化密钥库
			KeyStore ks=KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream in=new FileInputStream(keyStorePath);
			ks.load(in, password.toCharArray());
			in.close();
			return ks;
	}
	
	
	/** 获取证书---由密钥库提取*/
	public static Certificate getCertificate(String keyStorePath,String alias,String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
			//获得密钥库
			KeyStore ks=getKeyStore(keyStorePath, password);
			return ks.getCertificate(alias);
	}
	
	/** 获取证书---由证书文件加载*/
	public static Certificate getCertificate(String certificatePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
			CertificateFactory cf=CertificateFactory.getInstance(Cert_Type);
			FileInputStream in=new FileInputStream(certificatePath);
			Certificate certificate= cf.generateCertificate(in);
			in.close();
			return certificate;
	}
	
	
	
	/** 由keyStore获得私钥*/
	public static PrivateKey getPrivatekKey(String keyStorePath,String alias,String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
			KeyStore ks=getKeyStore(keyStorePath,password);
			return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}
	
	/** 由certificate获得公钥*/
	public static PublicKey getPublicKey(String keyStorePath,String alias,String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		Certificate cf=getCertificate(keyStorePath,alias,password);
		return cf.getPublicKey();
	}
	
	/** 由certificate获得公钥*/
	public static PublicKey getPublicKey(String certificatePath) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		Certificate cf=getCertificate(certificatePath);
		return cf.getPublicKey();
	}
	
	/**签名【公钥签名,私钥验证】
	 * 1由证书获得签名算法
	 * 2用私钥签名
	 * */
	public static byte[] sign(byte[] data,String keyStorePath,String alias,String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, InvalidKeyException, SignatureException{
		X509Certificate cf=(X509Certificate)getCertificate(keyStorePath, alias, password);
		//取得签名算法
		Signature signAlg=Signature.getInstance(cf.getSigAlgName());
		PrivateKey privateKey=getPrivatekKey(keyStorePath, alias, password);
		//初始化签名算法
		signAlg.initSign(privateKey);
		//更新签名数据
		signAlg.update(data);
		//签名数据
		return signAlg.sign();
	}
	/**签名验证
	 * 1由证书获得签名算法
	 * 2用公钥验证
	 * */
	public static boolean verify(byte[] data,byte[] sign,String keyStorePath,String alias,String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InvalidKeyException, SignatureException{
		X509Certificate cf=(X509Certificate)getCertificate(keyStorePath, alias, password);
		//取得签名算法
		Signature signAlg=Signature.getInstance(cf.getSigAlgName());
		//公钥初始化验证
		signAlg.initVerify(cf);
		signAlg.update(data);
		//验证签名
		return signAlg.verify(sign);
	}
	
	
	/**公钥加密*/
	public static byte[] encryptByPublicKey(byte[] data,String certificatePath) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		PublicKey puk=getPublicKey(certificatePath);
		Cipher cipher=Cipher.getInstance(puk.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, puk);
		return cipher.doFinal(data);
	}
	
	/**私钥解密 */
	public static byte[] decryptByPrivateKey(byte[] encryptData,String keyStorePath, String alias, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		PrivateKey prk=getPrivatekKey(keyStorePath, alias, password);
		Cipher cipher=Cipher.getInstance(prk.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, prk);
		return cipher.doFinal(encryptData);
	}
	
}
