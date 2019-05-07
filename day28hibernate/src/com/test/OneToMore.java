package com.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.domain.Customer;
import com.domain.Linkman;
import com.utils.HibernateUtils;

public class OneToMore {
	
	/**
	 * 	一对多  双向关联
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer c1 = new Customer();
		c1.setCust_name("美美");
		
		Linkman l1 = new Linkman();
		Linkman l2 = new Linkman();
		l1.setLkm_name("熊大");
		l2.setLkm_name("熊二");
		/*双向关联*/
		c1.getLinkmans().add(l1);
		c1.getLinkmans().add(l2);
		l1.setCustomer(c1);
		l2.setCustomer(c1);
		
		session.save(c1);
		session.save(l1);
		session.save(l2);
		
		tr.commit();
	}
	
	/*一对多  级联保存*/
	@Test
	public void run2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer c1 = new Customer();
		c1.setCust_name("美美");
		
		Linkman l1 = new Linkman();
		Linkman l2 = new Linkman();
		l1.setLkm_name("熊大");
		l2.setLkm_name("熊二");
	
//		c1.getLinkmans().add(l1);
//		c1.getLinkmans().add(l2);
//		session.save(c1);     //在 customer配置级联
		/*一方放弃维护外键  只能在多方配置级联*/
		l1.setCustomer(c1);
		l2.setCustomer(c1);
		session.save(l1);
		session.save(l2);
		tr.commit();
	}
	
	/*一对多  孤儿删除*/
	@Test
	public void run3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer  c1=session.get(Customer.class, 1L);
		Linkman linkman = session.get(Linkman.class,1L);
		c1.getLinkmans().remove(linkman);
		tr.commit();
	}
	
	/*一对多  1 方放弃维护外键*/
	@Test
	public void run4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer  c2=session.get(Customer.class, 2L);
		Linkman linkman = session.get(Linkman.class,1L);
		//做双向的关联
		c2.getLinkmans().add(linkman);
		linkman.setCustomer(c2);
		
		tr.commit();
	}
	
}	
