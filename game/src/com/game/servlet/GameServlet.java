package com.game.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.Scanner;

import com.mysql.connect.MysqlConnect;

import com.mysql.jdbc.Connection;
import java.net.URLDecoder;

import java.net.URLEncoder;

@SuppressWarnings("serial")
public class GameServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GameServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	// ��õ�ǰϵͳʱ��
	public String getNowtimeTwo() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");// ��ʽ��Сд������
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html��charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("Remote Addr: " + request.getRemoteAddr() + " time:" + getNowtimeTwo()); // ��ÿͻ��˵�ip��ַ
		try {
			String action, ipaddress;

			// String
			// reqUrl=request.getRequestURL().toString();//http://localhost:8080/game/servlet/game
			// ����MysqlConnect��
			MysqlConnect connect = new MysqlConnect();
			// ����mysql�����ݿ�����
			Connection connection = (Connection) connect.ConnectMysql();
			action = request.getParameter("action");
			ipaddress = request.getRemoteAddr();
			// http�������Ϊ�շ��ش���
			if (action == null) {
				out.write("action error");
			}
			//
			else if (action.equalsIgnoreCase("picTest")) {
				String picContent = request.getParameter("picContent");
				System.out.println(picContent);
			}
			// ��ȡ�ű��汾��
			else if (action.equalsIgnoreCase("getScriptsVersion")) {
				String strScriptsName = request.getParameter("scrpitsName");
				String strVersion = connect.getScrpitsVersion(strScriptsName);
				out.write(strVersion);
			}
			// ��ȡͻϮ�˺�
			else if (action.equalsIgnoreCase("getAccTx")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountTx();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountTx(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// ��ȡħ������ios�˺�
			else if (action.equalsIgnoreCase("getAccMklyIOS")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountMklyIOS();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountMklyIOS(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// ��ȡħ������׿�˺�
			else if (action.equalsIgnoreCase("getAccMklyAndroid")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountMklyAndroid();
					String oldaccount = null;
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountMklyAndroid(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// ��ȡios�Ҵ��˺�
			else if (action.equalsIgnoreCase("getaccgd")) {
				String account = connect.getAccountgd();
				String oldaccount = null;
				// connect.lockAccountbh(account,ipaddress);
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockAccountgd(acc[0], ipaddress);
					out.write(account);
				}
			}
			// ��ȡSAO-MD�˺�
			else if (action.equalsIgnoreCase("getAccSMD")) {
				synchronized (this.getClass()) {
					String account = connect.getAccounSMD();
					String oldaccount = null;
					// connect.lockAccountbh(account,ipaddress);
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountSMD(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// �ͷŵ���̨���˺�
			else if (action.equalsIgnoreCase("releaseAccSMD")) {
				String account = request.getParameter("account");
				int diamond = Integer.parseInt(request.getParameter("diamond"));
				Boolean bRet = connect.releaseAccountSMD(account, diamond, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ�ħ������ios�˺�
			else if (action.equalsIgnoreCase("releaseAccMklyIOS")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccountMklyIOS(account, remark, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡ���շ����˺��Գ�
			else if (action.equalsIgnoreCase("getAccMrfzZc")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountMrfzZc();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountMrfzZc(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// ��ȡ���շ����˺ų�ʼ
			else if (action.equalsIgnoreCase("getAccMrfzSsr")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiMrfzSsr(imei);
					if (count == 0) {
						String account = connect.getAccountMrfzSsr();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountMrfzSsr(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiMrfzSsr(imei);
						out.write(account);
					}
				}
			}
			// ��ȡ���շ����˺ų�ʼǩ��
			else if (action.equalsIgnoreCase("getAccMrfzSsrSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiMrfzSsr(imei);
					if (count == 0) {
						String account = connect.getAccountMrfzSsrSign();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountMrfzSsr(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiMrfzSsr(imei);
						out.write(account);
					}
				}
			}

			// ��ȡ���շ����˺ų�ʼ
			else if (action.equalsIgnoreCase("getAccMrfzSsrByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					String account = connect.getAccountMrfzSsrByUid(uid);
					if (account == null) {
						out.write("null");
					} else {

						out.write(account);
					}
				}
			}
			// ������״̬
			else if (action.equalsIgnoreCase("SoldMrfzSsrByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.SoldMrfzSsrByUid(uid);
					if (ret) {
						out.write("ok");
					} else {
						out.write("error");
					}
				}
			}
			// �ûָ�״̬
			else if (action.equalsIgnoreCase("RecMrfzSsrByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.RecMrfzSsrByUid(uid);
					if (ret) {
						out.write("ok");
					} else {

						out.write("error");
					}
				}
			}
			// ������״̬
			else if (action.equalsIgnoreCase("SoldBangByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.SoldBangByUid(uid);
					if (ret) {
						out.write("ok");
					} else {
						out.write("error");
					}
				}
			} else if (action.equalsIgnoreCase("SoldXzgjByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.SoldXzgjByUid(uid);
					if (ret) {
						out.write("ok");
					} else {
						out.write("error");
					}
				}
			}
			// �ûָ�״̬
			else if (action.equalsIgnoreCase("RecBangByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.RecBangByUid(uid);
					if (ret) {
						out.write("ok");
					} else {

						out.write("error");
					}
				}
			} else if (action.equalsIgnoreCase("RecXzgjByUid")) {
				synchronized (this.getClass()) {
					String uid = request.getParameter("account");
					boolean ret = connect.RecXzgjByUid(uid);
					if (ret) {
						out.write("ok");
					} else {

						out.write("error");
					}
				}
			}
			// ��ȡ������֮���˺�
			else if (action.equalsIgnoreCase("getaccbsjzl")) {
				String account = connect.getAccountbsjzl();
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockAccountbsjzl(acc[0], ipaddress);
					out.write(account);
				}
			}
			// ��ȡ����3��֤���˺�
			else if (action.equalsIgnoreCase("getaccbhidentify")) {
				String account = connect.getAccountbhidentify();
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockAccountbhidentify(acc[0], ipaddress);
					out.write(account);
				}
			}
			// ��ȡʳ֮��Լ�˺�
			else if (action.equalsIgnoreCase("getAccLx")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountLx();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountLx(acc[0], ipaddress);
						out.write(account);
					}
				}

			}
			// ��ȡʮ��ս���˺�
			else if (action.equalsIgnoreCase("getUrlQqOne")) {
				String account = connect.getUrlQqOne();
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockUrlQqOne(acc[1], ipaddress);
					out.write(acc[0]);
				}
			} else if (action.equalsIgnoreCase("getUrlQqTwo")) {
				String account = connect.getUrlQqTwo();
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockUrlQqTwo(acc[1], ipaddress);
					out.write(acc[0]);
				}
			} else if (action.equalsIgnoreCase("getUrlQqThree")) {
				String account = connect.getUrlQqThree();
				if (account == null) {
					out.write("null");
				} else {
					String acc[] = account.split("#");
					connect.lockUrlQqThree(acc[1], ipaddress);
					out.write(acc[0]);
				}
			}
			// ��ȡ�Ҵﰲ׿�˺�
			else if (action.equalsIgnoreCase("getAccGdAndroid")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountGdAndroid();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccountGdAndroid(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// ��ȡ�Ҵﰲ׿�˺Ų���
			else if (action.equalsIgnoreCase("getAccGdAndroidTest")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiGdAndroidTest(imei);
					if (count == 0) {
						String account = connect.getAccountGdAndroidTest();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountGdAndroidTest(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiGdAndroidTest(imei);
						out.write(account);
					}
				}
			}
			// ��ȡ�ӳ�С���˺�ios
			else if (action.equalsIgnoreCase("getaccdz")) {
				synchronized (this.getClass()) {
					String account = connect.getAccountdz();
					if (account == null) {
						out.write("null");
					}
					String acc[] = account.split("#");
					connect.lockAccountdz(acc[0], ipaddress);
					out.write(account);
				}
			}
			// ��ȡ�컪�˺�
			else if (action.equalsIgnoreCase("getaccth")) {
				String account = connect.getAccountth();
				if (account == null) {
					out.write("null");
				}
				String acc[] = account.split("#");
				connect.lockAccountth(acc[0], ipaddress);
				out.write(account);
			}
			// �ͷŶӳ�С���˺�
			else if (action.equalsIgnoreCase("releaseaccdz")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String total = request.getParameter("total");
				String role = request.getParameter("role");
				Boolean bRet = connect.releaseAccountdz(account, Integer.parseInt(total), remark, role, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ�ħ������׿�˺�
			else if (action.equalsIgnoreCase("releaseAccMklyAndroid")) {
				String account = request.getParameter("account");
				String holywater = request.getParameter("holywater");
				String bluestone = request.getParameter("bluestone");
				String redstone = request.getParameter("redstone");
				String urcard = request.getParameter("urcard");
				Boolean bRet = connect.releaseAccountMklyAndroid(account, holywater, bluestone, redstone, urcard,
						ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ��컪�˺�
			else if (action.equalsIgnoreCase("releaseaccth")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamond");
				Boolean bRet = connect.releaseAccountth(account, diamond, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ���֮�켣��ʼ�˺�
			else if (action.equalsIgnoreCase("upAccKzgjCdKey")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountTmzz(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ�����֮�ӳ�ʼ�˺�
			else if (action.equalsIgnoreCase("upAccFyzj")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountFyzj(account, pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ����Ͳ��Խű�
			else if (action.equalsIgnoreCase("upAccZy")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountZy(account,pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("upAccBangAndroid")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.uploadAccountBangSsrAndroid(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ�����֮���Գ��˺�
			else if (action.equalsIgnoreCase("upAccTmzzZc")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountTmzzZc(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ��Ҵ�MG�˺�
			else if (action.equalsIgnoreCase("upAccMG")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountczjy(account, pwd);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ������ο��˺�
			else if (action.equalsIgnoreCase("upAccZlong")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccZlong(account, pwd);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ�����Ԫ��ս
			else if (action.equalsIgnoreCase("upAccCcy")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccCcy(account, pwd);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ����շ����Գ�ע���˺�
			else if (action.equalsIgnoreCase("upAccMrfzZc")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccMrfzZc(account, pwd);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ����շ��۳�ʼע���˺�
			else if (action.equalsIgnoreCase("upAccMrfzSsr")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				String tok = request.getParameter("token");
				// String pwd
				// =URLDecoder.decode(request.getParameter("pwd"),"utf-8");
				// System.out.println(pwd);
				Boolean bRet = connect.uploadAccMrfzSsr(account, pwd, tok);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ����շ��۳�ʼע���˺ų�����
			else if (action.equalsIgnoreCase("upAccMrfzSsrEx")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				String tok = request.getParameter("token");
				String username = request.getParameter("username");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				// String pwd
				// =URLDecoder.decode(request.getParameter("pwd"),"utf-8");
				// System.out.println(pwd);
				Boolean bRet = connect.uploadAccMrfzSsrEx(account, pwd, tok, remark, total, role, username);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ��ֻ��豸��Ϣ
			else if (action.equalsIgnoreCase("uploadDevice")) {
				String device = request.getParameter("deviceid");
				String ipaddressEx = request.getParameter("ipaddress");
				Boolean bRet = connect.uploadDevice(device, ipaddressEx);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ���������˺�
			else if (action.equalsIgnoreCase("upAccLzjx")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountLzjx(account, pwd);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccTx")) {
				String account = request.getParameter("account");
				int zs = 0;
				if (request.getParameter("aomi").equals("") | request.getParameter("aomi").equals("nil")) {
					zs = 0;
				} else {
					zs = Integer.parseInt(request.getParameter("aomi"));
				}
				int wqj = 0;
				if (request.getParameter("gulao").equals("") | request.getParameter("gulao").equals("nil")) {
					wqj = 0;
				} else {
					wqj = Integer.parseInt(request.getParameter("gulao"));
				}
				int fyz = 0;
				if (request.getParameter("baoshi").equals("") | request.getParameter("baoshi").equals("nil")) {
					fyz = 0;
				} else {
					fyz = Integer.parseInt(request.getParameter("baoshi"));
				}
				Boolean bRet = connect.releaseAccountTx(account, zs, wqj, fyz, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ͻϮ�˺��������
			else if (action.equalsIgnoreCase("releaseAccTxError")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccTxError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ����շ����Գ�
			else if (action.equalsIgnoreCase("releaseAccMrfzZc")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				int ys = 0;
				if (request.getParameter("ys").equals("") | request.getParameter("ys").equals("nil")) {
					ys = 0;
				} else {
					ys = Integer.parseInt(request.getParameter("ys"));
				}
				int hcy = 0;
				if (request.getParameter("hcy").equals("") | request.getParameter("hcy").equals("nil")) {
					hcy = 0;
				} else {
					hcy = Integer.parseInt(request.getParameter("hcy"));
				}
				int xfpz = 0;
				if (request.getParameter("xfpz").equals("") | request.getParameter("xfpz").equals("nil")) {
					xfpz = 0;
				} else {
					xfpz = Integer.parseInt(request.getParameter("xfpz"));
				}
				Boolean bRet = connect.releaseAccountMrfzZc(account, remark, ipaddress, ys, hcy, xfpz);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ����շ��۳�ʼ
			else if (action.equalsIgnoreCase("releaseAccMrfzSsr")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				Boolean bRet = connect.releaseAccountMrfzSsr(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ���շ��ۿպ�
			else if (action.equalsIgnoreCase("releaseAccMrfzSsrNull")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccountMrfzSsrNull(account);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}

			}
			// ���շ���ǩ��
			else if (action.equalsIgnoreCase("releaseAccMrfzSsrSign")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccountMrfzSsrSign(account);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}

			}
			// �ͷŰ�����֮���˺�
			else if (action.equalsIgnoreCase("releaseaccbsjzl")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamond");
				String ticket = request.getParameter("ticket");
				String total = request.getParameter("total");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				// String account,String diamond,String ticket, String total,
				// String remark, String role, String ipaddress
				Boolean bRet = connect.releaseAccountbsjzl(account, diamond, ticket, total, remark, role, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷŸҴ��˺�
			else if (action.equalsIgnoreCase("releaseaccgd")) {
				String account = request.getParameter("account");
				int points = 0;
				if (request.getParameter("points").equals("") | request.getParameter("points").equals("nil")) {
					points = 0;
				} else {
					points = Integer.parseInt(request.getParameter("points"));
				}
				int tickets = 0;
				if (request.getParameter("tickets").equals("") | request.getParameter("tickets").equals("nil")) {
					tickets = 0;
				} else {
					tickets = Integer.parseInt(request.getParameter("tickets"));
				}
				int cg = 0;
				if (request.getParameter("cg").equals("") | request.getParameter("cg").equals("nil")) {
					cg = 0;
				} else {
					cg = Integer.parseInt(request.getParameter("cg"));
				}
				int jt = 0;
				if (request.getParameter("jt").equals("") | request.getParameter("jt").equals("nil")) {
					jt = 0;
				} else {
					jt = Integer.parseInt(request.getParameter("jt"));
				}
				String stars = request.getParameter("stars");
				String roleLevel = request.getParameter("level");
				Boolean bRet = connect.releaseAccountgd(account, points, tickets, cg, jt, stars, roleLevel, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ���������˺�
			else if (action.equalsIgnoreCase("releaseAccLzjx")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccountLzjx(account, remark, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ�ʮ��ս���˺�
			else if (action.equalsIgnoreCase("releaseaccsezj")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamond");
				Boolean bRet = connect.releaseAccountsezj(account, diamond, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ��˺�
			else if (action.equalsIgnoreCase("releaseaccbhidentify")) {
				String account = request.getParameter("account");
				String level = request.getParameter("level");
				String diamond = request.getParameter("diamond");
				Boolean bRet = connect.releaseAccountbhidentify(account, level, diamond, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ�ʱ֮��
			else if (action.equalsIgnoreCase("releaseAccLx")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamond");
				String role = request.getParameter("role");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccountLx(account, diamond, role, remark, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷŸҴﰲ׿�˺�
			else if (action.equalsIgnoreCase("releaseAccGdAndroid")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamonds");
				String normal = request.getParameter("normal");
				String strong = request.getParameter("strong");
				Boolean bRet = connect.releaseAccountGdAndroid(account, normal, strong, diamond, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccGdAndroidTest")) {
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamonds");
				String normal = request.getParameter("normal");
				String strong = request.getParameter("strong");
				String imei = request.getParameter("imei");
				Boolean bRet = connect.releaseAccountGdAndroidTest(account, normal, strong, diamond, ipaddress, imei);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ɾ����������˺�
			else if (action.equalsIgnoreCase("czjyError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.czjyError(account);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("deleteaccbh")) {
				String account = request.getParameter("account");
				int sLen = 0;
				sLen = account.length();
				if (sLen == 17) {
					account = account.substring(1);
				}
				Boolean bRet = connect.deleteAccountbh(account);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* ����֮�� */
			// ȡ�˺�
			else if (action.equalsIgnoreCase("getAcctmzz")) {
				synchronized (this.getClass()) {
					String account = connect.getAccTmzz();
					if (account == null) {
						out.write("null");
					} else {
						connect.lockAccTmzz(account, ipaddress);
						out.write(account);
					}
				}
			}
			// �ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccTmzz")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String diamond = request.getParameter("diamond");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccTmzz(account, diamond, remark, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccTmzzEx")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String role = request.getParameter("role");
				int diamonds = 0;
				if (request.getParameter("diamonds").equals("") | request.getParameter("diamonds").equals("nil")) {
					diamonds = 0;
				} else {
					diamonds = Integer.parseInt(request.getParameter("diamonds"));
				}
				int red = 0;
				if (request.getParameter("red").equals("") | request.getParameter("red").equals("nil")) {
					red = 0;
				} else {
					red = Integer.parseInt(request.getParameter("red"));
				}
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				// System.out.println(role);
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccTmzzEx(account, role, remark, diamonds, red, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			// ȡ�˺�
			else if (action.equalsIgnoreCase("getAcctmzzZc")) {
				synchronized (this.getClass()) {
					String account = connect.getAccTmzzZc();
					if (account == null) {
						out.write("null");
					} else {
						connect.lockAccTmzzZc(account, ipaddress);
						out.write(account);
					}
				}
			}
			// �ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccTmzzZc")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String role = request.getParameter("role");
				int diamonds = 0;
				if (request.getParameter("diamonds").equals("") | request.getParameter("diamonds").equals("nil")) {
					diamonds = 0;
				} else {
					diamonds = Integer.parseInt(request.getParameter("diamonds"));
				}
				int red = 0;
				if (request.getParameter("red").equals("") | request.getParameter("red").equals("nil")) {
					red = 0;
				} else {
					red = Integer.parseInt(request.getParameter("red"));
				}
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				// System.out.println(role);
				String remark = request.getParameter("remark");
				Boolean bRet = connect.releaseAccTmzzZc(account, role, remark, diamonds, red, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* ����֮�Ӳ��ֽ��� */

			// �λ�ģ��ս����˺�
			else if (action.equalsIgnoreCase("releaseAccMh")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccMh(account);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* ������ */
			// ������ȡ�˺�
			else if (action.equalsIgnoreCase("getAccHhwssr")) {
				synchronized (getClass()) {
					String account = connect.getAccHhwssr();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccHhwssr(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// �������ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccHhwssr")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String serveString = request.getParameter("serverid");
				int serverid = 0;
				if (serveString != null) {
					if (serveString.equals("") | serveString.equals("nil") | serveString.equals("null")) {
					} else {
						serverid = Integer.parseInt(serveString);
					}
				}
				String role = request.getParameter("role");

				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}

				Boolean bRet = connect.releaseAccHhwssr(account, remark, serverid, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getAccHhwzc")) {
				synchronized (this.getClass()) {
					String account = connect.getAccHhwzc();
					if (account == null) {
						out.write("null");
					} else {
						String acc[] = account.split("#");
						connect.lockAccHhwzc(acc[0], ipaddress);
						out.write(account);
					}
				}
			}
			// �������ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccHhwzc")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String zsString = request.getParameter("zs");
				String ptjString = request.getParameter("ptj");
				String qzjString = request.getParameter("qzj");
				String serveridString = request.getParameter("serverid");
				int zs = 0;
				if (zsString != null) {
					if (zsString.equals("") | zsString.equals("nil")) {
					} else {
						zs = Integer.parseInt(zsString);
					}
				}
				int ptj = 0;
				if (ptjString != null) {
					if (ptjString.equals("") | ptjString.equals("nil")) {
					} else {
						ptj = Integer.parseInt(ptjString);
					}
				}
				int qzj = 0;
				if (qzjString != null) {
					if (qzjString.equals("") | qzjString.equals("nil")) {
					} else {
						qzj = Integer.parseInt(qzjString);
					}
				}
				int serverid = 0;
				if (serveridString != null) {
					if (serveridString.equals("") | serveridString.equals("nil")) {
					} else {
						serverid = Integer.parseInt(serveridString);
					}
				}
				Boolean bRet = connect.releaseAccHhwzc(account, zs, ptj, qzj, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			/* �������˺Ž��� */
			/* ˫���ӽ��ȡ�˺�*/
			else if (action.equalsIgnoreCase("getAccSssj")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiSssj(imei);
					if (count == 0) {
						String account = connect.getAccountSssj();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountSssj(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiSssj(imei);
						out.write(account);
					}
				}
			}
			// ����Ԫ��ս�ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccCcy")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				String serString = request.getParameter("serverid");
				String zsString = request.getParameter("zs");
				String qzString = request.getParameter("qz");
				String jyString = request.getParameter("jy");
				int iSer = 0;
				if (serString != null) {
					if (serString.equals("") | serString.equals("nil")) {
					} else {
						iSer = Integer.parseInt(serString);
					}
				}
				int zs = 0;
				if (zsString != null) {
					if (zsString.equals("") | zsString.equals("nil")) {
					} else {
						zs = Integer.parseInt(zsString);
					}
				}
				int qz = 0;
				if (qzString != null) {
					if (qzString.equals("") | qzString.equals("nil")) {
					} else {
						qz = Integer.parseInt(qzString);
					}
				}
				int jy = 0;
				if (jyString != null) {
					if (jyString.equals("") | jyString.equals("nil")) {
					} else {
						jy = Integer.parseInt(jyString);
					}
				}
				Boolean bRet = connect.releaseAccCcy(account, iSer, zs, qz, jy, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} 
			//�ͷ�˫���ӽ��˺�
			else if (action.equalsIgnoreCase("releaseAccSssj")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccSssj(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* ����Ԫ��ս���� */

			/* Bang */
			else if (action.equalsIgnoreCase("getAccZy")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZy(imei);
					if (count == 0) {
						String account = connect.getAccountZy();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZy(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZy(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccBangSsrAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiBangSsrAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountBangSsrAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountBangSsrAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiBangSsrAndroid(imei);
						out.write(account);
					}
				}
			}
			// Bang�ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccBang")) {
				String account = request.getParameter("account");
				String gcString = request.getParameter("gangcai");
				String ryString = request.getParameter("ranyou");
				String wzString = request.getParameter("wuzi");
				String zsString = request.getParameter("zuanshi");
				int gc = 0;
				if (gcString != null) {
					if (gcString.equals("") | gcString.equals("nil")) {
					} else {
						gc = Integer.parseInt(gcString);
					}
				}
				int ry = 0;
				if (ryString != null) {
					if (ryString.equals("") | ryString.equals("nil")) {
					} else {
						ry = Integer.parseInt(ryString);
					}
				}
				int wz = 0;
				if (wzString != null) {
					if (wzString.equals("") | wzString.equals("nil")) {
					} else {
						wz = Integer.parseInt(wzString);
					}
				}
				int zs = 0;
				if (zsString != null) {
					if (zsString.equals("") | zsString.equals("nil")) {
					} else {
						zs = Integer.parseInt(zsString);
					}
				}
				Boolean bRet = connect.releaseAccBang(account, gc, ry, wz, zs, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccBangSsr")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				Boolean bRet = connect.releaseAccountBangSsr(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccBangSsrAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccountBangSsrAndroid(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* ��ӡս�� */
			else if (action.equalsIgnoreCase("getAccFyzj")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiFyzj(imei);
					if (count == 0) {
						String account = connect.getAccountFyzj();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountFyzj(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiFyzj(imei);
						out.write(account);
					}
				}
			}
			// ����Ԫ��ս�ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccFyzj")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");

				// String serString = request.getParameter("serverid");

				String zsString = request.getParameter("zs");
				String qzString = request.getParameter("wqj");
				String jyString = request.getParameter("fyz");

				/*
				 * int iSer = 0; if (serString != null) { if
				 * (serString.equals("") | serString.equals("nil")) { } else {
				 * iSer = Integer.parseInt(serString); } }
				 */

				int zs = 0;
				if (zsString != null) {
					if (zsString.equals("") | zsString.equals("nil")) {
					} else {
						zs = Integer.parseInt(zsString);
					}
				}
				int qz = 0;
				if (qzString != null) {
					if (qzString.equals("") | qzString.equals("nil")) {
					} else {
						qz = Integer.parseInt(qzString);
					}
				}
				int jy = 0;
				if (jyString != null) {
					if (jyString.equals("") | jyString.equals("nil")) {
					} else {
						jy = Integer.parseInt(jyString);
					}
				}

				Boolean bRet = connect.releaseAccFyzj(account, zs, qz, jy, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccFyzjError")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccFyzjError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			/* ��֮�켣���� */
			else if (action.equalsIgnoreCase("upAccXzgj")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountXzgj(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getAccXzgj")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiXzgj(imei);
					if (count == 0) {
						String account = connect.getAccountXzgj();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountXzgj(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiXzgj(imei);
						out.write(account);
					}
				}
			}
			// �ͷ���֮�켣�˺�
			else if (action.equalsIgnoreCase("releaseAccXzgj")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccXzgj(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��֮�켣��׿
			else if (action.equalsIgnoreCase("upAccXzgjAndroid")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountXzgjAndroid(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getAccFfbeAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiFfbeAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountFfbeAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountFfbeAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiFfbeAndroid(imei);
						out.write(account);
					}
				}
			}
			// �ͷ���֮�켣�˺�
			else if (action.equalsIgnoreCase("releaseAccFfbeAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccFfbeAndroid(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			/* all star */
			else if (action.equalsIgnoreCase("upAccAllstar")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccAllstar(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}

			} else if (action.equalsIgnoreCase("upAccPxzgZc")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccPxzgZc(account, pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getAccPxzg")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiPxzg(imei);
					if (count == 0) {
						String account = connect.getAccountPxzg();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountPxzg(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiPxzg(imei);
						out.write(account);
					}
				}
			}
			// �ͷ�����ս���˺�
			else if (action.equalsIgnoreCase("releaseAccPxzg")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");

				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}

				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}

				Boolean bRet = connect.releaseAccPxzg(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡallstar�Գ��
			else if (action.equalsIgnoreCase("getAccAllStar")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiAllStar(imei);
					if (count == 0) {
						String account = connect.getAccountAllStar();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountAllStar(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiAllStar(imei);
						out.write(account);
					}
				}
			}
			// allstar�ͷ�
			else if (action.equalsIgnoreCase("releaseAccAllstar")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				int red = 0;

				Boolean bRet = connect.releaseAccAllStar(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccPxzgError")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccPxzgError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccPxzgZcError")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccPxzgZcError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			// ���ջ��벿��
			else if (action.equalsIgnoreCase("getAccZzhx")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzhx(imei);
					if (count == 0) {
						String account = connect.getAccountZzhx();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZzhx(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzhx(imei);
						out.write(account);
					}
				}
			}
			// �ͷ����ջ����˺�
			else if (action.equalsIgnoreCase("releaseAccZzhx")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");

				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccZzhx(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡ���ջ����Գ��˺�
			else if (action.equalsIgnoreCase("getAccZzhxZc")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzhxZc(imei);
					if (count == 0) {
						String account = connect.getAccountZzhxZc();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZzhxZc(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzhxZc(imei);
						out.write(account);
					}
				}
			}
			// �ͷ����ջ����Գ��˺�
			else if (action.equalsIgnoreCase("releaseAccZzhxZc")) {
				String account = request.getParameter("account");

				int zs = 0;
				if (request.getParameter("zs") != null) {
					if (request.getParameter("zs").equals("") | request.getParameter("zs").equals("nil")) {
						zs = 0;
					} else {
						zs = Integer.parseInt(request.getParameter("zs"));
					}
				}
				int shi = 0;
				if (request.getParameter("shi") != null) {
					if (request.getParameter("shi").equals("") | request.getParameter("shi").equals("nil")) {
						shi = 0;
					} else {
						shi = Integer.parseInt(request.getParameter("shi"));
					}
				}
				int xi = 0;
				if (request.getParameter("xi") != null) {
					if (request.getParameter("xi").equals("") | request.getParameter("xi").equals("nil")) {
						xi = 0;
					} else {
						xi = Integer.parseInt(request.getParameter("xi"));
					}
				}
				Boolean bRet = connect.releaseAccZzhxZc(account, zs, shi, xi, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ������Լssr
			else if (action.equalsIgnoreCase("getAccClsySsrAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiClsySsrAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountClsySsrAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountClsySsrAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiClsySsrAndroid(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccClsySsrAndroidEx")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiClsySsrAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountClsySsrAndroidEx();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountClsySsrAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiClsySsrAndroid(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("releaseAccClsySsrAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");

				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}

				int red = 0;
				if (request.getParameter("red") != null) {
					if (request.getParameter("red").equals("") | request.getParameter("red").equals("nil")) {
						red = 0;
					} else {
						red = Integer.parseInt(request.getParameter("red"));
					}
				}
				Boolean bRet = connect.releaseAccClsySsrAndroid(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccClsySsrErrorAndroid")) {
				// ��¼�����ʯ����
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccClsySsrErrorAndroid(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ������Լssr����

			// ��װս��start
			else if (action.equalsIgnoreCase("upAccZzzjSsrEx")) {
				String account = request.getParameter("account");

				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
					total = 0;
				} else {
					total = Integer.parseInt(request.getParameter("total"));
				}
				// String pwd
				// =URLDecoder.decode(request.getParameter("pwd"),"utf-8");
				// System.out.println(pwd);
				Boolean bRet = connect.uploadAccZzzjSsrEx(account, remark, total, role);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �����װս����ʼ��
			else if (action.equalsIgnoreCase("getAccZzzjSsr")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzzjSsr(imei);
					if (count == 0) {
						String account = connect.getAccountZzzjSsr();
						if (account == null) {
							out.write("null");
						} else {

							connect.lockAccountZzzjSssr(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzzjSsr(imei);
						out.write(account);
					}
				}
			}
			// ȫ��ǩ��
			else if (action.equalsIgnoreCase("getAccZzzjSsrSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzzjSsr(imei);
					if (count == 0) {
						String account = connect.getAccountZzzjSsrSign();
						if (account == null) {
							out.write("null");
						} else {

							connect.lockAccountZzzjSssr(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzzjSsr(imei);
						out.write(account);
					}
				}
			}
			// �ͷ���װս��ssr
			else if (action.equalsIgnoreCase("releaseAccZzzjSsr")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccZzzjSsr(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ������˺���װս��
			else if (action.equalsIgnoreCase("releaseAccZzzjError")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccZzzjError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			else if (action.equalsIgnoreCase("upAccZzzjSsrAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.uploadAccZzzjSsrExAndroid(account, remark);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getAccZzzjSsrAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzzjSsrAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountZzzjSsrAndroid();
						if (account == null) {
							out.write("null");
						} else {
							connect.lockAccountZzzjSssrAndroid(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzzjSsrAndroid(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccZzzjSrAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZzzjSsrAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountZzzjSrAndroid();
						if (account == null) {
							out.write("null");
						} else {
							connect.lockAccountZzzjSssrAndroid(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZzzjSsrAndroid(imei);
						out.write(account);
					}
				}
			}
			// �ͷ���װս��ssr
			else if (action.equalsIgnoreCase("releaseAccZzzjSsrAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccZzzjSsrAndroid(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ҫůů�ϴ�ȡ�Ų���
			else if (action.equalsIgnoreCase("upAccSynnSsr")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				Boolean bRet = connect.uploadAccSynn(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ���﷨���ͷ��˺�
			else if (action.equalsIgnoreCase("releaseAccZwfz")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				String serverid = request.getParameter("serverid");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccZwfz(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccZwfzError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccZwfzError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �Ա�����ר��
			else if (action.equalsIgnoreCase("releaseAccSynnSsrPwd")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.releaseAccSynnPwd(account, pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ���﷨��ȡ��
			else if (action.equalsIgnoreCase("getAccZwfz")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZwfz(imei);
					if (count == 0) {
						String account = connect.getAccountZwfz();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZwfz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZwfz(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccZwfzWz")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZwfz(imei);
					if (count == 0) {
						String account = connect.getAccountZwfzWz();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZwfz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZwfz(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccZwfzTest")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZwfz(imei);
					if (count == 0) {
						String account = connect.getAccountZwfzTest();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZwfz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZwfz(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccZwfzWifi")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZwfz(imei);
					if (count == 0) {
						String account = connect.getAccountZwfzWifi();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZwfz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZwfz(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("upAccJlss")) {
				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountJlss(account, pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}

			// ���﷨���ϴ��˺�
			else if (action.equalsIgnoreCase("upAccZwfz")) {
				String uid = request.getParameter("uid");
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountZwfz(uid, account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ·�˳��ܻ�ȡ�˺�
			else if (action.equalsIgnoreCase("getAccLrcn")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiLrcn(imei);
					if (count == 0) {
						String account = connect.getAccountLrcn();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountLrcn(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiLrcn(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccLrcnSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiLrcn(imei);
					if (count == 0) {
						String account = connect.getAccountLrcnSign();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountLrcn(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiLrcn(imei);
						out.write(account);
					}
				}
			}
			// �ͷ�·�˳���
			else if (action.equalsIgnoreCase("releaseAccLrcn")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccLrcn(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccLrcnError")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccLrcnError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ĺ���Ȯios
			else if (action.equalsIgnoreCase("upAccWhmq")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.uploadAccountWhmq(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ĺ���Ȯ��ȡ�˺�
			else if (action.equalsIgnoreCase("getAccWhmq")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiWhmq(imei);
					if (count == 0) {
						String account = connect.getAccountWhmq();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountWhmq(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiWhmq(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccWhmqSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiWhmq(imei);
					if (count == 0) {
						String account = connect.getAccountWhmqSign();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountWhmq(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiWhmq(imei);
						out.write(account);
					}
				}
			}
			// �ͷ��ĺ���Ȯ
			else if (action.equalsIgnoreCase("releaseAccWhmq")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccWhmq(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccWhmqError")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccWhmqError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ����ʳ��start
			else if (action.equalsIgnoreCase("getAccJlss")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiJlss(imei);
					if (count == 0) {
						String account = connect.getAccountJlss();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountJlss(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiJlss(imei);
						out.write(account);
					}
				}
			}
			// �ͷž���ʳ��
			else if (action.equalsIgnoreCase("releaseAccJlss")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				int lingyu = 0;
				if (request.getParameter("lingyu") != null) {
					if (request.getParameter("lingyu").equals("") | request.getParameter("lingyu").equals("nil")) {
						lingyu = 0;
					} else {
						lingyu = Integer.parseInt(request.getParameter("lingyu"));
					}
				}
				int putong = 0;
				if (request.getParameter("putong") != null) {
					if (request.getParameter("putong").equals("") | request.getParameter("putong").equals("nil")) {
						putong = 0;
					} else {
						putong = Integer.parseInt(request.getParameter("putong"));
					}
				}
				int xianding = 0;
				if (request.getParameter("xianding") != null) {
					if (request.getParameter("xianding").equals("") | request.getParameter("xianding").equals("nil")) {
						xianding = 0;
					} else {
						xianding = Integer.parseInt(request.getParameter("xianding"));
					}
				}
				Boolean bRet = connect.releaseAccJlss(account, remark, role, total, serverid, lingyu, putong, xianding,
						ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡ����ʳ����׿
			else if (action.equalsIgnoreCase("getAccJlssAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiJlssAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountJlssAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountJlssAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiJlssAndroid(imei);
						out.write(account);
					}
				}
			}
			// �ͷž���ʳ����׿
			else if (action.equalsIgnoreCase("releaseAccJlssAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				Boolean bRet = connect.releaseAccJlssAndroid(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷž���ʳ�������˺�
			else if (action.equalsIgnoreCase("releaseAccJlssError")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccJlssError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccJlssErrorAndroid")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccJlssErrorAndroid(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ����ʦԤԼ��
			else if (action.equalsIgnoreCase("getAccYys")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiYys(imei);
					if (count == 0) {
						String account = connect.getAccountYys();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountYys(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiYys(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("releaseAccYys")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}

				Boolean bRet = connect.releaseAccYys(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ���������潨���ϴ�
			else if (action.equalsIgnoreCase("upAccCahsh")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountCahsh(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getUidCahsh")) {
				synchronized (this.getClass()) {
					String account = request.getParameter("account");
					int bRet = connect.getLockCntByUidCahsh(account);
					// System.out.println(bRet);
					String ret = String.valueOf(bRet);
					out.write(ret);
				}
			} else if (action.equalsIgnoreCase("getUidSgz")) {
				synchronized (this.getClass()) {
					String account = request.getParameter("account");
					int bRet = connect.getLockCntByUidSgz(account);
					// System.out.println(bRet);
					String ret = String.valueOf(bRet);
					out.write(ret);
				}
			}
			// IOS����ȡ��
			else if (action.equalsIgnoreCase("getAccCahsh")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiCahsh(imei);
					if (count == 0) {
						String account = connect.getAccountCahsh();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountCahsh(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiCahsh(imei);
						out.write(account);
					}
				}
			}
			// IOS�����ͷ�
			else if (action.equalsIgnoreCase("releaseAccCahsh")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				Boolean bRet = connect.releaseAccCahsh(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// IOS���ִ���ս�Գ�
			else if (action.equalsIgnoreCase("releaseAccHldzzZc")) {
				String account = request.getParameter("account");
				// String remark = request.getParameter("remark");
				// String role = request.getParameter("role");
				int diamonds = 0;
				if (request.getParameter("diamonds") != null) {
					if (request.getParameter("diamonds").equals("") | request.getParameter("diamonds").equals("nil")) {
						diamonds = 0;
					} else {
						diamonds = Integer.parseInt(request.getParameter("diamonds"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				int tickets = 0;
				if (request.getParameter("tickets") != null) {
					if (request.getParameter("tickets").equals("") | request.getParameter("tickets").equals("nil")) {
						tickets = 0;
					} else {
						tickets = Integer.parseInt(request.getParameter("tickets"));
					}
				}
				Boolean bRet = connect.releaseAccHldzzZc(account, serverid, diamonds, tickets, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// IOS��ͥ��ʦȡ���Գ��
			else if (action.equalsIgnoreCase("getAccjtjs")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiJtjs(imei);
					if (count == 0) {
						String account = connect.getAccountJtjs();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountJtjs(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiJtjs(imei);
						out.write(account);
					}
				}
			}

			// ��׿��ʮ��ѧԺ
			// ��׿��ʮ�������ϴ�
			else if (action.equalsIgnoreCase("upAccFssSsrAndroid")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.uploadAccountFssAndroid(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("getUidFssAndroid")) {
				synchronized (this.getClass()) {
					String account = request.getParameter("account");
					int bRet = connect.getLockCntByUidFssAndroid(account);
					String ret = String.valueOf(bRet);
					out.write(ret);

				}
			}
			// ��ͥ��ʦios�ͷ�
			else if (action.equalsIgnoreCase("releaseAccJtjs")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccJtjs(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��׿��ʮ��ȡ��
			else if (action.equalsIgnoreCase("getAccFssSsrAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiFssAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountFssAndroid();
						if (account == null) {
							out.write("null");
						} else {
							// String acc[] = account.split("#");
							connect.lockAccountFssAndroid(account, ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiFssAndroid(imei);
						out.write(account);
					}
				}
			}
			// ��ԴŮ���ͷ�
			else if (action.equalsIgnoreCase("releaseAccQyns")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				String serverid = request.getParameter("serverid");
				String fwq = request.getParameter("fwq");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccQyns(account, remark, role, total, serverid, fwq,ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ԴŮ��ȡ��
			else if (action.equalsIgnoreCase("getAccQyns")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiQyns(imei);
					if (count == 0) {
						String account = connect.getAccountQyns();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountQyns(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiQyns(imei);
						out.write(account);
					}
				}
			}
			//��ԴŮ��׿ȡ��
			else if (action.equalsIgnoreCase("getAccQynsAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiQynsAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountQynsAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountQynsAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiQynsAndroid(imei);
						out.write(account);
					}
				}
			}
			// ��ԴŮ��׿�ͷ�
			else if (action.equalsIgnoreCase("releaseAccQynsAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				String serverid = request.getParameter("serverid");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccQynsAndroid(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// iosExosHero�ͷ�
			else if (action.equalsIgnoreCase("releaseAccHero")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				String serverid = request.getParameter("serverid");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccHero(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			else if (action.equalsIgnoreCase("releaseAccHeroRe")) {
				String account = request.getParameter("account");

				Boolean bRet = connect.releaseAccHeroRe(account,ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// iosExosHeroȡ��
			else if (action.equalsIgnoreCase("getAccHero")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiHero(imei);
					if (count == 0) {
						String account = connect.getAccountHero();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountHero(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiHero(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccHeroSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiHeroSign(imei);
					if (count == 0) {
						String account = connect.getAccountHeroSign();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountHero(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiHeroSign(imei);
						out.write(account);
					}
				}
			}
			// ��ȡ���﷨��׿
			else if (action.equalsIgnoreCase("getAccZwfzAndroid")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiZwfzAndroid(imei);
					if (count == 0) {
						String account = connect.getAccountZwfzAndroid();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountZwfzAndroid(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiZwfzAndroid(imei);
						out.write(account);
					}
				}
			}
			// �ͷ����﷨��׿
			else if (action.equalsIgnoreCase("releaseAccZwfzAndroid")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				String serverid = request.getParameter("serverid");
				Boolean bRet = connect.releaseAccZwfzAndroid(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ����﷨��׿�쳣
			else if (action.equalsIgnoreCase("releaseAccZwfzAndroidError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccZwfzAndroidError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ϴ�����־ios
			else if (action.equalsIgnoreCase("upAccSgz")) {

				String account = request.getParameter("account");
				String pwd = request.getParameter("pwd");
				Boolean bRet = connect.uploadAccountSgz(account, pwd, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡ����־ios
			else if (action.equalsIgnoreCase("getAccSgz")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiSgz(imei);
					if (count == 0) {
						String account = connect.getAccountSgz();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountSgz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiSgz(imei);
						out.write(account);
					}
				}
			} else if (action.equalsIgnoreCase("getAccSgzSign")) {
				String imei = request.getParameter("imei");
				synchronized (this.getClass()) {
					int count = 0;
					count = connect.getLockCntByImeiSgz(imei);
					if (count == 0) {
						String account = connect.getAccountSgzSign();
						if (account == null) {
							out.write("null");
						} else {
							String acc[] = account.split("#");
							connect.lockAccountSgz(acc[0], ipaddress, imei);
							out.write(account);
						}
					} else {
						String account = connect.getLockInfoByImeiSgz(imei);
						out.write(account);
					}
				}
			}
			// �ͷ�����־ios
			else if (action.equalsIgnoreCase("releaseAccSgz")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				int serverid = 0;
				if (request.getParameter("serverid") != null) {
					if (request.getParameter("serverid").equals("") | request.getParameter("serverid").equals("nil")) {
						serverid = 0;
					} else {
						serverid = Integer.parseInt(request.getParameter("serverid"));
					}
				}
				Boolean bRet = connect.releaseAccSgz(account, remark, role, total, serverid, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			} else if (action.equalsIgnoreCase("releaseAccSgzEx")) {
				String account = request.getParameter("account");
				String remark = request.getParameter("remark");
				String role = request.getParameter("role");
				int total = 0;
				if (request.getParameter("total") != null) {
					if (request.getParameter("total").equals("") | request.getParameter("total").equals("nil")) {
						total = 0;
					} else {
						total = Integer.parseInt(request.getParameter("total"));
					}
				}
				Boolean bRet = connect.releaseAccSgzEx(account, remark, role, total, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ�����־ios����
			else if (action.equalsIgnoreCase("releaseAccSgzError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccSgzError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// �ͷ���ԴŮ�����
			else if (action.equalsIgnoreCase("releaseAccQynsAndroidError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccQynsAndroidError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			else if (action.equalsIgnoreCase("releaseAccQynsError")) {
				String account = request.getParameter("account");
				Boolean bRet = connect.releaseAccQynsError(account, ipaddress);
				if (bRet) {
					out.write("ok");
				} else {
					out.write("error");
				}
			}
			// ��ȡ�����
			else if (action.equalsIgnoreCase("getGiftCodeByGameId")) {
				String gameid = request.getParameter("gameid");
				String giftid = connect.getGiftIdByGameId(gameid);
				if (giftid != null) {
					out.write(giftid);
				} else {
					out.write("failed");
				}
			}
			/* �ű�����_satrt */
			// ͨ���豸�Ż�ȡ�������еĽű�����
			else if (action.equalsIgnoreCase("getScriptByDeviceid")) {
				String deviceId = request.getParameter("deviceid");
				String scriptName = connect.getScriptByDeviceid(deviceId);
				if (scriptName != null) {
					out.write(scriptName);
				} else {
					out.write("failed");
				}
			}
			// �ϴ�����log
			else if (action.equalsIgnoreCase("uploadLogHttp")) {
				String deviceId = request.getParameter("deviceid");
				String scriptName = request.getParameter("scriptname");
				String runLog = request.getParameter("runlog");
				boolean bRet = connect.uploadLogHttp(deviceId, scriptName, runLog);
				if (bRet) {
					out.write("success");
				} else {
					out.write("failed");
				}

			}
			// ��ȡ�ű��İ汾��
			else if (action.equalsIgnoreCase("getVersionByScriptName")) {
				String deviceId = request.getParameter("deviceid");
				String scriptName = request.getParameter("scriptname");
				String scriptVersion = connect.getVersionByScriptName(scriptName);
				if (scriptVersion != null) {
					out.write(scriptVersion);
				} else {
					out.write("failed");
				}

			}
			/* �ű�����_end */
			// ��ȡ����
			else if (action.equalsIgnoreCase("getRndNameCN")) {
				String scriptVersion = connect.getRndNameCN();
				if (scriptVersion != null) {
					out.write(scriptVersion);
				} else {
					out.write("failed");
				}
			} else {
				// do nothing
			}
			// �ر����ݿ�����
			connect.CutConnection(connection);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
