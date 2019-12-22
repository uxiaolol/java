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
	    //定义一个字符串（A-Z，a-z，0-9）即62位；
	    String str="zxcvbnmkjhgfdsaqwertyuip";
	    //由Random生成随机数
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //长度为几就循环几次
	        for(int i=0; i<length; ++i){
	          //产生0-61的数字
	          int number=random.nextInt(str.length());
	          //将产生的数字通过length次承载到sb中
	          sb.append(str.charAt(number));
	        }
	        //将承载的字符转换成字符串
	        return sb.toString();
	  }
	
    public static String sendGet(String url, String param) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlNameString = url + "?" + param;  
            URL realUrl = new URL(urlNameString);  
            // 打开和URL之间的连接  
            URLConnection connection = realUrl.openConnection();  
            // 设置通用的请求属性  
            connection.setRequestProperty("Accept", "*/*");  
            connection.setRequestProperty("Connection", "Keep-Alive");  
            //connection.setRequestProperty("Connection", "Keep-Alive");  
           // connection.setRequestProperty("Connection", "Keep-Alive");  
            connection.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
           
            //connection.setRequestProperty(host, value);
            // 建立实际的连接  
            connection.connect();  
            // 获取所有响应头字段  
            Map<String, List<String>> map = connection.getHeaderFields();  
            // 遍历所有的响应头字段  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  
            // 定义 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输入流  
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
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            conn.setRequestProperty("Cookie", "UM_distinctid=1626fce65e0a-0cff3bc0e3180c-17347840-1fa400-1626fce65e2137b; CNZZDATA1273168761=575975200-1522291095-%7C1522291095; PHPSESSID=d0s4k3e32b1hhsj5hc3fvkok17");
	            // 发送POST请求必须设置如下两行  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            //1.获取URLConnection对象对应的输出流  
	            out = new PrintWriter(conn.getOutputStream());  
	            //2.中文有乱码的需要将PrintWriter改为如下  
	            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")  
	            // 发送请求参数  
	            out.print(param);  
	            // flush输出流的缓冲  
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.out.println("发送 POST 请求出现异常！"+e);  
	            e.printStackTrace();  
	        }  
	        //使用finally块来关闭输出流、输入流  
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
	        //System.out.println("post推送结果："+result);  
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
		                System.out.print("文件存在");    
		            } else {    
		                System.out.print("文件不存在");    
		                f.createNewFile();// 不存在则创建    
		            } 
		            BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
		            output.write(account+","+password);  
		            output.write("\r\n");//换行  
		            output.flush();
		            output.close(); 
			 }
		     System.out.println(sr+"----"+account+","+password);  
		    
		}
	}
}
