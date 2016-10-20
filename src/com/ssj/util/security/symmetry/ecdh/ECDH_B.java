package com.ssj.util.security.symmetry.ecdh;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import com.ssj.util.security.SecurityCommonUtil;

public class ECDH_B {
	private String pukStrDH_A="MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEdNh07XVJTNg5qovE4yL9ZaXntNT6soJDrcsqyLJoNPWXuPnMYaIwVsPAXvuMpJrnq3N+4QHqnd4L0QYKuP3OYA==";
	private String pukStr="MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEpCgCteCWlglhZwE3Z7fI5WtiVKHY5zicjK7TXBvQMuQCZ8UHGHTffMy02cu82laeeSusR2Lje68zVEZGCVrO6w==";
	private String prkStr="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgMfdOCOe11qqeLTlGTwmG4eCIVqXufXvPIsZXYatpNbmgCgYIKoZIzj0DAQehRANCAASkKAK14JaWCWFnATdnt8jla2JUodjnOJyMrtNcG9Ay5AJnxQcYdN98zLTZy7zaVp55K6xHYuN7rzNURkYJWs7r";
	private ECPublicKey  puk=null;
	private ECPrivateKey prk=null;
	private SecretKey key=null;
	
	
	public ECDH_B() {
		try {
			puk=(ECPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStr), ECDHCoder.Key_Algorithm);
			SecurityCommonUtil.printKeyBase64("ECDH_B---puk",puk);
		} catch (Exception e) {
			System.out.println("ECDH_B由base64生成公钥异常");
			e.printStackTrace();
		}
		try {
			prk=(ECPrivateKey)SecurityCommonUtil.initPriKey(Base64.decode(prkStr), ECDHCoder.Key_Algorithm);
			SecurityCommonUtil.printKeyBase64("ECDH_B---prk",prk);
		} catch (Exception e) {
			System.out.println("ECDH_B由base64生成私钥异常");
			e.printStackTrace();
		}
		ECPublicKey basePuk=null;
		try {
			basePuk = (ECPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStrDH_A), ECDHCoder.Key_Algorithm);
		} catch (Exception e) {
			System.out.println("ECDH_B由base64生成对方公钥异常");
			e.printStackTrace();
		}
		try {
			key=SecurityCommonUtil.initeSecretKey(ECDHCoder.Key_Algorithm, ECDHCoder.Secret_Algorithm, basePuk, prk);
			SecurityCommonUtil.printKeyBase64("ECDH_B---key",key);
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			System.out.println("ECDH_B生成本地密钥异常");
			e.printStackTrace();
		}
	}
	@Test
	public void createKeyPair() throws Exception{
		SecurityCommonUtil.generateKeyPair(ECDHCoder.Key_Algorithm, new BouncyCastleProvider(), ECDHCoder.KEY_SIZE);
	}
	public ECPublicKey getPuk() {
		return puk;
	}
	public void setPuk(ECPublicKey puk) {
		this.puk = puk;
	}
	public SecretKey getKey() {
		return key;
	}
	public void setKey(SecretKey key) {
		this.key = key;
	}
	
	
}
