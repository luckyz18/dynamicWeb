package com.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	/**
	 * 获取连接池
	 * @return
	 */
	public static DataSource getDataSource() {		
		return ds;
	}
	
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	/**
	 * 释放资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeResource(Connection conn,Statement st,ResultSet rs){
		closeResultSet(rs);
		closeStatement(st);
		closeConn(conn);
	}
	
	/**
	 * 释放连接
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
		
		
	}
	/**
	 * 释放语句执行者连接
	 * @param st
	 */
	public static void closeStatement(Statement st){
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st = null;
		}
		
	}
	/**
	 *  释放处理结果集
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
			
	}
	
}
