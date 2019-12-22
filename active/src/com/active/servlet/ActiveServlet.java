package com.active.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.connect.MysqlConnect;
//import com.mysql.connect.MysqlConnect.product;
//import com.mysql.connect.MysqlConnect.warehouse;
import com.mysql.jdbc.Connection;

@SuppressWarnings("serial")
public class ActiveServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ActiveServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	// 获得当前系统时间
	public String getNowtimeTwo() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}
	
	public static String addNowtime(int intDay){
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, +intDay);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("Remote Addr: " + request.getRemoteAddr()+" time:"+getNowtimeTwo()); //获得客户端的ip地址 
		try {
			String action,deviceid,ipaddress,code;
			String reqUrl=request.getRequestURL().toString();//http://localhost:8080/game/servlet/game
			//创建MysqlConnect类
			MysqlConnect connect = new MysqlConnect();
			//创建mysql的数据库连接
			Connection connection = (Connection) connect.ConnectMysql();

			action = request.getParameter("action");
			
			ipaddress = request.getRemoteAddr();
			//http请求参数为空返回错误
			if (action == null){
				out.write("error");
			}
			else if (action.equalsIgnoreCase("getUrlQqOne")){
				synchronized(getClass()){
					String account = connect.getUrlQqOne();
					String oldaccount = null;
					//connect.lockAccountbh(account,ipaddress);
					if (account == null){
						out.write("null");
					}
					else{
						String acc[] = account.split("#");
						
						connect.lockUrlQqOne(acc[1],ipaddress);
						out.write(acc[0]);
					}
				}
			}

			else {
				// do nothing
				out.write("error");
			}	
			//关闭数据库连接
			connect.CutConnection(connection);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
