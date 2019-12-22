package com.mysql.connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.StyledEditorKit.BoldAction;

public class MysqlConnect {
	String drivename = "com.mysql.jdbc.Driver";
	//String url = "jdbc:mysql://127.0.0.1:3306/game?useUnicode=true&characterEncoding=utf8";
	String url = "jdbc:mysql://120.79.195.194:3306/game?useUnicode=true&characterEncoding=utf8";
	String user = "root";
	String password = "Sy19891120a";
	String insql;
	String upsql;
	String delsql;
	String location;
	int codetype=0;
	int index;
	String dev;
	Collection<String[]> resultCollection = new ArrayList<String[]>();	
	int count=0;
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

	//获取Url连接地址
	public String getUrlQqOne(){
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT id,url from game.url_record_one where url_status = 'unlock' order by recdate asc limit 1 "; 
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while(rs.next()){
				index = rs.getInt("id");
				location = rs.getString("url");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String iString = Integer.toString(index);
		return location+"#"+iString;
	}
	//锁定Url连接地址
	public Boolean lockUrlQqOne(String account,String ipaddress){
		try {
			String sqlString = String.format("update game.url_record_one set url_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where id = '%s' ", ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if(preparedStatement.executeUpdate()>0){				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	public ArrayList<String> getRet(String sql){
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
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
			Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE,
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
			Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return false;
	}
}
