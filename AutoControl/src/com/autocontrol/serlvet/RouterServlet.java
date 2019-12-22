/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autocontrol.serlvet;

import com.alibaba.fastjson.JSON;
import com.jundaonet.makemoney.async.AsyncProcessor;
import com.jundaonet.makemoney.async.IAsyncProcess;
import com.jundaonet.makemoney.log.MakeMoneyLog.MakeMoneyLog;
import com.jundaonet.makemoney.utilities.Utilities;
import com.mysql.mycc.MySqlConnectPool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;


import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author zhaohengyi
 */
@WebServlet(name = "RouterServlet", urlPatterns = { "/servlet/RouterServlet" })
public class RouterServlet extends HttpServlet {

	private final String TAG = "[RouterServlet]:";
	private final String ADSL = "http://192.168.1.1/index.php/Network/wanset_vlan_static/api";
	private final String PPTP = "http://192.168.1.1/index.php/Service/pptpClient/api";

	enum ControlerTypte {

		Connect, DisConnect
	}

	/**
	 * ִ��sql��䣬����
	 * 
	 * @param sql
	 * @return
	 * @throws java.sql.SQLException
	 * @throws java.lang.InterruptedException
	 */
	public int executeUpdate(String sql) throws SQLException,
			InterruptedException, Exception {
		return MySqlConnectPool.Instance().executeUpdate(sql);
	}

	/**
	 * ��ѯ
	 * 
	 * @param sql
	 * @return
	 * @throws java.sql.SQLException
	 * @throws InterruptedException
	 */
	public List<HashMap<String, String>> executeQuery(String sql)
			throws SQLException, InterruptedException, Exception {
		return MySqlConnectPool.Instance().executeQuery(sql);
	}

	private String logIn() throws UnsupportedEncodingException, IOException {
		// ��ȡ�ỰID
		String cookie = null;

		// TODO��cookie �а���page��С��������10
		HttpGet httpGet = new HttpGet("http://192.168.1.1");
		httpGet.setHeader("Accept",
				"Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Connection", "keep-alive");
		HttpResponse response = HttpClients.createDefault().execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			// cookieֻ��һ�У���ȡ ��һ��/���һ�� Ч����һ����
			cookie = response.getFirstHeader("Set-Cookie").getValue();
		}
		if (Utilities.strIsEmpty(cookie)) {
			return null;
		}

