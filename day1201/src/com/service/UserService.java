package com.service;

import java.sql.SQLException;

import com.dao.UserDao;
import com.domain.User;

public class UserService {


	public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
		// TODO getUserByUsernameAndPwd
		return new UserDao().getUserByUsernameAndPwd(username,password);
	}

	

}
