package com.ssj.util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


public class JavaMail {
    /**
     * Message对象将存储我们实际发送的电子邮件信息，
     * Message对象被作为一个MimeMessage对象来创建并且需要知道应当选择哪一个JavaMail session。
     */
    private MimeMessage message;
    
    /**
     * Session类代表JavaMail中的一个邮件会话。
     * 每一个基于JavaMail的应用程序至少有一个Session（可以有任意多的Session）。
     * 
     * JavaMail需要Properties来创建一个session对象。
     * 寻找"mail.smtp.host"    属性值就是发送邮件的主机
     * 寻找"mail.smtp.auth"    身份验证，目前免费邮件服务器都需要这一项
     */
    private Session session;
    
    /***
     * 邮件是既可以被发送也可以被受到。JavaMail使用了两个不同的类来完成这两个功能：Transport 和 Store。 
     * Transport 是用来发送信息的，而Store用来收信。对于这的教程我们只需要用到Transport对象。
     */
    private Transport transport;
    
    private String mailHost="";
    private String sender_username="";
    private String sender_password="";

    
    private Properties properties = new Properties();
    /*
     * 初始化方法
     */
    public JavaMail(boolean debug) {
            this.mailHost ="smtp.163.com";
            this.sender_username = "vipcaibao@163.com";
            this.sender_password = "bejmnkdsvycrbrcl";
    	properties.put("mailHost",  this.mailHost );
    	properties.put("sender_username",  this.sender_username);
    	properties.put("sender_password",  this.sender_password );
        session = Session.getInstance(properties);
        session.setDebug(debug);//开启后有调试信息
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     * 
     * @param subject
     *            邮件主题
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public void doSendHtmlEmail(String subject, String sendHtml,
            String receiveUser) {
        try {
            // 发件人
            //InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("vip财宝")+" <"+sender_username+">");
            message.setFrom(from);
            
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);//还可以有CC、BCC
            
            // 邮件主题
            message.setSubject(subject);
            
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");
            
            // 保存邮件
            message.saveChanges();
            
            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            //System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(transport!=null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JavaMail se = new JavaMail(true);
        String htmlH="<html>"
        		+ "<body>";
        String htmlF="</body>"
        		+ "</html>";
        String content="2015-08-06 10:20 查询结果 <br/>";
        String tb="<table border='1'  bordercolor='#000000' >"
        		+ "<tr>"
        				+ "<td>客户名称</td>"
        				+ "<td>身份证号</td>"
        				+ "<td>产品名称</td>"
        				+ "<td>购买金额</td>"
        				+ "<td>持有期限</td>"
        				+ "<td>购买时间</td>"
        				+ "<td>到期时间</td>"
        				+ "<td>到期处理方式</td>"
        		+ "</tr>"
        		+ "<tr>"
        				+ "<td>123</td>"
        				+ "<td>15153546545132</td>"
        				+ "<td>哈哈哈哈</td>"
        				+ "<td>1202.32</td>"
        				+ "<td>12天</td>"
        				+ "<td>2015-08-23 10:23:12</td>"
        				+ "<td>2015-08-23 10:23:12</td>"
        				+ "<td>到期赎回</td>"
        		+ "</tr>";
        content+=tb;
        se.doSendHtmlEmail("邮件主题", htmlH+content+htmlF, "1203872232@qq.com");
    }
}