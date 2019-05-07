package com.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.domain.Customer;
import com.domain.Linkman;
import com.utils.HibernateUtils;

/**
 * 延迟加载
 */
public class LazyLoad {
	/**
	 * 类级别的延迟加载
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer c = session.get(Customer.class, 4L);
		System.out.println("--------------");
		System.out.println(c.getCust_name());
		tr.commit();
	}
	/**
	 * 类级别的延迟加载
	 * load() 懒加载
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer c = session.load(Customer.class, 4L);
		System.out.println("--------------");
		System.out.println(c.getCust_name());
		tr.commit();
	}
	
	/**
	 * 关联级别的延迟加载
	 * 默认是懒加载
	 */
	@Test
	public void run3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer c = session.get(Customer.class, 5L);
		System.out.println("--------------");
		System.out.println(c.getLinkmans().size());
		
		tr.commit();
		
	}
	
	
	/**
	 * hibernate框架的查询策略
	 * set标签
	 * fetch: 查询语句的形式  默认select
	 * lazy: 查询的时机
	 */
	@Test
	public void run4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = session.createQuery("from Customer ").list();
		for (Customer c : list) {
			System.out.println(c.getLinkmans().size());
		}
		tr.commit();
	}
	
	/**
	 * hibernate框架的查询策略
	 * set标签
	 * fetch: join  迫切左连接
	 * lazy: 无论什么取值 
	 */
	@Test
	public void run5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = session.createQuery("from Customer ").list();
		for (Customer c : list) {
			System.out.println(c.getLinkmans().size());
		}
		tr.commit();
	}
	
	/**
	 * hibernate框架的查询策略
	 * set标签
	 * fetch: subselect 子查询
	 * lazy: 
	 */
	@Test
	public void run6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = session.createQuery("from Customer ").list();
		for (Customer c : list) {
			System.out.println(c.getLinkmans().size());
		}
		tr.commit();
	}
	
	/**
	 * hibernate框架的查询策略
	 * many-to-one 标签
	 * fetch:select
	 * lazy: proxy看一方class的lazy值 / false
	 */
	@Test
	public void run7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman lkm = session.get(Linkman.class, 5L);
		System.out.println(lkm.getCustomer().getCust_name());
		
		tr.commit();
	}
	
	/**
	 * hibernate框架的查询策略
	 * many-to-one 标签
	 * fetch:join  左外连接
	 * lazy: 无论取何值
	 */
	@Test
	public void run8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman lkm = session.get(Linkman.class, 5L);
		System.out.println(lkm.getCustomer().getCust_name());
		
		tr.commit();
	}
	
	
}
