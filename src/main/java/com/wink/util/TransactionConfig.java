package com.wink.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: Mr.Ye
 * @Description: TODO(控制线程安全的类)
 */
//用ThreadLocal线程内部类 来控制 取到的connection是一个线程中的connection，这样可以安全的控制事务
public class TransactionConfig {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection(){
        Connection conn = threadLocal.get();
        if (conn == null){
            try {
                conn = JDBCUtils.getConnection();
                threadLocal.set(conn);//从DBCP线程池中取出一个connn放入到ThreadLocal的Map之中
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //开启事务
    public static void startTransaction(){
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);//关闭自动提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //提交事务
    public static void commit(){
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //回滚
    public static void rollback(){
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //释放
    public static void close(){
        try {
            getConnection().close();//连接关闭，然后将threadLocal中的connection 清除
            threadLocal.remove();//threadLocal(当前线程中清除掉connection)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
