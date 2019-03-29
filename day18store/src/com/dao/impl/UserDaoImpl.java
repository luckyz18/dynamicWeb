package com.dao.impl;

import java.sql.SQLException;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.UserDao;
import com.domain.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{

	/**
	 * 注册
	 * @throws Exception 
	 */
	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}


	/**
	 * 根据激活码查找用户
	 * @throws Exception 
	 */
	@Override
	public User getByCode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where code= ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), code);
	}

	/**
	 * 更新用户
	 */
	@Override
	public void updateUser(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set username = ?,password = ? ,name=?,email=?,birthday = ?,state = ?,code=? where uid =? ";
		qr.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),
				user.getState(),null,user.getUid());
	}


	/**
	 * 通过用户名 密码 查询用户
	 * @throws SQLException 
	 */
	@Override
	public User getByUsernameAndPwd(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username,password);
		
	}

}
