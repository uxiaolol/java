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

	// ��ȡͻϮ
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

	// ��ȡħ������ios�˺�
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

	// ��ȡ�ӳ�Сһ�˺�
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

	// ��ȡ������֮���˺�
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

	// ��ȡ���շ����˺��Գ�
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

	// ��ȡ���շ����˺ų�ʼ
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

	// ��ȡ���շ����˺ų�ʼǩ��
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

	// ��ȡ���շ����˺ų�ʼ
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

	// ����״̬���շ���
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

	// �ָ�״̬���շ���
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

	// �ָ�״̬���շ���
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

	// ��ȡ�Ҵ��˺�
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

	// ��ȡqq�ɳ��˺�
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

	// �����ĺ���Ȯ�˺�
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

	// �ͷ�saomd�ɳ��˺�
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

	// �ͷ�ħ������ios�˺�
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

	// ��ȡ�ű��汾��--��ݸ
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

	// ��ȡ��������˺�
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

	// ��ȡUrl���ӵ�ַ
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

	// ��ȡ����3��֤���˺�
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

	// ��ȡ�Ҵﰲ׿
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

	// ��ȡ�Ҵﰲ׿����
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

	// ��׿�Ҵ����imei��ѯ�����˺���Ϣ
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

	// ���շ��۸���imei��ѯ�����˺���Ϣ
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

	// ��׿�Ҵ����imei��ѯ�Ƿ��������˺�
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

	// ���շ��۸���imei��ѯ�Ƿ��������˺�
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

	// ��ȡʱ֮��
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

	// ��������3��ű�
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

	// ����������֮���˺�
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

	// �����ӳ�Сһ�˺�
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

	// �����컪�˺�
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

	// �����Ҵ��˺�
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

	// �������շ����Գ��˺�
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

	// �������շ��۳�ʼ�˺�
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

	// �����Ҵﰲ׿�˺�
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

	// �����Ҵﰲ׿�˺Ų���
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

	// ����ͻϮ�˺�
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

	// ����ħ������IOS
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

	// ����Url���ӵ�ַ
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

	// ����ʳ֮��Լ�˺ű�
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

	// ��������¼�˺ű�
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

	// �ϴ�����֮�ӳ�ʼ�˺�
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

	// �ϴ�����֮�ӳ�ʼ�˺�
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

	// �ϴ�����֮���Գ��˺�
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

	// �ϴ������˺�
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

	// �ϴ������˺�
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

	// �ϴ�����Ԫ
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

	// �ϴ����շ����˺�
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

	// �ϴ����շ��۳�ʼ�˺�
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

	// �ϴ����շ��۳�ʼ�˺ų�����
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

	// �ϴ�����¼�˺�
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

	// �ϴ���֮��Ԫ�˺�
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

	// �ͷű���3�˺�
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

	// �ͷŰ�����֮���˺�
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

	// �ͷŶӳ�Сһ�˺�
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

	// �ͷ��컪�˺�
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

	// �ͷ����շ����Գ�
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

	// ���շ��ۿպ�
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

	// ���շ���ǩ��
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

	// �ͷ����շ��۳�ʼ
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

	// �ͷŸҴ��˺�
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

	// �ͷű���3�˺�
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

	// �ͷ�ʮ��ս���˺�
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

	// �ͷű���3�˺���֤��
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

	// �ͷ�ʱ֮���˺�
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

	// �ͷŸҴﰲ׿�˺�
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

	// �ͷŰ�׿�Ҵ����
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

	// ɾ����֮��Ԫ�˺��������
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

	// ɾ������3�˺�
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

	// ��������ʷʫ�˺�
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

	// �ͷŵ���ʷʫ�˺�
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

	/* �λ�ģ��ս */
	// ��ȡ����֮���˺�
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

	// ��ȡ����֮���Գ��
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

	// ȡ�λ�ģ��ս����vpn�˺�
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

	// ��������֮���˺�
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

	// ��������֮���Գ��
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

	// �ͷ�����֮���˺�
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

	// �ͷ�����֮�ӳ�ʼ
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

	// �ͷ�����֮���Գ�
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

	// �ͷ��λ�ģ��ս�˺�
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

	/* ������ */
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

	/* ˫���ӽ� */
	// ˫���ӽ����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ˫���ӽ��˺ų�ʼ
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

	// ����˫���ӽ��ʼ�˺�
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

	// ˫���ӽ����imei��ѯ�����˺���Ϣ
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
	//�ͷ�˫���ӽ��˺�
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
	/* ����Ԫ */

	/* bang bang */
	// Zy����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����Ԫ�˺ų�ʼ
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

	// ��������Ԫ��ʼ�˺�
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

	// ����Ԫ����imei��ѯ�����˺���Ϣ
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

	// ��ӡս�͸���imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��ӡս���˺ų�ʼ
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

	// ������ӡս�ͳ�ʼ�˺�
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

	// ��ӡս�͸���imei��ѯ�����˺���Ϣ
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

	// �ͷŷ�ӡս���˺�
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

	// ��ӡս�ʹ����˺�
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

	/* ��֮�켣���� */
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

	// ��֮�켣����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��֮�켣�˺ų�ʼ
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

	// ������֮�켣��ʼ�˺�
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

	// ��֮�켣����imei��ѯ�����˺���Ϣ
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

	// �ͷ���֮�켣�˺�
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

	/* ��֮�켣���� */
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

	// FFBE����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��֮�켣�˺ų�ʼ
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

	// ������֮�켣��ʼ�˺�
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

	// ��֮�켣����imei��ѯ�����˺���Ϣ
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

	// �ͷ���֮�켣�˺�
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

	/* �ϴ�All star�˺� */
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

	// ����ս�����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����ս���˺ų�ʼ
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

	// ��������ս���ʼ�˺�
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

	// ����ս�����imei��ѯ�����˺���Ϣ
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

	// ���ջ��벿��
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

	// ���ջ����Գ鲿��
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

	// �ͷ�����ս���˺�
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

	///////////////////////////// *������Լ*/
	// ������Լ����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ������Լ�˺ų�ʼ
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

	// ����������Լ��ʼ�˺�
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

	// ������Լ����imei��ѯ�����˺���Ϣ
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

	// ��װս���ϴ��˺�
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

	// ��װս������imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��װս���˺ų�ʼ
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

	// ������װս����ʼ�˺�
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

	// ��װս������imei��ѯ�����˺���Ϣ
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

	// �ͷ���װս��
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

	// �ͷ���װս�������
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

	// �ϴ���װս���˺�
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

	// ��װս������imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��װս���˺ų�ʼ
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

	// ��ȡ��װս��sr�˺�
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

	// ������װս����ʼ�˺�
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

	// ��װս������imei��ѯ�����˺���Ϣ
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

	// �ͷ���װս��
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

	// ��ҫůů
	// �ϴ��˺�
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

	// ���﷨���ͷ��˺�
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

	// �ͷ����﷨�������˺�
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

	// ���﷨�����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ���﷨���˺�
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
			String sqlString = "SELECT account,pwd from game.zwfz_account where (acc_status = 'unlock' or acc_status='finished' ) and remark like '%δ֪%' order by offlinedate asc limit 1";
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
	// ��ȡ��ҫůů�����˺�
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

	// �������﷨���˺�
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

	// ���﷨�����imei��ѯ�����˺���Ϣ
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

	// ����ʳ��start
	// �ϴ�����ʳ����ʼ�˺�
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

	// ����ʳ���ͷ��˺�
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

	// ����ʳ������imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����ʳ���˺�
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

	// ��������ʳ���˺�
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

	// ����ʳ������imei��ѯ�����˺���Ϣ
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

	// ����ʳ������imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����ʳ���˺�
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

	// ��������ʳ���˺�
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

	// ����ʳ������imei��ѯ�����˺���Ϣ
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

	// �ͷž���ʳ����׿�˺�
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

	// ����ʳ���ͷŰ�׿�����˺�
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

	/* ����ʳ������ */

	// �ϴ����﷨���ʼ�˺�
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

	// ·�˳����ͷ��˺�
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

	// ·�˳��������
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

	// ·�˳��ܸ���imei��ѯ�Ƿ��������˺�
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

	// ��ȡ·�˳����˺�
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

	// ����·�˳����˺�
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

	// ·�˳��ܸ���imei��ѯ�����˺���Ϣ
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

	// ·�˳��ܽ���
	// �ĺ���Ȯios��ʼ
	// �ϴ��ĺ���Ȯ��ʼ�˺�
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

	// �ĺ���Ȯios�ͷ��˺�
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

	// �ĺ���Ȯios�����
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

	// �ĺ���Ȯios����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ�ĺ���Ȯios�˺�
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

	// �����ĺ���Ȯios�˺�
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

	// �ĺ���Ȯios����imei��ѯ�����˺���Ϣ
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

	// �ĺ���Ȯios����

	// ����ʦ����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����ʦ�˺�
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

	// ��������ʦ�˺�
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

	// ����ʦ����imei��ѯ�����˺���Ϣ
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

	// ��׿��ʮ��ѧԺ
	// ��׿��ʮ���ϴ��˺�
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

	// ��׿��ʮ��ѧԺ����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��׿��ʮ��ѧԺ�˺ų�ʼ
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

	// ������׿��ʮ��ѧԺ��ʼ�˺�
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

	// ��׿��ʮ������imei��ѯ�����˺���Ϣ
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
	//��׿�鿴�Ƿ������ͬ�ο�id
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
	// ��ͥ��ʦios�ͷ��˺�
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
	//�����������ϴ��˺�
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

	// IOS��������imei��ѯ�Ƿ��������˺�
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

	//IOS�鿴�Ƿ������ͬ�ο�id
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
	// ��ȡIOS�����˺ų�ʼ
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

	// ����IOS������ʼ�˺�
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

	// IOS��������imei��ѯ�����˺���Ϣ
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

	// IOS�����ͷ��˺�
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

	// IOS��ͥ��ʦ����imei��ѯ�Ƿ��������˺��Գ��
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

	// ��ȡIOS��ͥ��ʦ�˺�
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

	// ����IOS��ͥ��ʦ�˺�
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

	// IOS��ͥ��ʦ����imei��ѯ�����˺���Ϣ
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

	// IOS��ʮ���ͷ��˺��Գ�
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

	// ��ԴŮ�����imei��ѯ�Ƿ��������˺�
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
	// ��ԴŮ��׿����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ��ԴŮ���˺�
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
	// ��ȡ��ԴŮ���˺�
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
	// ExosHero����imei��ѯ�Ƿ��������˺�
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
	// ��ȡExosHero�˺�
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
	// ����Hero�˺�
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
	// ������ԴŮ���˺�
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
	// ������ԴŮ��׿�˺�
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
	// Hero����imei��ѯ�����˺���Ϣ
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
	//��ԴŮ�����imei��ѯ�����˺���Ϣ
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
	//��ԴŮ��׿����imei��ѯ�����˺���Ϣ
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
	// Hero����imei��ѯ�����˺���Ϣ
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
	// ���﷨�����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ���﷨��׿�˺�
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
	// �������﷨��׿�˺�
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

	// ���﷨��׿����imei��ѯ�����˺���Ϣ
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

	// �ͷ����﷨��׿�˺�
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
	
	//�ϴ�����־ios
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
	// ����־����imei��ѯ�Ƿ��������˺�
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

	// ��ȡ����־ios�˺�
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
	// ��������־ios�˺�
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

	// ����־ios����imei��ѯ�����˺���Ϣ
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
	
	//�ͷ�����־ios
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
	//�ͷ�����־ios����
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
	//�ͷ���ԴŮ��׿����
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
	//�ͷ����﷨��׿�쳣
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
	/*ͨ����Ϸidȡ�����*/
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
	/* ��ȡ�ֻ��豸��Ϣ */
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

	/* ͨ���豸�Ż�ȡ���еĽű����� */
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

	/* ͨ���ű����ƻ�ȡ�汾�ż���Ƿ���Ҫ���� */
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

	/* �ϴ�������־ */
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

	// ��ȡ����
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
