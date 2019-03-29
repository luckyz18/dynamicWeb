package com.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.glassfish.external.statistics.Statistic;

import jdk.internal.dynalink.beans.StaticClass;

public class DataSourceUtils {
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal();
	
	/**
	 * 获取数据源   通过线程开启事务
	 * @return 连接池
	 */
	public static DataSource getDataSource(){	
		return ds;
	}
	
	/**
	 * 获取连接
	 * @return 连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		//获取连接的时候就绑定线程
		Connection conn = tl.get();
		if (conn == null) {
			//第一次获取 创建一个连接和当前线程绑定
			conn= ds.getConnection();
			tl.set(conn);	
		}
		
		return conn;
	}
	
	
	
	/**
	 * 释放资源
	 * 
	 * @param conn
	 *            连接
	 * @param st
	 *            语句执行者
	 * @param rs
	 *            结果集
	 */
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResource(st, rs);
		closeConn(conn);
	}
	
	/**
	 * 释放两个资源
	 * @param st
	 * @param rs
	 */
	public static void closeResource(Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
	}
	

	/**
	 * 释放连接
	 * 
	 * @param conn
	 *  连接
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//解除绑定
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}

	}

	/**
	 * 释放语句执行者
	 * 
	 * @param st
	 *            语句执行者
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}

	}

	/**
	 * 释放结果集
	 * 
	 * @param rs
	 *            结果集
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}

	}
	
	
	
	/**
	 * 开启事务
	 */
	public static void startTransction() {
		
		try {
			//获取连接 开启事务
			getConnection().setAutoCommit(false);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitAndClose() {
		
		try {
			//获取连接
			Connection conn = tl.get();
			//提交事务
			conn.commit();
			//释放资源
			conn.close();
			//解除绑定
			tl.remove();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 事务回滚
	 */
	public static void rollbackAndClose() {
		try {
			//获取连接
			Connection conn = tl.get();
			//事务回滚
			conn.rollback();
			//释放资源
			conn.close();
			//解除绑定
			tl.remove();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		 
	}
	
}

