package com.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.domain.User;
import com.utils.DataSourceUtils;

public class UserDao {

	public User getUserByusernameAndPassword(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		User user = qr.query(sql, new BeanHandler<>(User.class), username,password);	
		return user;
	}
	
	
	public int addUsernameAndPassword(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user (username,password,email,name,sex,birthday,hobby) values(?,?,?,?,?,?,?)"; 
		int i = qr.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),
				user.getSex(),user.getBirthday(),user.getHobby());
		return i;

	}
}

	

