package com.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dao.UserDao;
import com.domain.User;
import com.utils.HibernateUtils;

public class UserService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		User exitUser = null;
		try {
			UserDao dao  = new UserDao();
			exitUser = dao.getByNameAndPwd(user);
			tr.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return exitUser;
		
	}
	
	/*for test*/
	@Test
	public void run() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("12345");
		User existUser = this.login(user);
		if (existUser != null) {
			System.out.println("登录成功、、、、");
		}
	}
}	
