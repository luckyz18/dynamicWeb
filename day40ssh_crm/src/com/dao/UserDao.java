package com.dao;

import java.util.List;

import com.domain.User;

public interface UserDao extends BaseDao<User>{

	User checkCode(String user_code);

	void save(User user);

	User login(User user);

	

}
