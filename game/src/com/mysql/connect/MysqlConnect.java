package com.mysql.connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatCodePointException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.SessionTrackingMode;
import javax.servlet.jsp.tagext.TagExtraInfo;

import org.apache.tomcat.jni.Local;
import org.omg.Messaging.SyncScopeHelper;

public class MysqlConnect {
	String drivename = "com.mysql.jdbc.Driver";
	// String url =
	// "jdbc:mysql://192.168.1.200:3306/game?useUnicode=true&characterEncoding=utf8";
	String url = "jdbc:mysql://192.168.1.200:3306/game?useUnicode=true&characterEncoding=utf8";
	// String url =
	// "jdbc:mysql://47.92.66.119:3306/yxb?useUnicode=true&characterEncoding=utf8";
	String user = "root";
	String password = "root";
	// String password = "Sy19891120a";
	String location = "";
	int index = 0;
	int count = 0;

	Connection conn = null;
	ResultSet rs = null;

	public Connection ConnectMysql() {

		try {
			Class.forName(drivename);
			conn = (Connection) DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				// System.out.println("Succeeded connecting to the Database!");
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
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	// 获取突袭
	public String getAccountTx() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from tx_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取魔卡领域ios账号
	public String getAccountMklyIOS() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from mkly_account_ios where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取队长小一账号
	public String getAccountdz() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "select account,pwd from dzxy_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 获取半世界之旅账号
	public String getAccountbsjzl() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "select account,pwd from bsjzl_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public String getAccountth() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "select account,pwd from thbjz_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 获取明日方舟账号自抽
	public String getAccountMrfzZc() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.mrfz_account_zc where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				// location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取明日方舟账号初始
	public String getAccountMrfzSsr() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,tok,flag from game.mrfz_account_ssr where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("tok");
				location = location + "#" + rs.getString("flag");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取明日方舟账号初始签到
	public String getAccountMrfzSsrSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,tok,flag from game.mrfz_account_ssr where  acc_status = 'unlock' ORDER BY offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("tok");
				location = location + "#" + rs.getString("flag");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取明日方舟账号初始
	public String getAccountMrfzSsrByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT account,pwd,tok from game.mrfz_account_ssr where acc_status = 'unlock' and account='%s'",
					uid);

			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("tok");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 已售状态明日方舟
	public boolean SoldMrfzSsrByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.mrfz_account_ssr set acc_status ='sold',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 恢复状态明日方舟
	public boolean RecMrfzSsrByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.mrfz_account_ssr set acc_status ='unlock',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean SoldBangByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.bang_account set acc_status ='sold',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean SoldXzgjByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.xzgj_account set acc_status ='sold',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 恢复状态明日方舟
	public boolean RecBangByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.bang_account set acc_status ='unlock',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean RecXzgjByUid(String uid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"update game.xzgj_account set acc_status ='unlock',recdate=CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP  where account = '%s'",
					uid);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 获取敢达账号
	public String getAccountgd() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from gd_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取qq飞车账号
	public String getAccounSMD() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from saomd_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定文豪迷犬账号
	public Boolean lockAccountSMD(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.saomd_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放saomd飞车账号
	public boolean releaseAccountSMD(String account, int diamond, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.saomd_account set acc_status = 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',diamonds='%d' where account = '%s'",
					ipaddress, diamond, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放魔卡领域ios账号
	public boolean releaseAccountMklyIOS(String account, String remark, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.mkly_account_ios set acc_status = 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',remark='%s' where account = '%s'",
					ipaddress, remark, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 获取脚本版本号--东莞
	public String getScrpitsVersion(String scriptName) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select version_num from game.scripts_version where scripts_name = '%s'",
					scriptName);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("version_num");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 获取龙珠觉醒账号
	public String getAccountLzjx() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from lzjx_account where acc_status = 'unlock' and serverid>0  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取Url连接地址
	public String getUrlQqOne() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT id,url from game.url_record_one where url_status = 'unlock' order by recdate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				index = rs.getInt("id");
				location = rs.getString("url");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String iString = Integer.toString(index);
		return location + "#" + iString;
	}

	public String getUrlQqTwo() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT id,url from game.url_record_two where url_status = 'unlock' order by recdate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				index = rs.getInt("id");
				location = rs.getString("url");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String iString = Integer.toString(index);
		return location + "#" + iString;
	}

	public String getUrlQqThree() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT id,url from game.url_record_three where url_status = 'unlock' order by recdate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				index = rs.getInt("id");
				location = rs.getString("url");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String iString = Integer.toString(index);
		return location + "#" + iString;
	}

