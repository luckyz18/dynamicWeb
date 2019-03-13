package com.service;

import com.dao.UserDao;
import com.domain.User;

public class CheckUserName4Ajax {

	public User checkUsername(String username) throws Exception {
		return new UserDao().getUserByUserName(username);
	}
	
}
