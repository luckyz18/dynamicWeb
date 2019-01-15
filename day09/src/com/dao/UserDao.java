package com.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.domain.User;
import com.utils.DataSourceUtils;

public class UserDao {

	public User getUserByusernameAndPassword(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
//		System.out.println("111");
		String sql = "select * from user where username = ? and password = ?";
		User user = qr.query(sql, new BeanHandler<>(User.class), username,password);	
		return user;
	}
	
}
