package com.control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.jni.OS;

import com.ftp.control.*;
/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	// 获得当前系统时间
	public String getNowtimeTwo() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}
	/**
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//out.write("Remote Addr: " + request.getRemoteAddr()+" time:"+getNowtimeTwo()); //获得客户端的ip地址 
		String getRequestURL =request.getRequestURL().toString();
		
		Map map=request.getParameterMap();  
	    Set keSet=map.entrySet();  
	    ArrayList<String> list = new ArrayList<String>();
	    String scriptName=request.getParameter("scriptName");
	    String option=request.getParameter("operateOption");
	    for(Iterator itr=keSet.iterator();itr.hasNext();){  
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();
	        Object ov=me.getValue();
	        String[] value=new String[1];
	        if(ov instanceof String[]){  
	            value=(String[])ov;  
	        }else{
	            value[0]=ov.toString();  
	        }
	        for(int k=0;k<value.length;k++){  
	            //System.out.println(ok+" "+value[k]);
	        	if (ok.equals("ip")){
	        		list.add(value[k]);
	        	}
	        }  
	      }
	    
	    for(int i = 0;i < list.size(); i ++){
            System.out.println(list.get(i));
            String ip = list.get(i);
            ip = "192.168."+ip;
            FtpControl ftpControl = new FtpControl();
    	    FTPClient ftpRet = ftpControl.ftpConnect(ip);
    	    if (ftpRet != null) {
    	    	out.write(ip+" connect success!\n");
    	    	System.out.println(" connect success!");
    	    	String strCommand =null;
    	    	if (option.equalsIgnoreCase("startScript")){
    	    		strCommand = String.format("RETR .__STARTSCRIPT__. %s", scriptName);
    	    	}
    	    	else if(option.equalsIgnoreCase("stopScript")){
    	    		strCommand = "RETR .__STOPPLAY__.";
    	    	}
    	    	ftpRet.sendCommand(strCommand);
    	    }
    	    else{
    	    	System.out.println(" connect failed!");
    	    	out.write(ip+">>>>>>connect failed!<<<<<<<<\n");
    	    }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
