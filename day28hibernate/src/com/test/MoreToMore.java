package com.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.domain.Role;
import com.domain.User;
import com.utils.HibernateUtils;

public class MoreToMore {
	/**
	 * 配置多对多
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		User u1 = new User();
		u1.setName("张三");
		User u2 = new User();
		u2.setName("赵四");
		
		Role r1= new Role();
		r1.setRole_name("演员");
		Role r2= new Role();
		r2.setRole_name("导演");
		//双向关联
		u1.getRoles().add(r1);
		u1.getRoles().add(r2);
		u2.getRoles().add(r2);
		
		r1.getUsers().add(u1);
		r2.getUsers().add(u1);
		r2.getUsers().add(u2);
		
		session.save(u1);
		session.save(u2);
		session.save(r1);
		session.save(r2);
		
		tr.commit();
		
	}
	
	/**
	 * 级联保存
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		User u1 = new User();
		u1.setName("张三");
		User u2 = new User();
		u2.setName("赵四");
		
		Role r1= new Role();
		r1.setRole_name("演员");
		Role r2= new Role();
		r2.setRole_name("导演");
		
		u1.getRoles().add(r1);
		u1.getRoles().add(r2);
		u2.getRoles().add(r2);
		
		session.save(u1);
		session.save(u2);
		tr.commit();
		
	}
	/**
	 * 操作中间表
	 */
	@Test
	public void run3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		User u1 = session.get(User.class, 12);
		Role r2 = session.get(Role.class, 2L);
		u1.getRoles().remove(r2);
		
		tr.commit();
		
	}


}
