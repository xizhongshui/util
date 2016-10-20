package com.ssj.gameUtil;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by shenshuangjie on 2016/10/20.
 */
public class MyZlib {
    private static final Logger logger= LoggerFactory.getLogger(MyZlib.class);

    private static int cacheSize=1024;

    private static  Inflater decompresser=new Inflater();
    private static Deflater compresser=new Deflater();


    public static byte[] compress(byte input[]){
        byte output[]=new byte[0];
        ByteArrayOutputStream outputStream=null;
        try {
            compresser.reset();
            compresser.setInput(input);
            compresser.finish();

            byte buf[]=new byte[cacheSize];
            outputStream=new ByteArrayOutputStream();

            while (!compresser.finished()){
                int comLength=compresser.deflate(buf);
                outputStream.write(buf,0,comLength);
            }
            output=outputStream.toByteArray();
        }catch (Exception e){
            logger.info("### compress error ",e);
        } finally {
            try {
                if( outputStream!=null){
                    outputStream.close();
                    outputStream=null;
                }
            }catch (Exception e) {
                logger.info("### compress error ",e);
            }

        }
        return output;
    }


    public static byte[] decompress(byte input[]){
        byte outout[]=new byte[0];
        ByteArrayOutputStream outputStream=null;
        try {
            decompresser.reset();
            decompresser.setInput(input);

            outputStream= new ByteArrayOutputStream();
            byte buf[]=new byte[cacheSize];
            while (decompresser.getRemaining()!=0){
                int point=decompresser.inflate(buf);
                outputStream.write(buf,0,point);
            }
            outout= outputStream.toByteArray();

        }catch (Exception e){
            logger.info("### decompresser error ",e);
        } finally {
            try {
                if( outputStream!=null){
                    outputStream.close();
                    outputStream=null;
                }
            }catch (Exception e) {
                logger.info("### decompresser error ",e);
            }
        }
        return outout;
    }

    @Test
    public void compresserTest(){
        String src="shdfzh战三";
        try{

            byte src_byte[]=src.getBytes();
            MyZlib.compress(src_byte);

        }catch (Exception e){
            logger.info("### compresserTest error ",e);
        }
    }

    @Test
    public void zlibTest(){
        String src="shdfzh战三jkasdijkk";
        try{

            byte src_byte[]=src.getBytes();
            byte comp_byte[]=MyZlib.compress(src_byte);
            byte decomp_byte[]= MyZlib.decompress(comp_byte);

            String decomp_src=new String(decomp_byte);
            logger.info("### decomp_src:"+decomp_src);

        }catch (Exception e){
            logger.info("### compresserTest error ",e);
        }


    }



}
