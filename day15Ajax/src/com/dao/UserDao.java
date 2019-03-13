package com.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.domain.User;
import com.utils.DataSourceUtils;

public class UserDao {

	public User getUserByUserName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username);
		
	}
	
}
