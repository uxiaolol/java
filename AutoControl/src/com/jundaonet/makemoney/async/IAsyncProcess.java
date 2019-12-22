/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jundaonet.makemoney.async;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Administrator
 */
public interface IAsyncProcess {

	/**
	 * 响应客户端的处理请求，返回Map对象 详细描述处理结果
	 * 
	 * @param request
	 * @param error
	 * @return
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 * @throws InterruptedException
	 */
	Map<String, String> doNormalRunable(HttpServletRequest request,
			StringBuilder error) throws IOException, SQLException,
			InterruptedException, Exception;

	/**
	 * 简单应答，返回单一字符串，便于触摸精灵解析
	 * 
	 * @param request
	 * @param error
	 * @return
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 * @throws InterruptedException
	 */
	String doTouchelfRunable(HttpServletRequest request) throws IOException,
			SQLException, InterruptedException, Exception;
}
