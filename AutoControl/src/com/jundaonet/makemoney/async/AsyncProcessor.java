/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jundaonet.makemoney.async;

import com.jundaonet.makemoney.log.MakeMoneyLog.MakeMoneyLog;
import com.jundaonet.makemoney.utilities.Utilities;
import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Administrator
 */
public class AsyncProcessor implements Runnable {

	private final String TAG = "AsyncProcessor";
	private final AsyncContext asyncContext;
	private final IAsyncProcess asyncProcess;

	public AsyncProcessor(AsyncContext asyncCtx, IAsyncProcess run) {
		this.asyncContext = asyncCtx;
		this.asyncProcess = run;
	}

	// 响应客户端请求
	@SuppressWarnings("IncompatibleEquals")
	private void doNormalRun(HttpServletRequest request,
			HttpServletResponse resp) {
		boolean isOk = false;
		StringBuilder error = new StringBuilder("ok");
		Map<String, String> ret = new HashMap<String, String>();
		try {
			ret = this.asyncProcess.doNormalRunable(request, error);
			if (!ret.isEmpty()) {
				isOk = true;
			} else {
				if (error.equals("ok")) {
					error.delete(0, error.length());
					error.append("unknown error ...");
				}
			}
		} catch (Exception e) {
			error.delete(0, error.length());
			error.append(e.getLocalizedMessage());
			MakeMoneyLog.Logd(TAG, e.getLocalizedMessage());
		}

		if (ret.isEmpty()) {
			ret.put("msg", error.toString());
		}

		try {
			String msg = JSON.toJSONString(ret);
			resp.setHeader("status", isOk ? "ok" : "error");
			resp.setHeader("check", Utilities.MD5Encode(msg));
			PrintWriter out = resp.getWriter();
			out.write(URLEncoder.encode(msg, "utf-8"));
			// out.write(Utilities.compress(ret));
			out.flush();
			out.close();
		} catch (Exception e) {
			MakeMoneyLog.Logd(TAG, e.getLocalizedMessage());
		} finally {
			this.asyncContext.complete();
		}
	}

	// 响应触摸精灵请求
	private void doTouchelfRun(HttpServletRequest request,
			HttpServletResponse resp) {
		@SuppressWarnings("UnusedAssignment")
		String msg = null;
		try {
			msg = this.asyncProcess.doTouchelfRunable(request);
		} catch (Exception e) {
			msg = "error_inner_exception";
		}

		try {
			PrintWriter out = resp.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (Exception e) {
			MakeMoneyLog.Logd(TAG, e.getLocalizedMessage());
		} finally {
			this.asyncContext.complete();
		}
	}

	@SuppressWarnings({ "UseSpecificCatch", "IncompatibleEquals" })
	public void run() {
		HttpServletRequest request = (HttpServletRequest) this.asyncContext
				.getRequest();
		HttpServletResponse resp = (HttpServletResponse) this.asyncContext
				.getResponse();

		String token = request.getParameter("token");
		if (!Utilities.strIsEmpty(token) && token.equalsIgnoreCase("touchelf")) {
			this.doTouchelfRun(request, resp);
		} else {
			this.doNormalRun(request, resp);
		}
	}
}
