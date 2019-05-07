package com.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.domain.Customer;
import com.utils.HibernateUtils;

public class HqlQuery {
	/**
	 * Hql 聚合函数查询
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Number> list = session.createQuery("select count(*) from Linkman").list();
		Long count = list.get(0).longValue();
		System.out.println(count);
		tr.commit();
		
	}
	/**
	 * 多表查询
	 * 内连接：迫切、非迫切
	 * customer  linkman 1对多  主外键关联 
	 * 从customer 拿linkman
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Object[]> list = session.createQuery("from Customer c inner join c.linkmans lkm ").list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tr.commit();
	}
	
	@Test
	public void run4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		Query query = session.createQuery("from Customer c inner join c.linkmans lkm ");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tr.commit();
	}
	/**
	 * 迫切内连接 fetch
	 * 返回对象
	 */
	@Test
	public void run3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Customer> list = session.createQuery("from Customer c inner join fetch c.linkmans lkm ").list();
		Set<Customer> set = new HashSet<Customer>(list);
		for (Customer linkman : set) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	
}
