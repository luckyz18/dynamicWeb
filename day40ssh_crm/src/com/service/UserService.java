package com.service;

import java.util.List;

import com.domain.User;

public interface UserService {

	User checkCode(String user_code);

	void save(User user);

	User login(User user);

	List<User> findAll();
}
