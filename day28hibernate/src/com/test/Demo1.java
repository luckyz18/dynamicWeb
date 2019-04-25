package com.test;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.domain.Customer;
import com.utils.HibernateUtils;

public class Demo1 {
	@Test
	public void save2() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		Customer c = new Customer();
		c.setCust_name("测试2");
		session.save(c);
		
		tr.commit();
		//释放资源
		session.close();
		
	}
	
	
	@Test
	public void save() {
		//加载配置
		Configuration cfg = new Configuration();
		cfg.configure();	       //不给参数就默认加载hibernate.cfg.xml文件，
		
		//或者简写  方法链
//		Configuration cfg = new Configuration().configure();
		
		//创建 session_Factory  session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		//开启事务
		Transaction tr = session.getTransaction();
		tr.begin();
		
		//业务
		Customer customer = new Customer();
		customer.setCust_name("测试1");
		customer.setCust_level("2");
		customer.setCust_mobile("123456");
		session.save(customer);
		
		//提交事务
		tr.commit();
		//释放资源
		session.close();
		factory.close();
	}
	
	/**
	 * 测试查询
	 */
	@Test
	public void testGet() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		Customer c = session.get(Customer.class, 96L);
		System.out.println(c);
		tr.commit();
		session.close();
	}
	
	/**
	 * 测试删除
	 */
	@Test
	public void testDel() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		Customer c = session.get(Customer.class, 97L);
		session.delete(c);
		tr.commit();
		session.close();
	}
	
	/**
	 * 测试更新  要先查询再更新
	 */
	@Test
	public void testUpdate() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		
		Customer c = session.get(Customer.class, 96L);
		c.setCust_name("小明");
		c.setCust_level("1");
		session.update(c);
		
		tr.commit();
		session.close();
	}
	
	/**
	 * 测试添加或更新  要先查询再更新
	 * 提供主键就 更新  主键要存在
	 */
	@Test
	public void testSaveOrUpdate() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		
		Customer c = session.get(Customer.class, 96L);
		c.setCust_name("小仓");
		c.setCust_level("1");
		session.saveOrUpdate(c);
		
		tr.commit();
		session.close();
	}
	
	@Test
	public void save3() {
		Session session = null;
		Transaction tr = null;
		try {
			session = HibernateUtils.getSession();
		    tr = session.beginTransaction();
			
		    Customer c = new Customer();
		    c.setCust_name("哈哈");
		    session.save(c);
		    tr.commit();
			
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}finally {
			//释放资源
			session.close();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