		// ��¼��ǰ�Ự
		HttpPost httpPost = new HttpPost("http://192.168.1.1/login/x");
		httpPost.setHeader("Cookie", cookie);
		httpPost.setHeader("Connection", "keep-alive");
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("user", "admin"));
		param.add(new BasicNameValuePair("pass", "admin"));
		httpPost.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
		response = HttpClients.createDefault().execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			// {"recode":1,"error":"\u767b\u5f55\u5931\u8d25"}
			String ret = EntityUtils.toString(response.getEntity());
			Map<String, String> m = (Map<String, String>) JSON.parse(ret);
			return m.get("error").equalsIgnoreCase("��¼�ɹ�") ? cookie : null;
		}
		return null;
	}

	// ������������ݿ��е�url����
	private Map<String, String> strToMap(String str)
			throws UnsupportedEncodingException {
		String[] st = str.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String s : st) {
			String key = (String) s.subSequence(0, s.indexOf("="));
			String value = URLDecoder.decode(
					(String) s.subSequence(s.indexOf("=") + 1, s.length()),
					"utf-8");
			map.put(key, value);
		}
		return map;
	}

	// ���ƶ���������
	private boolean netControl(String cookie, String strPara,
			ControlerTypte type) throws UnsupportedEncodingException,
			IOException {
		/*
		 * id:30 type:down shell_data:internet=MAC_VLAN interface=wan1
		 * r:0.5467803536448628
		 */
		/*
		 * id:1 type:down r:0.06236004130914807
		 */
		if (Utilities.strIsEmpty(strPara)) {
			return false;
		}
		Map<String, String> map = this.strToMap(strPara);
		if (map.isEmpty()) {
			return false;
		}
		if (map.containsKey("type")) {
			if (type == ControlerTypte.Connect) {
				map.toString().replace("type", "up");				
			} else {
				map.toString().replace("type", "down");
			}
		} else {
			return false;
		}

		@SuppressWarnings("UnusedAssignment")
		String strHtml = null;
		if (map.containsKey("shell_data")) {
			strHtml = ADSL;
		} else {
			strHtml = PPTP;
		}

		// �趨cookie�����Ӳ���
		HttpPost httpPost = new HttpPost(strHtml);
		httpPost.setHeader("Cookie", cookie);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entrySet : map.entrySet()) {
			String key = entrySet.getKey();
			String value = entrySet.getValue();
			param.add(new BasicNameValuePair(key, value));
		}

		// ִ�����󣬷���json�ַ�����
		httpPost.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
		HttpResponse response = HttpClients.createDefault().execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			// {"recode":1,"error":"\u767b\u5f55\u5931\u8d25"}
			String ret = EntityUtils.toString(response.getEntity());
			if (Utilities.strIsEmpty(ret)) {
				return false;
			} else {
				Map<String, String> m = (Map<String, String>) JSON.parse(ret);
				return m.get("error").equalsIgnoreCase("ok");
			}
		}
		return false;
	}

	// ����aikuai·����������ʹ��http״̬����Ϊ �ɹ�/ʧ�� �ж�����
	private boolean doReconnectRouter(String url) throws IOException,
			InterruptedException {
		// ��ȡ�ỰID
		String cookie = this.logIn();
		if (Utilities.strIsEmpty(cookie)) {
			return false;
		}

		// �Ͽ������������2s��
		if (this.netControl(cookie, url, ControlerTypte.DisConnect)) {
			Thread.sleep(2000);
			return this.netControl(cookie, url, ControlerTypte.Connect);
		}
		return false;
	}

	@SuppressWarnings("UseSpecificCatch")
	private Map<String, String> httpHandler(HttpServletRequest request,
			StringBuilder error) throws InterruptedException, Exception {
		Map<String, String> map = new HashMap<String, String>();

		String action = request.getParameter("action");
		String sn = request.getParameter("sn");
		if (Utilities.strIsEmpty(action) || Utilities.strIsEmpty(sn)) {
			error.delete(0, error.length());
			error.append("key parameter null");
			return map;
		} else {
			if (action.equalsIgnoreCase("download")) {
				String sql = String
						.format("SELECT urlAddress FROM advertisement.mobile_net_map WHERE serialNum = '%s' limit 0, 1",
								sn);
				List<HashMap<String, String>> rets = this.executeQuery(sql);
				for (HashMap<String, String> ret : rets) {
					map.put("url", ret.get("urlAddress"));
				}
			} else if (action.equalsIgnoreCase("control")) {
				String url = request.getParameter("url");
				if (Utilities.strIsEmpty(url)) {
					error.delete(0, error.length());
					error.append("key parameter null");
					return map;
				} else {
					// ����·��������
					if (!this.doReconnectRouter(url)) {
						error.delete(0, error.length());
						error.append("reconnect router error");
						return map;
					} else {
						map.put("msg", "reconnect ok");
					}
				}
			} else {
				error.delete(0, error.length());
				error.append("key parameter null");
				return map;
			}
		}

		return map;
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet httpGet
	 * @param response
	 *            servlet response
	 */
	@Override
	@SuppressWarnings("UseSpecificCatch")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		AsyncContext asyncCtx = request.startAsync();
		asyncCtx.addListener(new AsyncListener() {

			public void onComplete(AsyncEvent event) throws IOException {
			}

			public void onTimeout(AsyncEvent event) throws IOException {
				MakeMoneyLog.Logd(TAG, "time out ...");
			}

			public void onError(AsyncEvent event) throws IOException {
				MakeMoneyLog.Logd(TAG, "on error");
			}

			public void onStartAsync(AsyncEvent event) throws IOException {
			}
		});
		asyncCtx.setTimeout(9000);
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request
				.getServletContext().getAttribute("executor");
		executor.execute(new AsyncProcessor(asyncCtx, new IAsyncProcess() {
			public Map<String, String> doNormalRunable(
					HttpServletRequest request, StringBuilder error)
					throws IOException, SQLException, InterruptedException,
					Exception {
				return httpHandler(request, error);
			}

			public String doTouchelfRunable(HttpServletRequest request)
					throws IOException, SQLException, InterruptedException,
					Exception {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		}));
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet httpGet
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "broadband info config";
	}// </editor-fold>
}
