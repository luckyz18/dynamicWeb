package com.service;

import com.dao.UserDaoImpl;

public class UserServiceImpl {
	private UserDaoImpl dao;
	public void setDao(UserDaoImpl dao) {
		this.dao = dao;
	}
	
	public void save() {
		System.out.println("service执行....");
		dao.save2();
	}
	
}
