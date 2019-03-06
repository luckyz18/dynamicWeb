package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.JdbcUtils;

public class AccountDao {
	
	//一旦出现异常 钱就飞了  在service中添加事务 	

	/**
	 * 转入
	 * @param fromUser
	 * @param money
	 * @throws Exception
	 */
	public void accountIn(Connection conn, String fromUser, String money) throws Exception {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		//try 是因为finally
		try {
			conn=(Connection) JdbcUtils.getConnection();
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
//			JdbcUtils.closeResource(conn, st, rs);   
			JdbcUtils.closeStatement(st);
		}
		
	}

	/**
	 * 转入
	 * @param toUser
	 * @param money
	 * @throws Exception
	 */
	public void accountOut(Connection conn,String toUser, String money) throws Exception {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn=(Connection) JdbcUtils.getConnection();
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
//			JdbcUtils.closeResource(conn, st, rs);   
			JdbcUtils.closeStatement(st);
		}
		
	}
	
}
