package com.mysql.mycc;

import java.awt.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnect {
	String drivename = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://jdwlad.com:7356/ecs_advertisement";
	String user = "zhaohengyi";
	String password = "jdwl2015";
	String insql;
	String upsql;
	String delsql;
	String sql = "select * from ecs_advertisement.iphone_reception";
	String name;
	ArrayList<String> retList = new ArrayList<String>();
	
	Connection conn;
	ResultSet rs = null;

	public Connection ConnectMysql() {

		try {
			Class.forName(drivename);
			conn = (Connection) DriverManager
					.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			} else {
				System.out.println("Falled connecting to the Database!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public void CutConnection(Connection conn) throws SQLException {
		try {
			if (rs != null)
				;
			if (conn != null)
				;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null){
				rs.close();
			}
			if (conn != null){
				conn.close();
			}
		}
	}

	public class info {
		String  sn = "";
		String action = "";
		String mobile = "";
		String remark = "";
		String reg = "";
		String act = "";
		
		String count = "";
		public String getReg() {
			return reg;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public void setReg(String reg) {
			this.reg = reg;
		}

		public String getAct() {
			return act;
		}

		public void setAct(String act) {
			this.act = act;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getAction() {
			return action;
		}

		public String getSn() {
			return sn;
		}

		public String getMobile() {
			return mobile;
		}
		
		public String getRemark() {
			return remark;
		}
	}

	public boolean InsertSql(info info) {
		try {
			insql = "insert into ecs_advertisement.iphone_reception(sn,action,mobile,remark) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insql);
			ps.setString(1, info.getSn());
			ps.setString(2, info.getAction());
			ps.setString(3, info.getMobile());
			ps.setString(4, info.getRemark());
			int result = ps.executeUpdate();
			if (result > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String SelectSql(String sql) {
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString("count");
				System.out.println(rs.getString("count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public ArrayList<String> getRet(String sql){
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				retList.add(rs.getString("remark"));
				System.out.println(rs.getString("remark"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}
	public boolean UpdateSql(String upsql) {
		try {
			PreparedStatement ps = conn.prepareStatement(upsql);
			int result = ps.executeUpdate();// 返回行数或者0
			if (result > 0)
				return true;
		} catch (SQLException ex) {
			Logger.getLogger(MyConnect.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return false;
	}
	
	public boolean DeletSql(String delsql) {
		try {
			PreparedStatement ps = conn.prepareStatement(upsql);
			int result = ps.executeUpdate(delsql);
			if (result > 0)
				return true;
		} catch (SQLException ex) {
			Logger.getLogger(MyConnect.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return false;
	}
}
