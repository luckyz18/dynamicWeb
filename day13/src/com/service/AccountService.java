package com.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dao.AccountDao;

import com.utils.JdbcUtils;

public class AccountService {

	public void account(String fromUser, String toUser, String money) throws Exception {
		AccountDao dao = new AccountDao();
		
		//java 添加事务
		Connection conn = null;
		try {
			//开启事务
			conn = JdbcUtils.getConnection();			
			conn.setAutoCommit(false);			
			
			dao.accountIn(conn,fromUser,money); //转出
//			int i = 1/0;
			dao.accountOut(conn,toUser,money);  //转入
			//事务提交
			conn.commit();
			JdbcUtils.closeConn(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
			//有异常 回滚
			conn.rollback();
			throw e;
		}
		
		
	}

}
