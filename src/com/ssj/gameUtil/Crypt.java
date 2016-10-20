package com.ssj.gameUtil;



/**
 * 
 * @author Athena
 * 
 *         CR4加密算法的实现
 */

public class Crypt {

	public static byte[] RC4(byte[] b_key, byte[] buf) {
		byte state[] = new byte[256];

		for (int i = 0; i < 256; i++) {
			state[i] = (byte) i;
		}
		byte tmp;
		int x = 0;
		int y = 0;

		int index1 = 0;
		int index2 = 0;
		if (b_key == null || b_key.length == 0) {
			return null;
		}
		for (int i = 0; i < 256; i++) {
			index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
			tmp = state[i];
			state[i] = state[index2];
			state[index2] = tmp;
			index1 = (index1 + 1) % b_key.length;
		}

		int xorIndex;

		if (buf == null) {
			return null;
		}

		byte[] result = new byte[buf.length];

		for (int i = 0; i < buf.length; i++) {
			x = (x + 1) & 0xff;
			y = ((state[x] & 0xff) + y) & 0xff;
			tmp = state[x];
			state[x] = state[y];
			state[y] = tmp;
			xorIndex = ((state[x] & 0xff) + (state[y] & 0xff)) & 0xff;
			result[i] = (byte) (buf[i] ^ state[xorIndex]);
		}
		return result;
	}
	
	
	public static byte[] RC41(byte[] b_key, byte[] buf) {
		byte state[] = new byte[256];

		for (int i = 0; i < 256; i++) {
			state[i] = (byte) i;
		}
		byte tmp;
		int x = 0;
		int y = 0;

		int index1 = 0;
		int index2 = 0;
		if (b_key == null || b_key.length == 0) {
			return null;
		}
		for (int i = 0; i < 256; i++) {
			index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
			tmp = state[i];
			state[i] = state[index2];
			state[index2] = tmp;
			index1 = (index1 + 1) % b_key.length;
		}

		int xorIndex;

		if (buf == null) {
			return null;
		}

		byte[] result = new byte[buf.length];

		for (int i = 0; i < buf.length; i++) {
			x = (x + 1) & 0xff;
			y = ((state[x] & 0xff) + y) & 0xff;
			tmp = state[x];
			state[x] = state[y];
			state[y] = tmp;
			xorIndex = ((state[x] & 0xff) + (state[y] & 0xff)) & 0xff;
			result[i] = (byte) (buf[i] ^ state[xorIndex]);
		}
		return result;
	}

	public static byte[] RC4_Decrypt(byte[] key, byte[] data) {
		if (data == null || key == null) {
			return null;
		}
		return RC4(key, data);
	}
	
	 public static String byte2hex(byte[] b) {
	        String hs = "";
	        String stmp = "";
	        for (int n = 0; n < b.length; n++) {
	            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
	            if (stmp.length() == 1) {
	                hs = hs + "0" + stmp;
	            } else {
	                hs = hs + stmp;
	            }
	        }
	        return hs.toUpperCase();
	    }
	
	 public static byte[] hex2byte(String strhex) {
	        if (strhex == null) {
	            return null;
	        }
	        int l = strhex.length();
	        if (l % 2 == 1) {
	            return null;
	        }
	        byte[] b = new byte[l / 2];
	        for (int i = 0; i != l / 2; i++) {
	            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
	                    16);
	        }
	        return b;
	    }
	 
	 
	public static void main(String args[]){
		byte[] key = "ajhgdjhjdd".getBytes();
		byte[] s = "Cjdnkw".getBytes();
		byte[] rrr = RC4(key,s);
		String m = byte2hex(rrr);
		byte[] c = hex2byte(m );
		System.out.println(byte2hex(rrr));
		System.out.println(new String(RC4(key,c)));
	}
}
