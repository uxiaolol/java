package FtpSocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.commons.net.ftp.FTPClient;
import org.ftp.transmission.config.Config;
import org.ftp.transmission.tools.Tools;
public class FtpSocketClient {
    Socket mFtpClient = null;
    BufferedReader mReader = null;
    BufferedWriter mWriter = null;
    
    public void connectFtp() {
        try {
            mFtpClient = new Socket(Config.FTP.HOST_IP, Config.FTP.HOST_PORT);
            mReader = new BufferedReader(new InputStreamReader(mFtpClient.getInputStream()));
            mWriter = new BufferedWriter(new OutputStreamWriter(mFtpClient.getOutputStream()));

            sendCommand("USER " + Config.FTP.FTP_USERNAME);
            sendCommand("PASS " + Config.FTP.FTP_PASSWD);
            //sendCommand("quote RETR .__REBOOT__.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean socketStatus(Socket socket) {
        if (socket == null || !socket.isConnected()) {
            return false;
        }
        return true;
    }
    
    public  void sendCommand(String command) throws IOException {
        if (Tools.StringTools.isEmpty(command)) {
            return;
        }

        if (mFtpClient == null) {
            return;
        }

        mWriter.write(command + "\r\n");
        mWriter.flush();
    }
    
    public static void exeCmd(String commandStr) {  
        BufferedReader br = null;  
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
        }  
      }
    } 
//    public static  void main(String[] args) {
//    	FtpSocketClient myFtpSocketClient = new FtpSocketClient();
//    	try {
//			myFtpSocketClient.sendCommand("RETR .__REBOOT__.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}