/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jundaonet.makemoney.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 启动时，监听器即被创建：线程池、连接池
 *
 * @author zhaohengyi
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));
        servletContextEvent.getServletContext().setAttribute("executor", executor);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.getServletContext().getAttribute("executor");
        executor.shutdown();
    }
}
