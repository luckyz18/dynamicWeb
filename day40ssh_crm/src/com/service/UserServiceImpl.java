package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.domain.User;
import com.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 注册校验
	 */
	
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}

	/**
	 * 	注册用户
	 * 	密码加密
	 */
	
	public void save(User user) {
		String user_password = user.getUser_password();
		user.setUser_password(MD5Utils.md5(user_password));
		user.setUser_state("1");
		userDao.save(user);
		
	}

	/**
	 * 登录  密码加密的
	 */
	
	public User login(User user) {
		String user_password = user.getUser_password();
		user.setUser_password(MD5Utils.md5(user_password));
		return userDao.login(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
