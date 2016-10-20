package com.ssj.util.security.symmetry.dh;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import com.ssj.util.security.SecurityCommonUtil;

public class DH_B {
	private String pukStrDH_A="MIHcMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyANDAAJAPsiqLOP6e5WpVOxLwf83SC/LMLbLjsFZENoUIeVBBLvAiCvGTOJiTSIjM7epGc4EtWvaXt54Gb2odOya2QOqPg==";
	private String pukStr="MIHcMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyANDAAJAEyiNHbUTOfWEYYegnEbSAfMsJXudmI1WHdTkXVTH3QfEe7rlDGjITQMWTgVfBbLgEhIDLtPFJOyHCUGAaZPVjg==";
	private String prkStr="MIHeAgEAMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyARCAkA/M+KXLZUdTTvQnXws8njckq1pS32OlAwwWCYHlxM2jBd7pZW0wltOrWHcO+W7iG5AEHDI9jX7DqVldiQHfXBI";
	private DHPublicKey  puk=null;
	private DHPrivateKey prk=null;
	private SecretKey key=null;
	public DH_B() {
		try {
			puk=(DHPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStr), DHCoder.Key_Algorithm);
			SecurityCommonUtil.printKeyBase64("DH_B---puk",puk);
		} catch (Exception e) {
			System.out.println("DH_B由base64生成公钥异常");
			e.printStackTrace();
		}
		try {
			prk=(DHPrivateKey)SecurityCommonUtil.initPriKey(Base64.decode(prkStr), DHCoder.Key_Algorithm);
			SecurityCommonUtil.printKeyBase64("DH_B---prk",prk);
		} catch (Exception e) {
			System.out.println("DH_B由base64生成私钥异常");
			e.printStackTrace();
		}
		DHPublicKey basePuk=null;
		try {
			basePuk = (DHPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStrDH_A), DHCoder.Key_Algorithm);
		} catch (Exception e) {
			System.out.println("DH_B由base64生成对方公钥异常");
			e.printStackTrace();
		}
		try {
			key=SecurityCommonUtil.initeSecretKey(DHCoder.Key_Algorithm, DHCoder.Secret_Algorithm, basePuk, prk);
			SecurityCommonUtil.printKeyBase64("DH_B---key",key);
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			System.out.println("DH_B生成本地密钥异常");
			e.printStackTrace();
		}
	}
	
	@Test
	public void genKeyPair() throws Exception{
		DHPublicKey basePuk=(DHPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStrDH_A), DHCoder.Key_Algorithm);
		DHParameterSpec paramSpec=basePuk.getParams();
		SecurityCommonUtil.generateKeyPair(DHCoder.Key_Algorithm, new BouncyCastleProvider(), paramSpec);
	}

	public DHPublicKey getPuk() {
		return puk;
	}

	public void setPuk(DHPublicKey puk) {
		this.puk = puk;
	}

	public DHPrivateKey getPrk() {
		return prk;
	}

	public void setPrk(DHPrivateKey prk) {
		this.prk = prk;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}
	
	
	
}