	// 获取崩坏3验证表账号
	public String getAccountbhidentify() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from bh_identify where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取敢达安卓
	public String getAccountGdAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from gd_account_android where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			// String sqlString = "SELECT account,level from bh_account where
			// (length(remark)=4 or length(remark)=5 ) and acc_status='unlock'";
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");

				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取敢达安卓测试
	public String getAccountGdAndroidTest() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from gd_account_android where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			// String sqlString = "SELECT account,level from bh_account where
			// (length(remark)=4 or length(remark)=5 ) and acc_status='unlock'";
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 安卓敢达根据imei查询锁定账号信息
	public String getLockInfoByImeiGdAndroidTest(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from gd_account_android where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 明日方舟根据imei查询锁定账号信息
	public String getLockInfoByImeiMrfzSsr(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,tok,flag from mrfz_account_ssr where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("tok");
				location = location + "#" + rs.getString("flag");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 安卓敢达根据imei查询是否有锁定账号
	public int getLockCntByImeiGdAndroidTest(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from gd_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 明日方舟根据imei查询是否有锁定账号
	public int getLockCntByImeiMrfzSsr(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from mrfz_account_ssr where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取时之歌
	public String getAccountLx() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.lx_ssr where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			// String sqlString = "SELECT account,level from bh_account where
			// (length(remark)=4 or length(remark)=5 ) and acc_status='unlock'";
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定崩坏3验号表
	public Boolean lockAccountbhidentify(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.bh_identify set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定半世界之旅账号
	public Boolean lockAccountbsjzl(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.bsjzl_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定队长小一账号
	public boolean lockAccountdz(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.dzxy_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 锁定天华账号
	public boolean lockAccountth(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.thbjz_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 锁定敢达账号
	public Boolean lockAccountgd(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.gd_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定明日方舟自抽账号
	public Boolean lockAccountMrfzZc(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.mrfz_account_zc set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定明日方舟初始账号
	public Boolean lockAccountMrfzSsr(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.mrfz_account_ssr set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定敢达安卓账号
	public Boolean lockAccountGdAndroid(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.gd_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定敢达安卓账号测试
	public Boolean lockAccountGdAndroidTest(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.gd_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定突袭账号
	public Boolean lockAccountTx(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.tx_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定魔卡领域IOS
	public Boolean lockAccountMklyIOS(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.mkly_account_ios set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定Url连接地址
	public Boolean lockUrlQqOne(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.url_record_one set url_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where id = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean lockUrlQqTwo(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.url_record_two set url_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where id = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean lockUrlQqThree(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.url_record_three set url_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where id = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定食之契约账号表
	public Boolean lockAccountLx(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.lx_ssr set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定茗心录账号表
	public Boolean lockAccountLzjx(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.lzjx_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传天命之子初始账号
	public boolean uploadAccountTmzz(String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.kzgj_cdkey  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传天命之子初始账号
	public boolean uploadAccountFyzj(String account, String pwd, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.fyzj_account  (account,pwd,ipaddress) values (?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			ps.setString(3, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传天命之子自抽账号
	public boolean uploadAccountTmzzZc(String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.tmzz_zc  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传张焱账号
	public boolean uploadAccountZy(String account,String pwd, String ipaddress) {
		try {
			String sqlString = String.format(
					"insert into  game.zy_account  (account,pwd,ipaddress) values (?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			ps.setString(3, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean uploadAccountBangSsrAndroid(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"insert into  game.bang_account_android  (account,ipaddress,offlinedate) values (?,?,'2019-06-08')");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传紫龙账号
	public boolean uploadAccZlong(String account, String pwd) {
		try {
			String sqlString = String.format("insert into  game.zlgame_guest  (account,pwd) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传超次元
	public boolean uploadAccCcy(String account, String pwd) {
		try {
			String sqlString = String.format("insert into  game.ccy_account  (account,pwd) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传明日方舟账号
	public boolean uploadAccMrfzZc(String account, String pwd) {
		try {
			String sqlString = String.format("insert into  game.mrfz_account_zc  (account,pwd) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传明日方舟初始账号
	public boolean uploadAccMrfzSsr(String account, String pwd, String token) {
		try {
			String sqlString = String.format(
					"insert into  game.mrfz_account_ssr  (account,pwd,tok,offlinedate,flag) values (?,?,?,'1972-01-01','no')");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			ps.setString(3, token);

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传明日方舟初始账号抽友情
	public boolean uploadAccMrfzSsrEx(String account, String pwd, String token, String remark, int total, String role,
			String username) {
		try {
			String sqlString="";
			if (role.equalsIgnoreCase("nil")){
				sqlString = String.format(
						"insert into  game.mrfz_account_ssr  (username,account,pwd,tok,flag,offlinedate,remark,total) values ('%s','%s','%s','%s','no',CURRENT_TIMESTAMP,'%s',%d)",
						username, account, pwd, token, remark, total);
			}
			else{
				String originalStr = role;

				String[] rr = role.split(",");
				String roleRet = "";
				if (rr.length > 0) {

					for (int i = 0; i < rr.length; i++) {
						roleRet = "1," + roleRet;
					}
					roleRet = roleRet.substring(0, roleRet.length() - 1);
				}

				sqlString = String.format(
						"insert into  game.mrfz_account_ssr  (username,account,pwd,tok,flag,offlinedate,remark,total,%s) values ('%s','%s','%s','%s','no',CURRENT_TIMESTAMP,'%s',%d,%s)",
						role, username, account, pwd, token, remark, total, roleRet);
			}
			
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			/*
			 * ps.setString(1, account); ps.setString(2, pwd); ps.setString(3,
			 * token); ps.setString(4, remark); ps.setInt(5,total);
			 */
			System.out.println(sqlString);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传茗心录账号
	public boolean uploadAccountLzjx(String account, String pwd) {
		try {
			String sqlString = String.format("insert into  game.jtjs_account  (account,pwd,offlinedate) values (?,?,'2018-05-05')");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传苍之纪元账号
	public boolean uploadAccountczjy(String account, String pwd) {
		try {
			String sqlString = String.format("insert into  game.mg_account  (account,pwd) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放崩坏3账号
	public boolean releaseAccountTx(String account, int zs, int wqj, int fyz, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.tx_account set acc_status ='unlock',recdate = CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP,zuanshi=%d,wuqijuan=%d,fengyin=%d,ipaddress='%s' where account = '%s'",
					zs, wqj, fyz, ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放半世界之旅账号
	public boolean releaseAccountbsjzl(String account, String diamond, String ticket, String total, String remark,
			String role, String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.bsjzl_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,diamond='%s' ,remark = '%s',ticket='%s',total='%s', ipaddress ='%s'  where account = '%s'",
						diamond, remark, ticket, total, ipaddress, account);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.bsjzl_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,diamond='%s' ,remark = '%s',ticket='%s',total='%s', ipaddress ='%s'  where account = '%s'",
						diamond, remark, ticket, total, ipaddress, account);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.bsjzl_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,diamond='%s' ,remark = '%s',ticket='%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						diamond, remark, ticket, total, url, ipaddress, account);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放队长小一账号
	public boolean releaseAccountdz(String account, Integer total, String remark, String role, String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.dzxy_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'   where account = '%s'",
						ipaddress, account);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.dzxy_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'   where account = '%s'",
						ipaddress, account);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.dzxy_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,role = '%s',total='%d',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
			}
			// String sqlString =String.format("update game.dzxy_account set
			// acc_status ='unlock',recdate =
			// CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP,total='%s',role='%s',ipaddress='%s'
			// where account = '%s'", total,role,ipaddress,account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放天华账号
	public boolean releaseAccountth(String account, String diamond, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.thbjz_account set acc_status ='unlock',recdate = CURRENT_TIMESTAMP,offlinedate=CURRENT_TIMESTAMP,diamond='%s',ipaddress='%s' where account = '%s'",
					diamond, ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放明日方舟自抽
	public boolean releaseAccountMrfzZc(String account, String remark, String ipaddress, int ys, int hcy, int xfpz) {
		try {
			String sqlString = String.format(
					"update game.mrfz_account_zc set remark='%s',acc_status = 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress='%s',ys='%d',hcy='%d',xfpz='%d' where account = '%s'",
					remark, ipaddress, ys, hcy, xfpz, account);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 明日方舟空号
	public boolean releaseAccountMrfzSsrNull(String account) {
		try {
			String sqlString = String.format(
					"update game.mrfz_account_ssr set acc_status='error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP where account='%s' ",
					account);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 明日方舟签到
	public boolean releaseAccountMrfzSsrSign(String account) {
		try {
			String sqlString = String.format(
					"update game.mrfz_account_ssr set acc_status='unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP where account='%s' ",
					account);

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 释放明日方舟初始
	public boolean releaseAccountMrfzSsr(String account, String remark, String role, int total, String ipaddress) {
		try {
			String sqlString = null;
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.mrfz_account_ssr set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,imei=NULL,ipaddress ='%s'  where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				String url = "";
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + "=1,";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.mrfz_account_ssr set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,%s remark='%s',ipaddress ='%s',total=%d where account = '%s'",
						url, remark, ipaddress, total, account);
				//
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 释放敢达账号
	public boolean releaseAccountgd(String account, int points, int tickets, int icg, int ijt, String stars,
			String roleLevel, String ipaddress) {
		try {
			// String sqlString = "update game.gd_account set acc_status =
			// 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate =
			// CURRENT_TIMESTAMP,points ='"+points+"',ipaddress
			// ='"+ipaddress+"', tickets='"+tickets+" ',remark = '"+roleLevel +"
			// ', stars='"+stars+"' where account ='"+account+"'";
			// String sqlString = "update game.gd_account set acc_status =
			// 'finished', recdate = CURRENT_TIMESTAMP,offlinedate =
			// CURRENT_TIMESTAMP,points ='"+points+"',ipaddress
			// ='"+ipaddress+"', tickets='"+tickets+" ',remark = '"+roleLevel +"
			// ', stars='"+stars+"' where account ='"+account+"'";
			String sqlString = String.format(
					"update game.gd_account set acc_status = 'finished', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,cg='%d',jt='%d',points ='%d',ipaddress ='%s', tickets='%d',remark = '%s', stars='%s'  where account ='%s'",
					icg, ijt, points, ipaddress, tickets, roleLevel, stars, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放崩坏3账号
	public boolean releaseAccountLzjx(String account, String remark, String ipaddress) {
		try {

			String sqlString = String.format(
					"update game.lzjx_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',remark='%s'  where account = '%s'",
					ipaddress, remark, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放十二战纪账号
	public boolean releaseAccountsezj(String account, String diamond, String ipaddress) {
		try {
			String sqlString = "update game.sezj_account set acc_status = 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='"
					+ ipaddress + "', remark='" + diamond + "'  where account ='" + account + "'";
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放崩坏3账号验证表
	public boolean releaseAccountbhidentify(String account, String level, String diamond, String ipaddress) {
		try {
			String sqlString = "update game.bh_identify set acc_status = 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,level ='"
					+ level + "',ipaddress ='" + ipaddress + "', remark='" + diamond + "'  where account ='" + account
					+ "'";
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放时之歌账号
	public boolean releaseAccountLx(String account, String diamond, String role, String remark, String ipaddress) {
		try {

			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.lx_ssr set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',diamond='%s' ,remark = '%s'  where account = '%s'",
						ipaddress, diamond, remark, account);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + "=1,";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.lx_ssr set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,%s ipaddress ='%s',diamond='%s' ,remark='%s'  where account = '%s'",
						url, ipaddress, diamond, remark, account);
			}
			// String sqlString = "update game.szg_account set acc_status =
			// 'unlock', recdate = CURRENT_TIMESTAMP,offlinedate =
			// CURRENT_TIMESTAMP,urrecord ='"+urrecord+"',ipaddress
			// ='"+ipaddress+"' where account ='"+account+"'";
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放敢达安卓账号
	public boolean releaseAccountGdAndroid(String account, String normal, String strong, String diamonds,
			String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.gd_account_android set recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP, acc_status='unlock', normal='%s',strong='%s',diamonds='%s' ,ipaddress='%s' where account = '%s'",
					normal, strong, diamonds, ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放安卓敢达测试
	public boolean releaseAccountGdAndroidTest(String account, String normal, String strong, String diamonds,
			String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.gd_account_android set recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP, acc_status='finished', normal='%s',strong='%s',diamonds='%s' ,ipaddress='%s',imei=NULL where account = '%s'",
					normal, strong, diamonds, ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 删除苍之纪元账号密码错误
	public boolean czjyError(String account) {
		try {
			String sqlString = "update game.czjy_account set acc_status = 'accerror' where account= '" + account + "'";
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 删除崩坏3账号
	public boolean deleteAccountbh(String account) {
		try {
			String sqlString = "delete from game.bh_account   where account ='" + account + "'";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public String getAccountMklyAndroid() {
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd from mkly_android_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);

			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定第七史诗账号
	public boolean lockAccountMklyAndroid(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.mkly_android_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放第七史诗账号
	public Boolean releaseAccountMklyAndroid(String account, String holywater, String bluestone, String redstone,
			String urcard, String ipaddress) {
		try {

			String sqlString = String.format(
					"update game.mkly_android_account set acc_status = 'finished', recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,holywater='%s',bluestone='%s',redstone='%s',urcard='%s',ipaddress='%s' where account = '%s'",
					holywater, bluestone, redstone, urcard, ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	/* 梦幻模拟战 */
	// 获取天命之子账号
	public String getAccTmzz() {
		// TODO Auto-generated method stub
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account from game.tmzz_test where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取天命之子自抽号
	public String getAccTmzzZc() {
		// TODO Auto-generated method stub
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account from game.tmzz_zc where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 取梦幻模拟战不用vpn账号
	public String getAccMhmnzNoVPN() {
		// TODO Auto-generated method stub
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,serverid from game.mhmnz_acc where acc_status = 'unlock' and remark = 'novpn'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);

			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定天命之子账号
	public boolean lockAccTmzz(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.tmzz_test  set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 锁定天命之子自抽号
	public boolean lockAccTmzzZc(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"update game.tmzz_zc  set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放天命之子账号
	public Boolean releaseAccTmzz(String account, String diamond, String remark, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.tmzz_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,diamonds = '%s',remark='%s',ipaddress ='%s'  where account = '%s'",
					diamond, remark, ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 释放天命之子初始
	public Boolean releaseAccTmzzEx(String account, String role, String remark, int diamonds, int red, int total,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.tmzz_test set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',diamonds='%d',red='%d'  where account = '%s'",
						ipaddress, diamonds, red, account);
				System.out.println(sqlString);
			} else if (role.equalsIgnoreCase("norole")) {
				sqlString = String.format(
						"update game.tmzz_test set acc_status = 'norole',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',diamonds='%d',red='%d'  where account = '%s'",
						ipaddress, diamonds, red, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				String url = "";
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.tmzz_test set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,%s remark='%s',ipaddress ='%s',diamonds='%d',red='%d',total='%d'  where account = '%s'",
						url, remark, ipaddress, diamonds, red, total, account);
				//
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 释放天命之子自抽
	public Boolean releaseAccTmzzZc(String account, String role, String remark, int diamonds, int red, int total,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.tmzz_zc set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',diamonds='%d',red='%d'  where account = '%s'",
						ipaddress, diamonds, red, account);
			} else {
				String[] sqlUrl = role.split(",");
				String url = "";
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.tmzz_zc set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,%s remark='%s',ipaddress ='%s',diamonds='%d',red='%d',total='%d'  where account = '%s'",
						url, remark, ipaddress, diamonds, red, total, account);
				//
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 释放梦幻模拟战账号
	public Boolean releaseAccMh(String account) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.mhmnz_acc set acc_status = 'feng',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP  where account = '%s'",
					account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/* 航海王 */
	public String getAccHhwssr() {
		// TODO Auto-generated method stub
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,serverid from game.hhw_ssr_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);

			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public Boolean lockAccHhwssr(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.hhw_ssr_account  set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccHhwssr(String account, String remark, int servcerid, String role, int total,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.hhw_ssr_account set acc_status = 'finished',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,serverid='%d' ,remark = '%s',ipaddress ='%s'  where account = '%s'",
						servcerid, remark, ipaddress, account);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + "=1,";
				}
				System.out.println(url);
				sqlString = String.format(
						"update game.hhw_ssr_account set acc_status = 'finished',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,serverid='%d' ,%s remark = '%s',ipaddress ='%s',total='%d'  where account = '%s'",
						servcerid, url, remark, ipaddress, total, account);
			}

			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public String getAccHhwzc() {
		// TODO Auto-generated method stub
		try {
			Statement statement = conn.createStatement();
			// String sqlString = "SELECT account,pwd,serverid from gd_account
			// where acc_status = 'unlock' and acc_remark is null order by
			// offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,serverid from game.hhw_zc_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);

			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public Boolean lockAccHhwzc(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.hhw_zc_account  set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s'  where account = '%s' ",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccHhwzc(String account, int zs, int ptj, int qzj, int serverid, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.hhw_zc_account set acc_status = 'finished',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,serverid = '%d',zs = '%d',ptj = '%d',qzj = '%d',ipaddress ='%s'  where account = '%s'",
					serverid, zs, ptj, qzj, ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/* 双生视界 */
	// 双生视界根据imei查询是否有锁定账号
	public int getLockCntByImeiSssj(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from sssj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取双生视界账号初始
	public String getAccountSssj() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,total from game.sssj_account where (acc_status = 'unlock' or acc_status='finished') order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定双生视界初始账号
	public Boolean lockAccountSssj(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.sssj_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 双生视界根据imei查询锁定账号信息
	public String getLockInfoByImeiSssj(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,total from sssj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean releaseAccCcy(String account, int iSer, int izs, int iqz, int ijy, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.ccy_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,zuanshi='%d',qiangzhe='%d',jingying='%d',serverid = '%d',ipaddress ='%s'  where account = '%s'",
					izs, iqz, ijy, iSer, ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	//释放双生视界账号
	public Boolean releaseAccSssj(String account,String remark,String role,int total,String ipaddress) {
		// TODO Auto-generated method stub 
				try {
					String sqlString = null;
					String url = "";
					if (role.equalsIgnoreCase("nil")) {
						sqlString = String.format(
								"update game.sssj_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
								remark, total, ipaddress,account);
						System.out.println(sqlString);
					} else if (role.isEmpty()) {
						sqlString = String.format(
								"update game.sssj_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
								remark, total, ipaddress,account);
						System.out.println(sqlString);
					} else {
						String[] sqlUrl = role.split(",");
						for (int index = 0; index < sqlUrl.length; index++) {
							url = url + sqlUrl[index] + ",";
						}

						sqlString = String.format(
								"update game.sssj_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s' where account = '%s'",
								remark, total, url, ipaddress, account);
						System.out.println(sqlString);
					}

					PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
					if (preparedStatement.executeUpdate() > 0) {
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return true;
	}
	/* 超次元 */

	/* bang bang */
	// Zy根据imei查询是否有锁定账号
	public int getLockCntByImeiZy(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from zy_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public int getLockCntByImeiBangSsrAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from bang_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取超次元账号初始
	public String getAccountZy() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zy_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			// String sqlString = "SELECT account from game.bang_account where
			// acc_status = 'unlock' and remark is null order by offlinedate asc
			// limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location =location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountBangSsrAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.bang_account_android where acc_status = 'unlock' and (remark is null or total >=1)  order by offlinedate asc limit 1";
			// String sqlString = "SELECT account from game.bang_account where
			// acc_status = 'unlock' and remark is null order by offlinedate asc
			// limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定超次元初始账号
	public Boolean lockAccountZy(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zy_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean lockAccountBangSsrAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.bang_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 超次元根据imei查询锁定账号信息
	public String getLockInfoByImeiZy(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select account,pwd from zy_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public String getLockInfoByImeiBangSsrAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account from bang_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean releaseAccBang(String account, int gc, int ry, int wz, int zs, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.bang_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,gangcai = '%d',ranyou = '%d',wuzi = '%d',zuanshi='%d',ipaddress ='%s'  where account = '%s'",
					gc, ry, wz, zs, ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public boolean releaseAccountBangSsr(String account, String remark, String role, int total, String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.bang_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.bang_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.bang_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean releaseAccountBangSsrAndroid(String account, String remark, String role, int total,
			String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.bang_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.bang_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.bang_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	/* bangbang */

	// 封印战纪根据imei查询是否有锁定账号
	public int getLockCntByImeiFyzj(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from fyzj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取封印战纪账号初始
	public String getAccountFyzj() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.fyzj_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定封印战纪初始账号
	public Boolean lockAccountFyzj(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.fyzj_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 封印战纪根据imei查询锁定账号信息
	public String getLockInfoByImeiFyzj(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,pwd from fyzj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放封印战纪账号
	public Boolean releaseAccFyzj(String account, int zs, int wqj, int fyz, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.fyzj_account set acc_status = 'unlock',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s',zs='%d',wqj='%d',fyz='%d'  where account = '%s'",
					ipaddress, zs, wqj, fyz, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 封印战纪错误账号
	public Boolean releaseAccFyzjError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.fyzj_account set acc_status = 'error',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public Boolean releaseAccTxError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.tx_account set acc_status = 'error',recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/* 星之轨迹国服 */
	public boolean uploadAccountXzgj(String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.xzgj_account  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 星之轨迹根据imei查询是否有锁定账号
	public int getLockCntByImeiXzgj(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from xzgj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取星之轨迹账号初始
	public String getAccountXzgj() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.xzgj_account where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定星之轨迹初始账号
	public Boolean lockAccountXzgj(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.xzgj_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 星之轨迹根据imei查询锁定账号信息
	public String getLockInfoByImeiXzgj(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select account from xzgj_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放星之轨迹账号
	public Boolean releaseAccXzgj(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.xzgj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.xzgj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.xzgj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	/* 星之轨迹国服 */
	public boolean uploadAccountXzgjAndroid(String account, String ipaddress) {
		try {
			String sqlString = String
					.format("insert into  game.xzgj_account_android  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// FFBE根据imei查询是否有锁定账号
	public int getLockCntByImeiFfbeAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from ffbe_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取星之轨迹账号初始
	public String getAccountFfbeAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,total from game.ffbe_account_android where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location =location+"#"+ rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定星之轨迹初始账号
	public Boolean lockAccountFfbeAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.ffbe_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 星之轨迹根据imei查询锁定账号信息
	public String getLockInfoByImeiFfbeAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,total from ffbe_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location =location+"#"+ rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放星之轨迹账号
	public Boolean releaseAccFfbeAndroid(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.ffbe_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.ffbe_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.ffbe_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	/* 上传All star账号 */
	public boolean uploadAccAllstar(String account, String ipaddress) {
		try {
			String sqlString = String.format(
					"insert into  game.as_account  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public boolean uploadAccPxzgZc(String account, String pwd, String ipaddress) {
		try {
			String sqlString = String
					.format("insert into  game.pxzg_account_zc  (account,pwd,ipaddress) values (?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			ps.setString(3, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 破晓战歌根据imei查询是否有锁定账号
	public int getLockCntByImeiPxzg(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from pxzg_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public int getLockCntByImeiAllStar(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from as_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取破晓战歌账号初始
	public String getAccountPxzg() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from game.pxzg_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountAllStar() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.as_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定破晓战歌初始账号
	public Boolean lockAccountPxzg(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.pxzg_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean lockAccountAllStar(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.as_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 破晓战歌根据imei查询锁定账号信息
	public String getLockInfoByImeiPxzg(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from pxzg_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public String getLockInfoByImeiAllStar(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account from as_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 最终幻想部分
	public String getLockInfoByImeiZzhx(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from zzhx_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean lockAccountZzhx(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zzhx_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public String getAccountZzhx() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zzhx_account where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public int getLockCntByImeiZzhx(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from zzhx_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public Boolean releaseAccZzhx(String account, String remark, String role, int total, String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.zzhx_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				// System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.zzhx_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				// System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.zzhx_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				// System.out.println(sqlString);
			}

			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 最终幻想自抽部分
	public String getLockInfoByImeiZzhxZc(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from zzhx_account_zc where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean lockAccountZzhxZc(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zzhx_account_zc set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public String getAccountZzhxZc() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zzhx_account_zc where acc_status = 'unlock' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public int getLockCntByImeiZzhxZc(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from zzhx_account_zc where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public Boolean releaseAccZzhxZc(String account, int zs, int shi, int xi, String ipaddress) {
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.zzhx_account_zc set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,zs='%d',shi='%d',xi='%d',ipaddress ='%s'  where account = '%s'",
					zs, shi, xi, ipaddress, account);
			// System.out.println(sqlString);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放破晓战歌账号
	public Boolean releaseAccPxzg(String account, String remark, String role, int total, int serverid,
			String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				// System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				// System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid, account);
				// System.out.println(sqlString);
			}
			/*
			 * sqlString = String.format(
			 * "update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,red='%d', ipaddress ='%s'  where account = '%s'"
			 * ,red, ipaddress, account);
			 */
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccAllStar(String account, String remark, String role, int total, int serverid, 
			String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.as_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				// System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.as_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				// System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.as_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid, account);
				// System.out.println(sqlString);
			}
			/*
			 * sqlString = String.format(
			 * "update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,red='%d', ipaddress ='%s'  where account = '%s'"
			 * ,red, ipaddress, account);
			 */
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccPxzgError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.pxzg_account set acc_status = 'error',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public Boolean releaseAccPxzgZcError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.pxzg_account_zc set acc_status = 'error',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	///////////////////////////// *苍蓝誓约*/
	// 苍蓝誓约根据imei查询是否有锁定账号
	public int getLockCntByImeiClsySsrAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from clsy_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取苍蓝誓约账号初始
	public String getAccountClsySsrAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.clsy_account where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountClsySsrAndroidEx() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.clsy_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定苍蓝誓约初始账号
	public Boolean lockAccountClsySsrAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.clsy_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 苍蓝誓约根据imei查询锁定账号信息
	public String getLockInfoByImeiClsySsrAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,pwd from clsy_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean releaseAccClsySsrErrorAndroid(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format(
					"update game.clsy_account set acc_status = 'error',recdate = CURRENT_TIMESTAMP,imei=NULL,offlinedate = CURRENT_TIMESTAMP,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			//
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public Boolean releaseAccClsySsrAndroid(String account, String remark, String role, int total, String ipaddress) {
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.clsy_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s' where account = '%s'",
						remark, total, ipaddress, account);
				// System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.clsy_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s' where account = '%s'",
						remark, total, ipaddress, account);
				// System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.clsy_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				// System.out.println(sqlString);
			}
			/*
			 * sqlString = String.format(
			 * "update game.pxzg_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,red='%d', ipaddress ='%s'  where account = '%s'"
			 * ,red, ipaddress, account);
			 */
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 重装战姬上传账号
	public boolean uploadAccZzzjSsrEx(String account, String remark, int total, String role) {
		try {
			String originalStr = role;

			String[] rr = role.split(",");
			String roleRet = "";
			if (rr.length > 0) {

				for (int i = 0; i < rr.length; i++) {
					roleRet = "1," + roleRet;
				}
				roleRet = roleRet.substring(0, roleRet.length() - 1);
			}
			String sqlString = String.format(
					"insert into  game.zzzj_account  (account,offlinedate,remark) values ('%s','2019-07-20','%s')",
					account, remark, total);
			PreparedStatement ps = conn.prepareStatement(sqlString);
			System.out.println(sqlString);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 重装战姬根据imei查询是否有锁定账号
	public int getLockCntByImeiZzzjSsr(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from zzzj_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取重装战姬账号初始
	public String getAccountZzzjSsr() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.zzzj_account where acc_status = 'unlock' and (remark='ssr' or total>0)  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountZzzjSsrSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.zzzj_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定重装战姬初始账号
	public Boolean lockAccountZzzjSssr(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zzzj_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 重装战姬根据imei查询锁定账号信息
	public String getLockInfoByImeiZzzjSsr(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select account from zzzj_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放重装战姬
	public Boolean releaseAccZzzjSsr(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.zzzj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.zzzj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.zzzj_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放重装战姬问题号
	public Boolean releaseAccZzzjError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.zzzj_account set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 上传重装战姬账号
	public boolean uploadAccZzzjSsrExAndroid(String account, String remark) {
		try {

			String sqlString = String.format(
					"insert into  game.zzzj_account_android  (account,offlinedate,remark) values ('%s','2019-07-20','%s')",
					account, remark);
			PreparedStatement ps = conn.prepareStatement(sqlString);
			System.out.println(sqlString);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 重装战姬根据imei查询是否有锁定账号
	public int getLockCntByImeiZzzjSsrAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from zzzj_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取重装战姬账号初始
	public String getAccountZzzjSsrAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.zzzj_account_android where acc_status = 'unlock' and (remark='ssr' or total>0)  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 获取重装战姬sr账号
	public String getAccountZzzjSrAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.zzzj_account_android where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定重装战姬初始账号
	public Boolean lockAccountZzzjSssrAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zzzj_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 重装战姬根据imei查询锁定账号信息
	public String getLockInfoByImeiZzzjSsrAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account from zzzj_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放重装战姬
	public Boolean releaseAccZzzjSsrAndroid(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.zzzj_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.zzzj_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.zzzj_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 闪耀暖暖
	// 上传账号
	public boolean uploadAccSynn(String account, String ipaddress) {
		try {

			String sqlString = String.format(
					"insert into  game.synn_account  (account,offlinedate,ipaddress,serverid) values ('%s','2019-07-20','%s',1)",
					account, ipaddress);
			PreparedStatement ps = conn.prepareStatement(sqlString);
			System.out.println(sqlString);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 造物法则释放账号
	public Boolean releaseAccZwfz(String account, String remark, String role, int total, String serverid,String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.zwfz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.zwfz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.zwfz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, url, ipaddress, serverid,account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccSynnNew(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.synn_account set acc_status = 'unlock',roleinfo='new',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.synn_account set acc_status = 'unlock',roleinfo='new',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.synn_account set acc_status = 'unlock',roleinfo='new',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 释放造物法则问题账号
	public Boolean releaseAccZwfzError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.zwfz_account set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccSynnPwd(String account, String pwd, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.synn_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,ipaddress ='%s',pwd='%s'  where account = '%s'",
					ipaddress, pwd, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 造物法则根据imei查询是否有锁定账号
	public int getLockCntByImeiZwfz(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from zwfz_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取造物法则账号
	public String getAccountZwfz() {
		try {
			Statement statement = conn.createStatement();
			//String sqlString = "SELECT account,pwd from game.zwfz_account where acc_status = 'unlock' and (total is null or total=0 ) and numtype is null  order by offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,total from game.zwfz_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
				location = location+"#"+ rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountZwfzWz() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zwfz_account where (acc_status = 'unlock' or acc_status='finished' ) and remark like '%未知%' order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountZwfzTest() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zwfz_account where acc_status = 'unlock' and numtype='4G' and offlinedate < curdate() order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountZwfzWifi() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.zwfz_account where acc_status = 'unlock' and numtype='wifi' and offlinedate < curdate() order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// 获取闪耀暖暖问题账号
	public String getAccountSynnError() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.synn_account where acc_status = 'unlock' and account=pwd  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定造物法则账号
	public Boolean lockAccountZwfz(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zwfz_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 造物法则根据imei查询锁定账号信息
	public String getLockInfoByImeiZwfz(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select account,pwd,total from zwfz_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location +"#" + rs.getString("pwd");
				location = location +"#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 精灵食肆start
	// 上传精灵食肆初始账号
	public boolean uploadAccountJlss(String account, String pwd, String ipaddress) {
		try {
			String sqlString = String
					.format("insert into  game.jlss_account_android  (account,pwd,ipaddress) values (?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, pwd);
			ps.setString(3, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 精灵食肆释放账号
	public Boolean releaseAccJlss(String account, String remark, String role, int total, int serverid, int lingyu,
			int putong, int xianding, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.jlss_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.jlss_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d',lingyu='%d',putong='%d',xianding='%d'   where account = '%s'",
						remark, total, ipaddress, serverid, lingyu, putong, xianding, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.jlss_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d',lingyu='%d',putong='%d',xianding='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid, lingyu, putong, xianding, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	public Boolean releaseAccJlssError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.jlss_account set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 精灵食肆根据imei查询是否有锁定账号
	public int getLockCntByImeiJlss(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from jlss_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取精灵食肆账号
	public String getAccountJlss() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from game.jlss_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定精灵食肆账号
	public Boolean lockAccountJlss(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.jlss_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 精灵食肆根据imei查询锁定账号信息
	public String getLockInfoByImeiJlss(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from jlss_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 精灵食肆根据imei查询是否有锁定账号
	public int getLockCntByImeiJlssAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from jlss_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取精灵食肆账号
	public String getAccountJlssAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from game.jlss_account_android where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定精灵食肆账号
	public Boolean lockAccountJlssAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.jlss_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 精灵食肆根据imei查询锁定账号信息
	public String getLockInfoByImeiJlssAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from jlss_account_android where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放精灵食肆安卓账号
	public Boolean releaseAccJlssAndroid(String account, String remark, String role, int total, int serverid,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.jlss_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.jlss_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.jlss_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 精灵食肆释放安卓错误账号
	public Boolean releaseAccJlssErrorAndroid(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			sqlString = String.format(
					"update game.jlss_account_android set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	/* 精灵食肆结束 */

	// 上传造物法则初始账号
	public boolean uploadAccountZwfz(String uid, String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.zwfz_account  (uid,account,ipaddress) values (?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, uid);
			ps.setString(2, account);
			ps.setString(3, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 路人超能释放账号
	public Boolean releaseAccLrcn(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.cnlr_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.cnlr_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.cnlr_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 路人超能问题号
	public Boolean releaseAccLrcnError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;

			sqlString = String.format(
					"update game.cnlr_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP, ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 路人超能根据imei查询是否有锁定账号
	public int getLockCntByImeiLrcn(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from cnlr_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取路人超能账号
	public String getAccountLrcn() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.cnlr_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountLrcnSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.cnlr_account where acc_status = 'unlock' and remark is not null  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定路人超能账号
	public Boolean lockAccountLrcn(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.cnlr_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 路人超能根据imei查询锁定账号信息
	public String getLockInfoByImeiLrcn(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,pwd from cnlr_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 路人超能结束
	// 文豪迷犬ios开始
	// 上传文豪迷犬初始账号
	public boolean uploadAccountWhmq(String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.whmq_account  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 文豪迷犬ios释放账号
	public Boolean releaseAccWhmq(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.whmq_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.whmq_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.whmq_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 文豪迷犬ios问题号
	public Boolean releaseAccWhmqError(String account, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;

			sqlString = String.format(
					"update game.whmq_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP, ipaddress ='%s'  where account = '%s'",
					ipaddress, account);
			System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 文豪迷犬ios根据imei查询是否有锁定账号
	public int getLockCntByImeiWhmq(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from whmq_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取文豪迷犬ios账号
	public String getAccountWhmq() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.whmq_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	public String getAccountWhmqSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.whmq_account where acc_status = 'unlock' and total >4  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定文豪迷犬ios账号
	public Boolean lockAccountWhmq(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.whmq_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 文豪迷犬ios根据imei查询锁定账号信息
	public String getLockInfoByImeiWhmq(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select account from whmq_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 文豪迷犬ios结束

	// 阴阳师根据imei查询是否有锁定账号
	public int getLockCntByImeiYys(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from yys_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取阴阳师账号
	public String getAccountYys() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.yys_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定阴阳师账号
	public Boolean lockAccountYys(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.yys_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 阴阳师根据imei查询锁定账号信息
	public String getLockInfoByImeiYys(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from yys_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	public Boolean releaseAccYys(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.yys_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s' where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.yys_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s' where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.yys_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 安卓妃十三学院
	// 安卓妃十三上传账号
	public boolean uploadAccountFssAndroid(String account, String ipaddress) {
		try {
			String sqlString = String
					.format("insert into  game.fssxy_account_android  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 安卓妃十三学院根据imei查询是否有锁定账号
	public int getLockCntByImeiFssAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from fssxy_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取安卓妃十三学院账号初始
	public String getAccountFssAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.fssxy_account_android where acc_status = 'unlock' and offlinedate < DATE_ADD(CURDATE(),INTERVAL 4 hour)   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定安卓妃十三学院初始账号
	public Boolean lockAccountFssAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.fssxy_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 安卓妃十三根据imei查询锁定账号信息
	public String getLockInfoByImeiFssAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account from fssxy_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	//安卓查看是否存在相同游客id
	public int getLockCntByUidFssAndroid(String account) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from fssxy_account_android where account='%s'", account);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	// 家庭教师ios释放账号
	public Boolean releaseAccJtjs(String account, String remark, String role, int total, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.jtjs_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.jtjs_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.jtjs_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//长安幻世绘上传账号
	public boolean uploadAccountCahsh(String account, String ipaddress) {
		try {
			String sqlString = String.format("insert into  game.cahsh_account  (account,ipaddress) values (?,?)");
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, ipaddress);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// IOS长安根据imei查询是否有锁定账号
	public int getLockCntByImeiCahsh(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from cahsh_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	//IOS查看是否存在相同游客id
	public int getLockCntByUidCahsh(String account) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from cahsh_account where account='%s'", account);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	public int getLockCntByUidSgz(String account) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from sgz_account where account='%s'", account);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	// 获取IOS长安账号初始
	public String getAccountCahsh() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account from game.cahsh_account where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定IOS长安初始账号
	public Boolean lockAccountCahsh(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.cahsh_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// IOS长安根据imei查询锁定账号信息
	public String getLockInfoByImeiCahsh(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account from cahsh_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// IOS长安释放账号
	public Boolean releaseAccCahsh(String account, String remark, String role, int total,int serverid, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.cahsh_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s' ,serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.cahsh_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.cahsh_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid,account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// IOS家庭教师根据imei查询是否有锁定账号自抽号
	public int getLockCntByImeiJtjs(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from jtjs_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取IOS家庭教师账号
	public String getAccountJtjs() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,serverid from game.jtjs_account where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
				location = location+"#"+ rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	// 锁定IOS家庭教师账号
	public Boolean lockAccountJtjs(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.jtjs_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// IOS家庭教师根据imei查询锁定账号信息
	public String getLockInfoByImeiJtjs(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("select account,pwd,serverid from jtjs_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
				location = location+"#"+ rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// IOS妃十三释放账号自抽
	public Boolean releaseAccHldzzZc(String account, int serverid, int diamonds, int tickets,String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			
			sqlString = String.format("update game.hldzz_account_zc set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,serverid = '%d',diamonds='%d',ipaddress ='%s',tickets='%d'  where account = '%s'",
					serverid, diamonds, ipaddress, tickets, account);
				System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 启源女神根据imei查询是否有锁定账号
	public int getLockCntByImeiQyns(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from qyns_account where   acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	// 启源女神安卓根据imei查询是否有锁定账号
	public int getLockCntByImeiQynsAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from qyns_account_android where   acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取启源女神账号
	public String getAccountQyns() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,total from game.qyns_account where  acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// 获取启源女神账号
	public String getAccountQynsAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.qyns_account_android where acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// ExosHero根据imei查询是否有锁定账号
	public int getLockCntByImeiHero(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from hero_account where total is null and  acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	public int getLockCntByImeiHeroSign(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String
					.format("SELECT count(*) as cnt from hero_account where  total>=0 and  acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	// 获取ExosHero账号
	public String getAccountHero() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,total from game.hero_account where total is null and acc_status = 'unlock'  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountHeroSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,total from game.hero_account where (acc_status = 'unlock' or acc_status='finished') and total>=0  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountVgameSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.xlsj_account where acc_status = 'unlock' and remark is not null  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// 锁定Hero账号
	public Boolean lockAccountHero(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.hero_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	// 锁定启源女神账号
	public Boolean lockAccountQyns(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.qyns_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	// 锁定启源女神安卓账号
	public Boolean lockAccountQynsAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.qyns_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	// Hero根据imei查询锁定账号信息
	public String getLockInfoByImeiHero(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,total from hero_account where total is null and acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	//启源女神根据imei查询锁定账号信息
	public String getLockInfoByImeiQyns(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,total from qyns_account where  acc_status = 'lock' and imei='%s' limit 1", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");		
				location = location + "#" + rs.getString("total");				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	//启源女神安卓根据imei查询锁定账号信息
	public String getLockInfoByImeiQynsAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd from qyns_account_android where  acc_status = 'lock' and imei='%s' limit 1 ", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	// Hero根据imei查询锁定账号信息
	public String getLockInfoByImeiHeroSign(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,total from hero_account where total >=0 and acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("total");				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	public Boolean releaseAccHero(String account, String remark, String role, int total, String serverid,String ipaddress) {
		// TODO Auto-generated method stub 
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.hero_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.hero_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.hero_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, url, ipaddress,serverid, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	public Boolean releaseAccHeroRe(String account,String ipaddress) {
		// TODO Auto-generated method stub 
		try {
			String sqlString = null;

				sqlString = String.format(
						"update game.hero_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,p001=null,p002=null,p003=null,p004=null,p005=null,p006=null,p007=null,p008=null,p009=null,p010=null,p011=null,p012=null,p013=null,p014=null,p015=null,p016=null,p017=null,p018=null,p019=null,p020=null,p021=null,p022=null,p023=null,p024=null,p025=null ,ipaddress ='%s' where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	public Boolean releaseAccQyns(String account, String remark, String role, int total, String serverid,String fwq,String ipaddress) {
		// TODO Auto-generated method stub 
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.qyns_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s',fwq='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,fwq,account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.qyns_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s' ,fwq='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,fwq,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.qyns_account set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%s' ,fwq='%s'  where account = '%s'",
						remark, total, url, ipaddress,serverid, fwq,account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	public Boolean releaseAccQynsAndroid(String account, String remark, String role, int total, String serverid,String ipaddress) {
		// TODO Auto-generated method stub 
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.qyns_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.qyns_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.qyns_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, url, ipaddress,serverid, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	// 造物法则根据imei查询是否有锁定账号
	public int getLockCntByImeiZwfzAndroid(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from zwfz_account_android where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取造物法则安卓账号
	public String getAccountZwfzAndroid() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd,total from game.zwfz_account_android where acc_status = 'unlock'   order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountSgzSignAndroid() {
		try {
			Statement statement = conn.createStatement();
			//String sqlString = "SELECT account,pwd,serverid from game.sgz_account_android where acc_status = 'unlock' and total >0 and serverid > 119  order by offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,serverid from game.sgz_account_android where (acc_status = 'finished' or acc_status='unlock' ) and ((p001=1 or p002=1 or p003=1 or p004=1 or p006=1 or p007=1 or p012=1 or p015=1 or p016=1 or p017=1 or p018=1 or p019=1 or p022=1 or p039=1 or p042=1 or p044=1)) and offlinedate < DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -1 hour) AND offlinedate > DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -3.5 hour)  order by offlinedate asc  limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// 锁定造物法则安卓账号
	public Boolean lockAccountZwfzAndroid(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.zwfz_account_android set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 造物法则安卓根据imei查询锁定账号信息
	public String getLockInfoByImeiZwfzAndroid(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,total from zwfz_account_android where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("total");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	// 释放造物法则安卓账号
	public Boolean releaseAccZwfzAndroid(String account, String remark, String role, int total,
			String serverid,String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.zwfz_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress,serverid, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.zwfz_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, ipaddress, serverid,account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.zwfz_account_android set acc_status = 'unlock',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%s'  where account = '%s'",
						remark, total, url, ipaddress, serverid,account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	
	//上传三国志ios
	public Boolean uploadAccountSgz(String account,String pwd, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format("insert into game.sgz_account (account,pwd,ipaddress) values ('%s','%s','%s')  ",
					account, pwd,ipaddress);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}	
	// 三国志根据imei查询是否有锁定账号
	public int getLockCntByImeiSgz(String imei) {//
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"SELECT count(*) as cnt from sgz_account where acc_status = 'lock' and imei='%s'", imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	// 获取三国志ios账号
	public String getAccountSgz() {
		try {
			Statement statement = conn.createStatement();
			//String sqlString = "SELECT account,pwd,serverid from game.sgz_account where (acc_status = 'finished' or acc_status='unlock' ) and ((p001=1 or p002=1 or p003=1 or p004=1 or p006=1 or p007=1 or p012=1 or p015=1 or p016=1 or p017=1 or p018=1 or p019=1 or p022=1 or p039=1 or p042=1 or p044=1)) and offlinedate < DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -2 hour) AND offlinedate > DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -3.5 hour)  order by offlinedate asc limit 1";
			String sqlString = "SELECT account,pwd,serverid from game.sgz_account where (acc_status = 'finished' or acc_status='unlock' ) and ((p001=1 or p002=1 or p003=1 or p004=1 or p006=1 or p007=1 or p012=1 or p015=1 or p016=1 or p017=1 or p022=1)) and offlinedate < DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -2 hour)   order by offlinedate asc limit 1";
			//String sqlString = "SELECT account,pwd,serverid from game.sgz_account where (acc_status = 'finished' or acc_status='unlock' ) and total>0  order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location+"#"+ rs.getString("pwd");
				location = location+"#"+ rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	public String getAccountSgzSign() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = "SELECT account,pwd from game.sgz_account where acc_status = 'unlock' and (total <1 or total is null ) order by offlinedate asc limit 1";
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");			
				location = location+"#"+ rs.getString("pwd");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	// 锁定三国志ios账号
	public Boolean lockAccountSgz(String account, String ipaddress, String imei) {
		try {
			String sqlString = String.format(
					"update game.sgz_account set acc_status = 'lock', recdate = CURRENT_TIMESTAMP, ipaddress='%s',imei='%s'  where account = '%s' ",
					ipaddress, imei, account);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// 三国志ios根据imei查询锁定账号信息
	public String getLockInfoByImeiSgz(String imei) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select account,pwd,serverid from sgz_account where acc_status = 'lock' and imei='%s'",
					imei);
			System.out.println(sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("account");
				location = location + "#" + rs.getString("pwd");
				location = location + "#" + rs.getString("serverid");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	
	//释放三国志ios
	public Boolean releaseAccSgz(String account, String remark, String role, int total, int serverid,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, ipaddress, serverid, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s',serverid='%d'  where account = '%s'",
						remark, total, url, ipaddress, serverid, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	public Boolean releaseAccSgzEx(String account, String remark, String role, int total,
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
			String url = "";
			if (role.equalsIgnoreCase("nil")) {
				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else if (role.isEmpty()) {
				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP,remark = '%s',total='%d', ipaddress ='%s'  where account = '%s'",
						remark, total, ipaddress, account);
				System.out.println(sqlString);
			} else {
				String[] sqlUrl = role.split(",");
				for (int index = 0; index < sqlUrl.length; index++) {
					url = url + sqlUrl[index] + ",";
				}

				sqlString = String.format(
						"update game.sgz_account set acc_status = 'finished',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP ,remark = '%s',total='%s',%s ipaddress ='%s'  where account = '%s'",
						remark, total, url, ipaddress, account);
				System.out.println(sqlString);
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	//释放三国志ios过期
	public Boolean releaseAccSgzError(String account, 
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;


				sqlString = String.format(
						"update game.sgz_account set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	//释放启源女神安卓错误
	public Boolean releaseAccQynsAndroidError(String account, 
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;


				sqlString = String.format(
						"update game.qyns_account_android set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	public Boolean releaseAccQynsError(String account, 
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;


				sqlString = String.format(
						"update game.qyns_account set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//释放造物法则安卓异常
	public Boolean releaseAccZwfzAndroidError(String account, 
			String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = null;
				sqlString = String.format(
						"update game.zwfz_account_android set acc_status = 'error',imei=NULL,recdate = CURRENT_TIMESTAMP,offlinedate = CURRENT_TIMESTAMP , ipaddress ='%s'where account = '%s'",
						ipaddress, account);
				System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	/*通过游戏id取礼包码*/
	public String getGiftIdByGameId(String gameid) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select giftcode from game.gift_code_account where gameid = '%s'",
					gameid);
			System.out.println("getScriptByDeviceid:" + sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("giftcode");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}	
	/* 获取手机设备信息 */
	public Boolean uploadDevice(String deviceid, String ipaddress) {
		// TODO Auto-generated method stub
		try {
			String sqlString = String.format("insert into game.device_info (deviceid,ipaddress) values ('%s','%s')  ",
					deviceid, ipaddress);
			System.out.println(sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/* 通过设备号获取运行的脚本名称 */
	public String getScriptByDeviceid(String deviceId) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select script_name from device.device_info where deviceid = '%s'",
					deviceId);
			System.out.println("getScriptByDeviceid:" + sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("script_name");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	/* 通过脚本名称获取版本号检测是否需要更新 */
	public String getVersionByScriptName(String scriptName) {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format(
					"select script_version from device.version_script_info where script_name = '%s'", scriptName);
			System.out.println("getVersionByScriptName:" + sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("script_version");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}

	/* 上传运行日志 */
	public Boolean uploadLogHttp(String deviceid, String scriptName, String runLog) {
		try {
			String sqlString = String.format(
					"update device.device_info set remark = '%s' where deviceid = '%s'",
					 runLog, deviceid);
			System.out.println("uploadLogHttp:" + sqlString);
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	// 获取名字
	public String getRndNameCN() {
		try {
			Statement statement = conn.createStatement();
			String sqlString = String.format("select name from game.name_info where id = 1");
			// System.out.println("getVersionByScriptName:" + sqlString);
			rs = statement.executeQuery(sqlString);
			while (rs.next()) {
				location = rs.getString("name");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
}
