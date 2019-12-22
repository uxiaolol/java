package czjyPost;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.net.URL;  
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;  
import java.util.Map; 
import java.util.Random;

public class czjyPost {
	public static String getRandomString(int length){
	    //����һ���ַ�����A-Z��a-z��0-9����62λ��
	    String str="zxcvbnmkjhgfdsaqwertyuip";
	    //��Random���������
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //����Ϊ����ѭ������
	        for(int i=0; i<length; ++i){
	          //����0-61������
	          int number=random.nextInt(str.length());
	          //������������ͨ��length�γ��ص�sb��
	          sb.append(str.charAt(number));
	        }
	        //�����ص��ַ�ת�����ַ���
	        return sb.toString();
	  }
	
    public static String sendGet(String url, String param) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlNameString = url + "?" + param;  
            URL realUrl = new URL(urlNameString);  
            // �򿪺�URL֮�������  
            URLConnection connection = realUrl.openConnection();  
            // ����ͨ�õ���������  
            connection.setRequestProperty("Accept", "*/*");  
            connection.setRequestProperty("Connection", "Keep-Alive");  
            //connection.setRequestProperty("Connection", "Keep-Alive");  
           // connection.setRequestProperty("Connection", "Keep-Alive");  
            connection.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
           
            //connection.setRequestProperty(host, value);
            // ����ʵ�ʵ�����  
            connection.connect();  
            // ��ȡ������Ӧͷ�ֶ�  
            Map<String, List<String>> map = connection.getHeaderFields();  
            // �������е���Ӧͷ�ֶ�  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  
            // ���� BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("����GET��������쳣��" + e);  
            e.printStackTrace();  
        }  
        // ʹ��finally�����ر�������  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return result;  
    }  	
	
	public static String sendPost(String url, String param){
		 PrintWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	            URL realUrl = new URL(url);  
	            // �򿪺�URL֮�������  
	            URLConnection conn = realUrl.openConnection();  
	            // ����ͨ�õ���������  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            conn.setRequestProperty("Cookie", "UM_distinctid=1626fce65e0a-0cff3bc0e3180c-17347840-1fa400-1626fce65e2137b; CNZZDATA1273168761=575975200-1522291095-%7C1522291095; PHPSESSID=d0s4k3e32b1hhsj5hc3fvkok17");
	            // ����POST�������������������  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            //1.��ȡURLConnection�����Ӧ�������  
	            out = new PrintWriter(conn.getOutputStream());  
	            //2.�������������Ҫ��PrintWriter��Ϊ����  
	            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")  
	            // �����������  
	            out.print(param);  
	            // flush������Ļ���  
	            out.flush();  
	            // ����BufferedReader����������ȡURL����Ӧ  
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.out.println("���� POST ��������쳣��"+e);  
	            e.printStackTrace();  
	        }  
	        //ʹ��finally�����ر��������������  
	        finally{  
	            try{  
	                if(out!=null){  
	                    out.close();  
	                }  
	                if(in!=null){  
	                    in.close();  
	                }  
	            }  
	            catch(IOException ex){  
	                ex.printStackTrace();  
	            }  
	        }  
	        //System.out.println("post���ͽ����"+result);  
	        return result;  
	    }  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for (int index=1;index <2000;index=index + 1){
			String account = getRandomString(8);
			String password = getRandomString(8);
			
			 String sr=sendPost("http://czjy.rastargame.com/api/gift/gift.api.php?", "act=1&name="+account+"&pwd="+password);  
			 int _index = sr.indexOf("1000");
			 if (_index == 6){
				 File f = new File("E:\\czjy.txt");    
		            if (f.exists()) {    
		                System.out.print("�ļ�����");    
		            } else {    
		                System.out.print("�ļ�������");    
		                f.createNewFile();// �������򴴽�    
		            } 
		            BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
		            output.write(account+","+password);  
		            output.write("\r\n");//����  
		            output.flush();
		            output.close(); 
			 }
		     System.out.println(sr+"----"+account+","+password);  
		    
		}
	}
}
