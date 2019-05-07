package com.dao;

import org.hibernate.Session;

import com.domain.User;
import com.utils.HibernateUtils;

public class Userdao {

	public void save1(User u1) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(u1);
	}

	public void save2(User u2) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(u2);
	}
	
}
