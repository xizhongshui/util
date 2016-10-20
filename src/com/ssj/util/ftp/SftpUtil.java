package com.ssj.util.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
	
	private static Logger logger = Logger.getLogger(SftpUtil.class);
	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public static ChannelSftp getConnect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			//设置第一次登陆的时候提示，可选值：(ask | yes | no)
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			//设置登陆超时时间   
			sshSession.connect(30000);
			logger.info("ssh session created..................................................................");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect(10000);
			logger.info("ssh session open channel......................................................");
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + host + ".");
		} catch (Exception e) {

		}
		return sftp;
	}


	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public static void downFile(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
		InputStream in = null;
		InputStreamReader reader = null;
		BufferedReader dr = null;
		String str = null;
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
		/*	if(!file.exists()){
				file.createNewFile();
			}*/
			sftp.get(downloadFile, new FileOutputStream(file));
			
			in = sftp.get(directory+downloadFile);
			reader = new InputStreamReader(in,"utf-8");
			dr=new BufferedReader(reader);
			while((str = dr.readLine()) != null){ 
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(dr!=null)
					dr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(reader!=null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public static Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		try {
			return sftp.ls(directory);
		} catch (Exception e) {
			logger.info("文件目录不存在！");
			return null;
		}
	}
	
	public static void close(ChannelSftp sftp){
		if(sftp!=null && !sftp.isClosed()){
			sftp.exit();
		}
}
	
	@Test
	public void test(){
		ChannelSftp  chanSftp=SftpUtil.getConnect("101.200.183.50", 22, "mysql", "123456");
		SftpUtil.downFile("/home/mysql/data/", "20150611140033_App.txt", "e://20150611140033_App.txt", chanSftp);
		SftpUtil.close(chanSftp);
	}
}