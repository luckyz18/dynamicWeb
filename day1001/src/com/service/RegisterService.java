package com.service;

import java.sql.SQLException;


import com.dao.UserDao;
import com.domain.User;

public class RegisterService {
	public int register(User user) throws Exception {
		UserDao dao = new UserDao();
		return dao.addUsernameAndPassword(user);
		
	}

	
}
