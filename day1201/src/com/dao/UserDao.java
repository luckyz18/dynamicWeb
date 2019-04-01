package com.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.domain.User;
import com.utils.DataSourceUtils;

public class UserDao {


	public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from User where username = ? and password = ?";
		User user = qr.query(sql, new BeanHandler<>(User.class), username,password);		
		return user;
	}

}
