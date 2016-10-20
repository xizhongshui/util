package com.ssj.util.security.rsa;

/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

/**
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。
 * 
 */
public class RSAUtil2 {
	private static Logger logger=Logger.getLogger(RSAUtil2.class);
	public static final String Key_Algorithm="RSA";
	public static final String Public_Key="RSAPubKey";
	public static final String Private_Key="RSAPriKey";
	public static final String Modulus="Modulus";//16进制
	public static final String PuKeyE="PuKeyE";//16进制
	/**Modulus 16进制,PuKeyE：加密指数 16进制,RSAPubKey key,RSAPriKey key*/
	public static final HashMap<String,Object> keyMap=new HashMap<String,Object>();
	public static final String Private_KeyStr="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKrIfIin69w23S/"+
				"gHRmXpdQBEA/mS1VAv4LfsSBJb4FOGLXa5WzkEFxRvlXbfmtws4OdTOME6CSrple2a5OB7mWuLu"+
				"MRxBDx1NVybLmmOU+PiaoQcD2W8VE/k1NTB02Erhpn88UDiTy8feOsucIEetvdkNYmMRwvkyHqH"+
				"78G70rTAgMBAAECgYEAo+CkF2HC4tpGntUYN2GcP3YRmqAqs5Dg9E2AIvI0rEhrT73PnxCtXlkWI"+
				"j7v7VsE9wtvJvyjYeTQRqSGT9JDFf+bVknHDMI/OrqoZGA9z+dplRzrpowfpOmXItSvGckrAqEaqp"+
				"5K3/qHc7ydOtdN/gVr1x8VcYG1jrGuCSl59kECQQDUdVUWrjHq4bTBVfXG7Av569wa7+oZPGbY1G1"+
				"jDuZH1Kpux2ZWWdHD0JWV1dLvvlwEkLwuQceHg+a16X/Tf0rhAkEAzcioXgIndzjlqmukGvRXS1fCo"+
				"9/rLpPs/2A2wW3hYXmh0M9pCnk9l7V7cBLNnOJ7jza4nJif3BV/p0QOP19gMwJAFUrNBEDg79E+9EF"+
				"RKGZvrNRecrSomW7cP87KR6sumPRPQFrrfqvxwdYdp66OPUiuoCaBNYGSJZqqfICyNtgDAQJBALAlTI"+
				"l7ypBw8ZZZIVW5RWfGrWNc7pEmKj3G+9fC2KN5XBvDDqTadvB/djPOxJXAAAfszUWuF1hqRqTEynVQj"+
				"KUCQC37h3hRG1vBHThFESW2wZNlsZqztBKSoS1H/juDKiOlWqGn4AAmS5RF2Mx5y4fwXB4dGyo3x5h/Op/e7ubCMwY=";
	public static final String Public_KeyStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqyHyIp+vcNt0v4B0Zl6XUARAP5ktV"+
				"QL+C37EgSW+BThi12uVs5BBcUb5V235rcLODnUzjBOgkq6ZXtmuTge5lri7jEcQQ8dTVcmy5pjlPj4m"+
				"qEHA9lvFRP5NTUwdNhK4aZ/PFA4k8vH3jrLnCBHrb3ZDWJjEcL5Mh6h+/Bu9K0wIDAQAB";
	
	/**
	 * * 生成密钥对 *
	 * 
	 * @return KeyPair *
	 * @throws EncryptException
	 */
	public  static void generateKeyPair() throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Key_Algorithm, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
		    keyPairGen.generateKeyPair();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void initKeyPair(){
		try {
				initPriKey(Base64.decode(Private_KeyStr));
				initPubKey(Base64.decode(Public_KeyStr));
		} catch (Exception e) {
				logger.info("公私钥初始化============================");
				e.printStackTrace();
		}
	}
	
	/**私钥材料生成私钥*/
	private static void initPriKey(byte[] key) throws Exception{
			PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec(key);
			KeyFactory kf=KeyFactory.getInstance(Key_Algorithm);
			RSAPrivateKey priKey=(RSAPrivateKey)kf.generatePrivate(spec);
			keyMap.put(Private_Key, priKey);
	}
	
	/**公钥材料生成私钥*/
	private static void initPubKey(byte[] key) throws Exception{
			X509EncodedKeySpec spec=new X509EncodedKeySpec(key);
			KeyFactory kf=KeyFactory.getInstance(Key_Algorithm);
			RSAPublicKey pubKey=(RSAPublicKey)kf.generatePublic(spec);
			String modulus=pubKey.getModulus().toString(16);
			String puKeyE=pubKey.getPublicExponent().toString(16);
			
			keyMap.put(Modulus, modulus);
			keyMap.put(PuKeyE, puKeyE);
			keyMap.put(Public_Key, pubKey);
			
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

	/**
	 * * 加密 *
	 * 
	 * @param key
	 *            加密的密钥 *
	 * @param data
	 *            待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data) throws Exception {
		try {
			PublicKey pk=(PublicKey)keyMap.get(Public_Key);
			Cipher cipher = Cipher.getInstance(Key_Algorithm, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
					: data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i
							* outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i
							* blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。

				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * * 解密 *
	 * 
	 * @param key
	 *            解密的密钥 *
	 * @param raw
	 *            已经加密的数据 *
	 * @return 解密后的明文 *
	 * @throws Exception
	 */
	public static byte[] decrypt( byte[] raw) throws Exception {
		try {
			RSAPrivateKey pk=(RSAPrivateKey)keyMap.get(Private_Key);
			Cipher cipher = Cipher.getInstance(Key_Algorithm,new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	/**网络传输
	* @Author         : 申双杰
	* @Description: 密文转码
	 * @version      : V1.0                          
	**/
	public static String urlEncode(String passwordEn) throws Exception{
			String result=null;
			byte[] en_result = new BigInteger(passwordEn, 16).toByteArray();
			try {
					byte[] de_result = RSAUtil2.decrypt(en_result);
				/*	result=new String(de_result);
					logger.info("解码结果"+result);*/
					StringBuffer sb = new StringBuffer();
					sb.append(new String(de_result));
					result= sb.reverse().toString();
					result = URLDecoder.decode(result,"UTF-8");//
					logger.info("解码结果"+result);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			return result;
	}
}
