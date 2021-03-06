package com.ssj.util.security.rsa.test;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
 
import javax.crypto.Cipher;
 
import org.apache.commons.codec.binary.Base64;
 
 
public class RSAUtil {
     
    private static final KeyPair keyPair = initKey();
     
    private static KeyPair initKey(){
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(1024, random);
            return generator.generateKeyPair();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 生成public key
     * @return
     */
    public static String generateBase64PublicKey(){
        RSAPublicKey key = (RSAPublicKey)keyPair.getPublic();
        return new String(Base64.encodeBase64(key.getEncoded()));
    }
     
    /**
     * 解密
     * @param string
     * @return
     */
    public static String decryptBase64(String string) {
        return new String(decrypt(Base64.decodeBase64(string)));
    }
     
    private static byte[] decrypt(byte[] string) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            RSAPrivateKey pbk = (RSAPrivateKey)keyPair.getPrivate();
            cipher.init(Cipher.DECRYPT_MODE, pbk);
            byte[] plainText = cipher.doFinal(string);
            return plainText;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     
    public static void main(String[] args) {
        // 生成public key
        System.out.println(generateBase64PublicKey());
         
        // 解密
        System.out.println(decryptBase64("H0T5TpJbtcTSVdTy/6MSRN1q1k9fPaY+wa7Pg/lfqkDZD5wQDId9PdEIEw1syjpPYxY5dBFunILsEZfz9ZvWClVEeJNg8V/EcI1waUdLLt/5xh4D0hXZRsxI/Y4D7s3spdGxQg81hl9F0j9yMhntDWk1gT3pt4TSkwcwg9UlcHs="));
    }
 
}