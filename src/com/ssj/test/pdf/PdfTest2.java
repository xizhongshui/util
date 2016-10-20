package com.ssj.test.pdf;

import org.junit.Test;

import java.util.UUID;

/*import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;*/

public class PdfTest2 {
	
	@Test
	public void PDFForWebService() {
//		Date spendTime=new Date();
//		Calendar spendTimeCal=DateUtil.baseCalAndDateTimeCalendar(Calendar.getInstance(), spendTime);
//		String idCardNo="410928198907081354";
//		String productName="V45天";
//		 String period="45";
//		 String RMB="一千伍三十八万五千六百元整";
//		 String amount="15385600";
//		 String payment="15385600";
//		 String profit="5.6";
//		 String userId="13323852175userid";
//		 String userName="上官丹凤步非烟";//上官丹凤步非烟
//		 String userUUid="037bdcd8c44049d78d3118808c7159de";
//		 String mobile="13323852175";
//		 String channelCode="产品渠道";
//		 String productType="100";
//		 String productId="100productId";
//		 String orderNo="cb_orderNo";
//		String allPath="";
//		String projectPdfPath="D:";
//		String pdfPath="D:";
//		try {
//			System.out.println(projectPdfPath+"-----------");
//			String sealCert=projectPdfPath+"\\pdf\\gaizhang.png";//盖章
//			String tempPath_100=projectPdfPath+"\\pdf\\100_s.pdf";//100模板
//			String tempPath_No100=projectPdfPath+"\\pdf\\no_100.pdf";//_100模板
//			ByteArrayOutputStream os=new ByteArrayOutputStream();
//			FileOutputStream fos=null;
//			PdfStamper ps=null;
//			Calendar cal = Calendar.getInstance();// 使用日历类
//			int year = cal.get(Calendar.YEAR);// 得到年
//			int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
//			int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
//			String monthPath = pdfPath + File.separator + year
//							+ File.separator + month + File.separator + day;
//			File file = new File(monthPath + File.separator+"db");
//			if (!file.exists()) {
//				file.mkdirs();
//			}
//			allPath = "D:\\" + getUuid() + "-me.pdf";;
//			try {
//				 FontSelector selector = new FontSelector();
////			        selector.addFont(FontFactory.getFont("STSong-Light",   "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
//			        selector.addFont(FontFactory.getFont("HELVETI1",   "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
//				if("100".equals(productType)){
//					PdfReader reader =new PdfReader(tempPath_100);
//					ps=new PdfStamper(reader, os);
//					AcroFields s = ps.getAcroFields();
//					s.setField("year", (spendTimeCal.get(Calendar.YEAR)+""));
//					s.setField("month", ((spendTimeCal.get(Calendar.MONTH)+2)+""));
//					s.setField("day",(spendTimeCal.get(Calendar.DAY_OF_MONTH)+""));
//					s.setField("idcardnosuccess", "√");
//					s.setField("RMB", RMB);
//					s.setField("amount", amount);
//					s.setField("isfy", "FY".equals(payment)?"√":"x");
//					s.setField("notisfy", "FY".equals(payment)?"x":"√");
//					s.setField("idcardno", idCardNo);
//					s.setField("productName", productName);
//					s.setField("profit", profit+"%");
//					s.setField("period", (period+"天"));
//					s.setField("mobile", mobile);
//					s.setField("realName",  selector.process(userName).getContent()  );
//					Image img = Image.getInstance(sealCert);// 插入水印
//					img.setAbsolutePosition(360,330);
//					PdfContentByte under = ps.getOverContent(2);
//					img.scalePercent(50);//依照比例缩
//				    under.addImage(img);
//
//					ps.setFormFlattening(true);
//					ps.close();
//					System.out.println("allPath======"+allPath);
//					fos = new FileOutputStream(allPath);
//					fos.write(os.toByteArray());
//					fos.flush();
//					fos.close();
//					os.close();
//				}else{
//					PdfReader reader =new PdfReader(tempPath_No100);
//					ps=new PdfStamper(reader, os);
//					AcroFields s = ps.getAcroFields();
//					s.setField("year", (spendTimeCal.get(Calendar.YEAR)+""));
//					s.setField("month", ((spendTimeCal.get(Calendar.MONTH)+1)+""));
//					s.setField("day",(spendTimeCal.get(Calendar.DAY_OF_MONTH)+""));
//					s.setField("idcardnosuccess", "√");
//					s.setField("RMB", RMB);
//					s.setField("amount", amount);
//					s.setField("isfy", "FY".equals(payment)?"√":"X");
//					s.setField("notisfy", "FY".equals(payment)?"x":"√");
//					s.setField("idcardno", idCardNo);
//					s.setField("productName", productName);
//					s.setField("profit", profit+"%");
//					s.setField("period", (period+"天"));
//					s.setField("realName", userName);
//					s.setField("mobile", mobile);
////					s.setField("realName", userName);
//					Image img = Image.getInstance(sealCert);// 插入水印
//					img.setAbsolutePosition(360,75);
//					PdfContentByte under = ps.getOverContent(9);
//					img.scalePercent(50);//依照比例缩
//				    under.addImage(img);
//
//					ps.setFormFlattening(false);
//					ps.close();
//					fos = new FileOutputStream(allPath);
//					fos.write(os.toByteArray());
//					fos.flush();
//					fos.close();
//					os.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public String getUuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
