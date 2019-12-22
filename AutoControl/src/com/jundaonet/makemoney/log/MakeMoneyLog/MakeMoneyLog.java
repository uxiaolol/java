package com.jundaonet.makemoney.log.MakeMoneyLog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author zhy
 */
public class MakeMoneyLog {

	/**
	 * 调试日志
	 * 
	 * @param tag
	 * @param args
	 */
	public static void Logd(String tag, Object... args) {
		/*
		 * Date d = new Date(); SimpleDateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"); StringBuilder log = new
		 * StringBuilder(); log.append("[INFO:]"); log.append(format.format(d));
		 * log.append(" - "); log.append("Thread name:");
		 * log.append(Thread.currentThread().getName()); log.append(" - ");
		 * log.append(String.format(msg, args)); System.out.println(log);
		 */
	}

	/**
	 * 错误日志记录
	 * 
	 * @param tag
	 * @param f
	 * @param args
	 */
	@SuppressWarnings({ "empty-statement" })
	public synchronized static void Loge(String tag, String f, String args) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		StringBuilder log = new StringBuilder();
		log.append("[ERROR:]");
		log.append(format.format(d));
		log.append(" - ");
		log.append("Thread name:");
		log.append(Thread.currentThread().getName());
		log.append(" - ");
		log.append(tag).append("-").append(String.format(f, args));
		System.err.println(log);
	}

	public synchronized static void Loge(String tag, String msg) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		StringBuilder log = new StringBuilder();
		log.append("[ERROR:]");
		log.append(format.format(d));
		log.append(" - ");
		log.append("Thread name:");
		log.append(Thread.currentThread().getName());
		log.append(" - ");
		log.append(tag).append("-").append(msg);
		System.err.println(log);
	}

	@SuppressWarnings({ "empty-statement",
			"StringConcatenationInsideStringBufferAppend" })
	public synchronized static void Loge(String tag, String f, Object... args) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		StringBuilder log = new StringBuilder();
		log.append("[ERROR:]");
		log.append(format.format(d));
		log.append(" - ");
		log.append("Thread name:");
		log.append(Thread.currentThread().getName());
		log.append(" - ");
		log.append(tag).append("-").append(String.format(f, args));
		System.err.println(log);
	}

	@SuppressWarnings({ "empty-statement" })
	public synchronized static void Loge(String msg) {
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		StringBuilder log = new StringBuilder();
		log.append("[ERROR:]");
		log.append(format.format(d));
		log.append(" - ");
		log.append("Thread name:");
		log.append(Thread.currentThread().getName());
		log.append(" - ");
		log.append(msg);
		System.err.println(log);
	}

	/**
	 * 通知日志记录
	 * 
	 * @param tag
	 * @param args
	 */
	@SuppressWarnings("empty-statement")
	public synchronized static void Logi(String tag, Object... args) {

	}
}
