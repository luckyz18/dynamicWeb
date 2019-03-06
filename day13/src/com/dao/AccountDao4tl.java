package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.utils.DataSourceUtils;
import com.utils.JdbcUtils;

public class AccountDao4tl {
	
	//一旦出现异常 钱就飞了  在service中添加事务 	

	/**
	 * 转入
	 * @param fromUser
	 * @param money
	 * @throws Exception
	 */
	public void accountOut(String fromUser, String money) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		//try 是因为finally
		try {
			conn=DataSourceUtils.getConnection();  //工具类获取的连接已经和线程绑定
			String sql = "update account set money = money - ? where name = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, money);
			st.setString(2, fromUser);
			int i = st.executeUpdate();
			System.out.println("chu:"+i);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;  //throw  是告诉调用方 这里有问题
		}finally {
			DataSourceUtils.closeResource(st, rs);
		}
		
	}

	/**
	 * 转入
	 * @param toUser
	 * @param money
	 * @throws Exception
	 */
	public void accountIn(String toUser, String money) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn=DataSourceUtils.getConnection(); 
			String sql = "update account set money = money + ? where name = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, money);
			st.setString(2, toUser);
			int i = st.executeUpdate();
			System.out.println("入:"+i);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;  
		}finally {
			DataSourceUtils.closeResource(st, rs);
		}
		
	}
	
}
