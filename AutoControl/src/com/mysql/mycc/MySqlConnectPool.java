package com.mysql.mycc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

import com.jundaonet.makemoney.log.MakeMoneyLog.MakeMoneyLog;

/**
 *
 * @author zhy
 */
public class MySqlConnectPool {

    private final String DRIVER = "com.mysql.jdbc.Driver";
    //private final String URL = "jdbc:mysql://192.168.1.8:3306/advertisement?user=root&password=zhaohengyi1988&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
    //private final String URL = "jdbc:mysql://120.24.58.164:3306/advertisement?user=root&password=zhaohengyi1988&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
    private final String URL = "jdbc:mysql://127.0.0.1:3306/advertisement?user=root&password=zhaohengyi1988&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";

    private final Timer timer = new Timer();

    // 连接池的大小
    private final int PoolSize = 10;

    // 存放连接池中数据库连接,线程安全
    private final List<Connection> ConnectionList = Collections.synchronizedList(new ArrayList<Connection>());

    private final String TAG = "[Mysql:]";

    private int ConnectIndex = 0;

    //单例对象
    private volatile static MySqlConnectPool singleton = null;

    /**
     * 获取MySqlConnectPool 单例对象
     *
     * @return
     */
    public static MySqlConnectPool Instance() {
        if (singleton == null) {
            synchronized (MySqlConnectPool.class) {
                if (singleton == null) {
                    singleton = new MySqlConnectPool();
                }
            }
        }
        return singleton;
    }

    @SuppressWarnings("UseSpecificCatch")
    public MySqlConnectPool() {
        MakeMoneyLog.Logd(TAG, "Before create connection poll .... ");

        for (int i = 0; i < PoolSize; i++) {
            try {
                Class.forName(DRIVER);
                this.ConnectionList.add(DriverManager.getConnection(URL));
            } catch (Exception e) {
                MakeMoneyLog.Loge(TAG, "CreateConnectPool error: %s", e.getLocalizedMessage());
            }
        }

        if (ConnectionList.size() != PoolSize) {
            MakeMoneyLog.Loge(TAG, "Fatil error : create connect pool ...");
            return;
        }

        MakeMoneyLog.Logd(TAG, "Create connection poll sucess .... ");

        //定时扫描连接池中的链接，保持连接池的数量恒定
        timer.schedule(new TimerTask() {
            @SuppressWarnings("null")
            public void run() {
                Thread.currentThread().setName("Monitor sql pool timer");
                for (int i = 0; i < PoolSize; i++) {
                    Connection con = ConnectionList.get(i);
                    synchronized (con) {
                        if (con != null) {
                            try {
                                if (!con.isValid(10)) {
                                    con.close();
                                    //ConnectionList.remove(con);
                                    ConnectionList.remove(i);

                                    Class.forName(DRIVER);
                                    ConnectionList.add(i, DriverManager.getConnection(URL));
                                }
                            } catch (Exception ex) {
                                MakeMoneyLog.Loge(TAG, "In Sql Pool, unkenow eror:%s", ex.getLocalizedMessage());
                            }
                        }
                    }
                }
            }//每隔0.5分钟进行一次扫描
        }, 1000 * 10, 1000 * 30);
    }

    /**
     * 从连接池中取出一个链接
     *
     * @return
     * @throws java.lang.InterruptedException
     */
    @SuppressWarnings({"UnusedAssignment"})
    public synchronized Connection getConnection() throws InterruptedException {
        ConnectIndex = ((ConnectIndex == PoolSize - 1) ? 0 : ++ConnectIndex);
        return ConnectionList.get(ConnectIndex);
    }

    /**
     * 执行sql语句，更新
     *
     * @param sql
     * @return
     * @throws java.lang.InterruptedException
     * @throws java.sql.SQLException
     */
    public int executeUpdate(String sql) throws InterruptedException, SQLException, Exception {
        int ret = 0;
        Connection con = MySqlConnectPool.Instance().getConnection();
        if (null != con) {
            synchronized (con) {
                boolean isOK = true;
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                try {
                    ret = preparedStatement.executeUpdate();
                } catch (Exception e) {
                    isOK = false;
                    String error = String.format("ExecuteUpdate error:%s\r\n\t With sql:[%s]", e.getLocalizedMessage(), sql);
                    MakeMoneyLog.Loge(TAG, error);
                } finally {
                    preparedStatement.close();
                }

                if (!isOK) {
                    throw new Exception("executeQuery error");
                }
            }
        } else {
            MakeMoneyLog.Loge(TAG, "ExecuteUpdate failed: connection is null ...");
        }

        return ret;
    }

    /**
     * 查询
     *
     * @param sql
     * @return
     * @throws InterruptedException
     * @throws java.sql.SQLException
     */
    public List<HashMap<String, String>> executeQuery(String sql) throws InterruptedException, SQLException, Exception {
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        Connection con = MySqlConnectPool.Instance().getConnection();
        if (null != con) {
            synchronized (con) {
                boolean isOK = true;
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                try {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ResultSetMetaData m = resultSet.getMetaData();
                    int columns = m.getColumnCount();
                    List<String> names = new ArrayList<String>();
                    for (int i = 0; i < columns; i++) {
                        names.add(m.getColumnName(i + 1));
                    }

                    while (resultSet.next()) {
                        HashMap<String, String> h = new HashMap<String, String>();
                        for (String key : names) {
                            h.put(key, resultSet.getString(key));
                        }
                        result.add(h);
                    }
                    resultSet.close();
                } catch (Exception e) {
                    isOK = false;
                    String error = String.format("ExecuteQuery error:%s\r\n\t With sql:[%s]", e.getLocalizedMessage(), sql);
                    MakeMoneyLog.Loge(TAG, error);
                } finally {
                    preparedStatement.close();
                }

                if (!isOK) {
                    throw new Exception("executeQuery error");
                }
            }
        } else {
            MakeMoneyLog.Loge(TAG, "ExecuteQuery failed: connection is null ...");
        }
        return result;
    }
}

