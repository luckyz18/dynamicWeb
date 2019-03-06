package com.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.utils.DataSourceUtils;

public class AccountDao4db {

	public void accountIn(String toUser, String money) throws Exception {
		QueryRunner qr = new QueryRunner();  //不加参数  手动开启事务
		String sql = "update account set money = money + ? where name=? ";
		qr.update(DataSourceUtils.getConnection(), sql, money,toUser);
		
		
	}

	public void accountOut(String fromUser, String money) throws Exception {
		QueryRunner qr = new QueryRunner();  //不加参数  手动开启事务
		String sql = "update account set money = money - ? where name=? ";
		qr.update(DataSourceUtils.getConnection(), sql, money,fromUser);
		
	}

}
