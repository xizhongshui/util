package com.ssj.gameUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


	public static String md5s(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static void main(String agrs[]) {
	/*	String s = "sjuhdkjhc_40";
		String s2 = new String( Crypt.RC4("aaaa".getBytes(), s.getBytes()));
		System.out.println(s2);
		System.out.println(new String( Base64.encode(s2.getBytes())));*/
			String md5=MD5.md5s("1232uu*#89JK");
			System.out.println("md5 length=="+md5.length());
			System.out.println("md5 =="+md5);
	}
}
