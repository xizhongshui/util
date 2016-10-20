package com.ssj.test.io;

import java.nio.ByteBuffer;

import org.junit.Test;


public class BufferCtrl {

	/**
	 *clear方法，该方法会把读写限制设为缓冲区的容量，同时把读写位置设为0；
	当需要读取一个缓冲区中的数据时，可以调用flip方法，该方法会把读写限制设为当前的读写位置，
	再把读写位置设为0，这样可以保证缓冲区中的全部数据都可以被读取；
	当希望重新读取缓冲区中的数据的时候，可以调用rewind方法，该方法不会改变读写限制，但是会把读写位置设为0*/
	
	/*buffer创建*/
	@Test
	public void create(){
		ByteBuffer buff=ByteBuffer.allocate(1024);//N byte
		
		byte[] byteSrc="我的吻kkjk".getBytes();
		int len=byteSrc.length;
		byte[] byteDire=new byte[len] ;
		buff.put(byteSrc);
		buff.flip();
		buff.get(byteDire, 0, len);
		buff.limit(buff.capacity());
		buff.put(byteSrc);
		System.out.println(new String(byteDire).toString());
	}
	
	/*单字节循环*/
	@Test
	public void loop(){
		ByteBuffer buff=ByteBuffer.allocate(32);
		byte[] byteSrc="字节缓冲去测试kkjk".getBytes();
		int len=byteSrc.length;
		System.out.println("len==="+len);
		buff.put(byteSrc);
		buff.flip();//care
		int remain=buff.remaining();
		byte[] byteDire=new byte[remain] ;
		System.out.println("remain==="+remain);
		for(int i=0;i<remain;i++){
			byteDire[i]=buff.get();
		}
		System.out.println(new String(byteDire).toString());
	}
}
