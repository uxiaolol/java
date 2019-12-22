package com.ftp.control;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpControl {

	private static String userName = "";
	//private static String password = "anonymous@anonymous.org";
	private static String password = "";
	private static Integer port = 50021;	
	
	
	public FTPClient ftpConnect(String host) {
		FTPClient ftpClient = new FTPClient();
		
		
		if (host.isEmpty()) {
			return null;
		}

		ftpClient.setDataTimeout(3 * 60 * 1000); // 设置传输超时时间为60秒
		ftpClient.setConnectTimeout(500); // 连接超时为10秒

		int reply;
		try {
			if (port == null || port <= 0) {
				port = 21;
			}
			ftpClient.connect(host, port);
			boolean logined = ftpClient.login(userName, password);
			if (logined) {
				System.out.println("连接FTP服务器成功");
			}
			else{
				System.out.println("连接FTP服务器使用的用户名/密码错误,无法登陆FTP,userName=" + userName + ",password=" + password
						+ ",host=" + host + ":" + port);
			}
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return null;
			}
			/*if (StringUtils.isNotBlank(ftpDir)) {
				ftp.changeWorkingDirectory(ftpDir);
			}*/
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();// 设置FTP被动传输模式
			return ftpClient;
		} catch (SocketException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return null;
	}
}
