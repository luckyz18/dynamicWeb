package com.service;

import java.sql.SQLException;

import com.dao.UserDao;
import com.domain.User;

public class LoginService {

	public User login(String username, String password) throws Exception {
		
		UserDao dao = new UserDao();
		return dao.getUserByusernameAndPassword(username,password);
	}

}
