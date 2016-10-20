package com.ssj.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	/**
	 * 32位MD5摘要算法
	 */
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
			String md5Str=MD5.md5s("1232uu*#89JK");
			System.out.println("md5Str length=="+md5Str.length());
			System.out.println("md5Str =="+md5Str);
			/*md5Str length==32
			md5Str ==db26d43072f91cc6c4dd39b60719468b*/
	}
}
