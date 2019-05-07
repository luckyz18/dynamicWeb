package com.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.Userdao;
import com.domain.User;
import com.utils.HibernateUtils;

public class UserService {
	//测试在业务层开启事务：threadlocal
	public static void save(User u1, User u2) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Userdao dao = new Userdao();
		try {
			dao.save1(u1);
			int i = 10 /0;
			dao.save2(u2);
			tr.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
			//释放资源  自动释放
		}
	}
	
}
