package com.ssj.test.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.Test;
public class PdfTest {
	@Test
	public void test2(){
//		String sealCert="d:\\1.png";
//		Path tempPath=Paths.get("d:\\xy_templ.pdf");
//		Path newPath=Paths.get("d:\\xy.pdf");
//		ByteArrayOutputStream os=new ByteArrayOutputStream();
//		FileOutputStream fos=null;
//		PdfStamper ps=null;
//		try {
////			Files.copy(tempPath, newPath, StandardCopyOption.REPLACE_EXISTING);
//			PdfReader reader =new PdfReader(tempPath.toString());
//			ps=new PdfStamper(reader, os);
//			AcroFields s = ps.getAcroFields();
//			s.setField("qy_year", "2015");
//			s.setField("qy_month", "5");
//			s.setField("qy_day", "26");
//			s.setField("zjlx", "√");
//
//			 Image img = Image.getInstance(sealCert);// 插入水印
//			 img.setAbsolutePosition(150, 100);
//			 PdfContentByte under = ps.getOverContent(2);
//		     under.addImage(img);
//
//			ps.setFormFlattening(true);
//			ps.close();
//			fos = new FileOutputStream(newPath.toString());
//			fos.write(os.toByteArray());
//			fos.flush();
//			fos.close();
//			os.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (com.esa2000.pdfseal.pdflib.core.DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@Test
	public void uuidTest(){
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
}
