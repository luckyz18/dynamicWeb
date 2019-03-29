package com.dao;

import com.domain.User;

public interface UserDao {

	/**
	 * 注册
	 * @param user
	 * @throws Exception 
	 */
	void add(User user) throws Exception;

	User getByCode(String code) throws Exception;

	void updateUser(User user) throws Exception;

	User getByUsernameAndPwd(String username, String password) throws Exception;

}
