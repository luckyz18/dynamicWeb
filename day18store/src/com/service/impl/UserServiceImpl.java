package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.User;
import com.service.UserService;
import com.utils.MailUtils;

public class UserServiceImpl implements UserService {

	/**
	 * 注册
	 * @throws Exception 
	 */
	@Override
	public void register(User user) throws Exception {
		UserDao dao = new UserDaoImpl();
		dao.add(user);
		
		//发送邮件
		String emailMsg = "注册成功，点此激活码激活,  <a href='http://localhost:8080/day18store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	/**
	 * 激活用户
	 */
	@Override
	public User active(String code) throws Exception {
		UserDao dao = new UserDaoImpl();
		User user =  dao.getByCode(code);
		if (user == null) {
			return null;
		}
		
		user.setState(1);
		dao.updateUser(user);
		return user;
	}

	/**
	 * 用户登录
	 * @throws Exception 
	 */
	@Override
	public User login(String username, String password) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.getByUsernameAndPwd(username,password);
		 
	}
	

	
	
	
	

	
	
}
