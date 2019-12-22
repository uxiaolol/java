package com.autocontrol.serlvet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.MidiDevice.Info;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.mycc.MyConnect;
import com.mysql.mycc.MyConnect.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//�̳���httpservlet����
@WebServlet(name = "IphoneServlet", urlPatterns = { "/servlet/IphoneServlet" })
public class IphoneServlet extends HttpServlet {
	// ��õ�ǰϵͳʱ��
	public String getNowtimeTwo() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");// ��ʽ��Сд������
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html��charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String urlString = request.getRequestURI();
		System.out.println(urlString);
		PrintWriter out = response.getWriter();

		String action, sn, mobile, remark;
		boolean ret = false;
		try {
			action = request.getParameter("action");
			
			if (action == null){
				MyConnect mycd = new MyConnect();
				info myinfo = mycd.new info();
				Connection cc = mycd.ConnectMysql();
				String sql = "SELECT count(*) as count from iphone_reception where recdate > curdate() and action = 'reg'";
				String strreg = mycd.SelectSql(sql);
				sql = "SELECT count(*) as count from iphone_reception where recdate > curdate() and action = 'active';";				
				String stract = mycd.SelectSql(sql);
				
				out.write("ע��Ϊ:"+strreg+""+"����Ϊ:"+stract);
				
			}
			else{
				String current = getNowtimeTwo();
				System.out.println(current + "��ʼ����get����:" + action);
				sn = request.getParameter("sn");
				mobile = request.getParameter("mobile");
				remark = request.getParameter("remark");
				if (mobile.isEmpty()) {
					mobile = "";
				}
				if (remark.isEmpty()) {
					remark = "";
				}
				MyConnect mycd = new MyConnect();
				info myinfo = mycd.new info();
				Connection cc = mycd.ConnectMysql();
				myinfo.setAction(action);
				myinfo.setSn(sn);
				myinfo.setMobile(mobile);
				myinfo.setRemark(remark);
				ret = mycd.InsertSql(myinfo);
				mycd.CutConnection(cc);
				current = getNowtimeTwo();
				System.out.println(current + "����get����:" + action + "����");
				if (ret) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
