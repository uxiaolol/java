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
	 * ��Ӧ�ͻ��˵Ĵ������󣬷���Map���� ��ϸ����������
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
	 * ��Ӧ�𣬷��ص�һ�ַ��������ڴ����������
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
