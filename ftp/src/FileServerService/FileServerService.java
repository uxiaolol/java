package FileServerService;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileServerService {
	private static String host = "192.168.5.84";
	//private static String userName = "ftp";
	private static String userName = "";
	//private static String password = "anonymous@anonymous.org";
	private static String password = "";
	private static Integer port = 50021;
	

	private FTPClient connect() {
		FTPClient ftp;
		
		if (host.isEmpty()) {
			return null;
		}
		ftp = new FTPClient();
		ftp.setDataTimeout(3 * 60 * 1000); // ���ô��䳬ʱʱ��Ϊ60��
		ftp.setConnectTimeout(10 * 1000); // ���ӳ�ʱΪ10��

		int reply;
		try {
			if (port == null || port <= 0) {
				port = 21;
			}
			ftp.connect(host, port);
			boolean logined = ftp.login(userName, password);
			if (logined) {
				System.out.println("����FTP�������ɹ�");
			}
			else{
				System.out.println("����FTP������ʹ�õ��û���/�������,�޷���½FTP,userName=" + userName + ",password=" + password
						+ ",host=" + host + ":" + port);
			}
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			/*if (StringUtils.isNotBlank(ftpDir)) {
				ftp.changeWorkingDirectory(ftpDir);
			}*/
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();// ����FTP��������ģʽ
			return ftp;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FileServerService myFtpClient = new FileServerService();
		FTPClient ftpClient=myFtpClient.connect();
		try {
			int ret = 0;
			ret = ftpClient.sendCommand("RETR .__STARTSCRIPT__. jdwl_hhw.lua");
			
			//ret = ftpClient.sendCommand("RETR .__STOPPLAY__.");
			System.out.println(ret);
			//ftpClient.sendCommand("RETR .__REBOOT__.");
			//ftpClient.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// TODO Auto-generated catch block
			
		}
	}

