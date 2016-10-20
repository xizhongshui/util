package com.ssj.test.io.out;

import java.io.ByteArrayOutputStream;

/**
 * 此类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
 * 可使用 toByteArray()和 toString()一次获取全部数组数据
 *
 * Created by shenshuangjie on 2016/10/20.
 */
public class ByteArrayOut {

    public void testOut(){
        int initSize=1024;
        ByteArrayOutputStream out=new ByteArrayOutputStream(initSize);

        try {
            String src="zhansan_张三";
            byte [] bytes=src.getBytes("UTF-8");
            out.write(bytes,0,bytes.length);

            out.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
