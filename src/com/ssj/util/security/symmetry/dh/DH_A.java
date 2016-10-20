package com.ssj.util.security.symmetry.dh;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;

import org.bouncycastle.util.encoders.Base64;

import com.ssj.util.security.SecurityCommonUtil;

public class DH_A {
		private String pukStrDH_B="MIHcMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyANDAAJAEyiNHbUTOfWEYYegnEbSAfMsJXudmI1WHdTkXVTH3QfEe7rlDGjITQMWTgVfBbLgEhIDLtPFJOyHCUGAaZPVjg==";
		private String pukStr="MIHcMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyANDAAJAPsiqLOP6e5WpVOxLwf83SC/LMLbLjsFZENoUIeVBBLvAiCvGTOJiTSIjM7epGc4EtWvaXt54Gb2odOya2QOqPg==";
		private String prkStr="MIHeAgEAMIGUBgkqhkiG9w0BAwEwgYYCQQCMJDAAfQSkW29x4RbZ+n76fI4jxtieCiUOOrwn1uQleakimPASbKlxmiEpCeAkxEosUywZicXuas/Buzbz3noPAkEAiZtH3zLlDNOYhwA27OZ9lShRuZk4+A67wS7fITucfhvffqw+Oit6ncZY+VG5WbbL3k0IouWawHF6L4uxSoalyARCAkAjLVZ+1yhaFp7Wg2YjLFGInVAAP1HWxg+uGD/j9hgEgsnNYPjNecHKGq9He2wsvsghSsQbRUIgJotWd981Zfgl";
		private DHPublicKey  puk=null;
		private DHPrivateKey prk=null;
		private SecretKey key=null;
		public DH_A() {
			try {
				puk=(DHPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStr), DHCoder.Key_Algorithm);
				SecurityCommonUtil.printKeyBase64("DH_A---puk",puk);
			} catch (Exception e) {
				System.out.println("DH_A由base64生成公钥异常");
				e.printStackTrace();
			}
			try {
				prk=(DHPrivateKey)SecurityCommonUtil.initPriKey(Base64.decode(prkStr), DHCoder.Key_Algorithm);
				SecurityCommonUtil.printKeyBase64("DH_A---prk",prk);
			} catch (Exception e) {
				System.out.println("DH_A由base64生成私钥异常");
				e.printStackTrace();
			}
			DHPublicKey basePuk=null;
			try {
				basePuk = (DHPublicKey)SecurityCommonUtil.initPubKey(Base64.decode(pukStrDH_B), DHCoder.Key_Algorithm);
			} catch (Exception e) {
				System.out.println("DH_A由base64生成对方公钥异常");
				e.printStackTrace();
			}
			try {
				key=SecurityCommonUtil.initeSecretKey(DHCoder.Key_Algorithm, DHCoder.Secret_Algorithm, basePuk, prk);
				SecurityCommonUtil.printKeyBase64("DH_A---key",key);
			} catch (InvalidKeyException | NoSuchAlgorithmException e) {
				System.out.println("DH_A生成本地密钥异常");
				e.printStackTrace();
			}
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
