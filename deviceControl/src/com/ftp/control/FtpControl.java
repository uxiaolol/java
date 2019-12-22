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

		ftpClient.setDataTimeout(3 * 60 * 1000); // ���ô��䳬ʱʱ��Ϊ60��
		ftpClient.setConnectTimeout(500); // ���ӳ�ʱΪ10��

		int reply;
		try {
			if (port == null || port <= 0) {
				port = 21;
			}
			ftpClient.connect(host, port);
			boolean logined = ftpClient.login(userName, password);
			if (logined) {
				System.out.println("����FTP�������ɹ�");
			}
			else{
				System.out.println("����FTP������ʹ�õ��û���/�������,�޷���½FTP,userName=" + userName + ",password=" + password
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
			ftpClient.enterLocalPassiveMode();// ����FTP��������ģʽ
			return ftpClient;
		} catch (SocketException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return null;
	}
}
