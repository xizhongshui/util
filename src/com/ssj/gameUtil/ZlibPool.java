package com.ssj.gameUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZlibPool {
	private static final ZlibPool[] pool = new ZlibPool[10];
	private static final int cachesize = 1024;
	private Inflater decompresser = new Inflater();
	private Deflater compresser = new Deflater();
	private static final int poolSize = pool.length;

	private ZlibPool() {

	}

	public synchronized byte[] compressBytes(byte input[]) {
		this.compresser.reset();
		this.compresser.setInput(input);
		this.compresser.finish();
		byte output[] = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream(input.length);
		try {
			byte[] buf = new byte[cachesize];
			int got;
			while (!compresser.finished()) {
				got = compresser.deflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	public synchronized byte[] decompressBytes(byte data[]) {
		byte[] output = new byte[0];
		this.decompresser.reset();
		this.decompresser.setInput(data);

		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!this.decompresser.finished()) {
				int i = this.decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;

	}

	private static ZlibPool getCompress() {
		return pool[Rnd.get(poolSize)];
	}

	private static ZlibPool getDecompress() {
		return pool[Rnd.get(poolSize)];
	}

	public static byte[] compress(byte input[]) {
		return getCompress().compressBytes(input);
	}

	public static byte[] decompress(byte data[]) {
		return getDecompress().decompressBytes(data);
	}

	public static void initial() {
		for (int i = 0; i < poolSize; i++) {
			pool[i] = new ZlibPool();
		}
	}
}
