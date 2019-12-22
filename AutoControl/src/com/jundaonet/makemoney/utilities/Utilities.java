/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jundaonet.makemoney.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import com.jundaonet.makemoney.log.MakeMoneyLog.MakeMoneyLog;

/**
 * 
 * @author zhy
 */
public class Utilities {

	public enum TimeType {

		/**
		 * Unixʱ�����ȷ����
		 */
		UnixTime,
		/**
		 * ʱ�����ȷ������
		 */
		Millisecond,
		/**
		 * ��ǰʱ��Ϊʱ���
		 */
		Now
	}

	public static boolean isIp(String str) {
		String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	/**
	 * ����ʱ���
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	public static String parseTime(String value, TimeType type) {
		if (type == TimeType.Now) {
			Date d = new Date();
			SimpleDateFormat format = new SimpleDateFormat(
					"MM.dd.yyyy HH:mm:ss");
			return format.format(d);
		} else {
			Calendar calendar = Calendar.getInstance();
			if (type == TimeType.UnixTime) {
				calendar.setTimeInMillis(Long.parseLong(value) * 1000);
			} else {
				calendar.setTimeInMillis(Long.parseLong(value));
			}

			return new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(calendar
					.getTime());
		}
	}

	@SuppressWarnings({ "BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch" })
	public static String MD5Encode(String str) {
		try {
			@SuppressWarnings("UnusedAssignment")
			MessageDigest messageDigest = null;
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();

			@SuppressWarnings("StringBufferMayBeStringBuilder")
			StringBuffer md5StrBuff = new StringBuffer();

			for (byte data : byteArray) {
				String hex = Integer.toHexString(data & 0xff);
				if (hex.length() == 1) {
					md5StrBuff.append("0");
				}
				md5StrBuff.append(hex);
			}

			return md5StrBuff.toString();
		} catch (Exception e) {
			MakeMoneyLog.Loge("MD5 encode error .... !!!:"
					+ e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param params
	 *            HashMap<String,String> params ��������������в���������ת��Ϊ�ַ�������
	 * @param dev_server_secret
	 *            String dev_server_secret �����������׺�̨���õ���Կ
	 * @return
	 */
	public static String getSignature(HashMap<String, String> params,
			String dev_server_secret) {
		// �Ƚ�����������������ֵ��������������
		Map<String, String> sortedParams = new TreeMap<String, String>(params);

		Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
		// �����������ֵ䣬�����в�����"key=value"��ʽƴ����һ��
		StringBuilder basestring = new StringBuilder();
		for (Map.Entry<String, String> param : entrys) {
			basestring.append(param.getKey()).append("=")
					.append(param.getValue());
		}
		basestring.append(dev_server_secret);
		return Utilities.MD5Encode(basestring.toString());
	}

	public static String HMACSHA1(HashMap<String, String> params, String key) {
		// �Ƚ�����������������ֵ��������������
		Map<String, String> sortedParams = new TreeMap<String, String>(params);

		Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
		// �����������ֵ䣬�����в�����"key=value"��ʽƴ����һ��
		StringBuilder basestring = new StringBuilder();
		for (Map.Entry<String, String> param : entrys) {
			basestring.append(param.getKey()).append(param.getValue());
		}

		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(basestring.toString().getBytes());
		} catch (InvalidKeyException e) {
			MakeMoneyLog.Loge("HMACSHA1 exception:" + e.getLocalizedMessage());
		} catch (NoSuchAlgorithmException ignore) {
			MakeMoneyLog.Loge("HMACSHA1 exception:"
					+ ignore.getLocalizedMessage());
		}

		return Base64.encodeBytes(byteHMAC);
	}

	public static boolean strIsEmpty(String str) {
		return (str == null) ? true : ((str.length() <= 0));
	}

	// ѹ��
	public static String compress(String str) throws IOException {
		if (strIsEmpty(str)) {
			return str;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString();
	}

	// ��ѹ��
	public static String uncompress(String str) throws IOException {
		if (strIsEmpty(str)) {
			return str;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		GZIPInputStream gunzip = new GZIPInputStream(in);

		// ���1M
		byte[] buffer = new byte[1024 * 1024];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toString();
	}

	@SuppressWarnings({ "UnusedAssignment", "UseSpecificCatch" })
//	public static Map<String, String> getIpInfo(String ip) throws IOException {
//		Map<String, String> map = new HashMap<String, String>();
//		if (!Utilities.strIsEmpty(ip)) {
//			String req = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
//			HttpGet httpGet = new HttpGet(req);
//			HttpResponse response = HttpClients.createDefault()
//					.execute(httpGet);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				String msg = EntityUtils
//						.toString(response.getEntity(), "utf-8");
//				if (!Utilities.strIsEmpty(msg)) {
//					Map<String, Map<String, String>> m = ((Map<String, Map<String, String>>) JSON
//							.parse(msg));
//					Object code = m.get("code");
//					if (code instanceof Integer) {
//						if (((Integer) code) == 0) {
//							map = ((Map<String, Map<String, String>>) JSON
//									.parse(msg)).get("data");
//						}
//					}
//				}
//			}
//			return map;
//		}
//		return map;
//	}

	private static int getLuhnCheckCode(String iccid) {
		int A = 0;
		int B = 0;
		int C = 0;

		boolean odd = true;
		for (char c : iccid.toCharArray()) {
			int num = Integer.parseInt(String.valueOf(c));
			if (odd) {
				B += num;
			} else {
				num = num * 2;
				if (num >= 10) {
					A += (num / 10 + (num % 10));
				} else {
					A += num;
				}
			}
			odd = !odd;
		}
		C = A + B;
		return (C * 9) % 10;
	}

	/*
	 * MNC��Mobile Network Code���ƶ�������룩������ʶ���ƶ��û����������ƶ�ͨ������2~3λ�� �й��ƶ�ϵͳʹ��00��02��07��
	 * �й���ͨGSMϵͳʹ��01��06�� �й�����CDMAϵͳʹ��03��05������4Gʹ��11�� �й���ͨϵͳʹ��20��
	 * 
	 * �й��ƶ���Ϊ����898602 ���й���ͨ��Ϊ�����й�����
	 */
	public static String getICCID(String pre, String iccid) {

		@SuppressWarnings("UnusedAssignment")
		String head = null;

		// �ƶ�
		if (pre.equals("46000") || pre.equals("46002") || pre.equals("46007")
				|| pre.equals("46020")) {
			head = "898600";
		} else if (pre.equals("46001") || pre.equals("46006")) {// ��ͨ
			head = "898601";
		} else if (pre.equals("46003") || pre.equals("46005")
				|| pre.equals("46011")) {// ����
			head = "898603";
		} else {
			head = "898601";
			MakeMoneyLog.Loge("ICCID", "unknow mnc:" + pre);
		}

		String str = head + iccid;
		return str + getLuhnCheckCode(iccid);
	}
}
