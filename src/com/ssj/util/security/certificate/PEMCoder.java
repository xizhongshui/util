package com.ssj.util.security.certificate;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

/** ******************  类说明  *********************
 * @Description: OpenSSL可直接生成PEM文件,可使用
 * BouncyCastleProvider操作PEM文件提取密钥对,如果PEM文件本身
 * 加密,需要使用PEMDecryptorPrivider对文件解密
 * ************************************************/
public class PEMCoder {
		
	/**获取keyPair
	 * @throws IOException */
	public static KeyPair readkeypKeyPair(String  pemPath,String password) throws IOException{
		FileReader in=new FileReader(new File(pemPath));
		//构建PEMParser解析类
		PEMReader reader = new PEMReader(in,new MyPassFinder(password));
		KeyPair keyPair = (KeyPair) reader.readObject();
		reader.close();
		in.close();
		return keyPair;
	}
	
	
}
class MyPassFinder implements PasswordFinder{
	private String password="";
	
	public MyPassFinder(){
	}
	
	public MyPassFinder(String password){
		this.password=password;
	}
	@Override
	public char[] getPassword() {
		return password.toCharArray();
	}
	
}
