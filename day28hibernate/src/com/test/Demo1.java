package com.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.domain.Customer;
import com.domain.User;
import com.utils.HibernateUtils;

public class Demo1 {
	
	/**
	 * 查询
	 */
	@Test
	public void TestQuery() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		
//		Query query = session.createQuery("from User");         //查询所有  from 是javabean
//		Query query = session.createQuery("from User where age > ? ");  //条件查询
//		query.setInteger(0, 11);
		
//		Query query = session.createQuery("from User where age > :ageee ");
//		query.setInteger("ageee", 12);
		
//		Query query = session.createQuery("from User where age > ? ");
//		query.setParameter(0, 12);
		
		Criteria criteria = session.createCriteria(User.class);
		/*排序*/
//		criteria.addOrder(Order.desc("id"));
		/*分页*/
//		criteria.setFirstResult(0);
//		criteria.setMaxResults(3);
		
		/*统计查询*/
//		criteria.setProjection(Projections.rowCount());
//		Object obj = criteria.uniqueResult();
//		Long obj1 = (Long)obj;
//		int count = obj1.intValue();
//		System.out.println(count);
		
		/*离线查询*/
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
//		Criteria criteria2 = detachedCriteria.getExecutableCriteria(session);
//		List<User> list = criteria2.list();
		
		/*多表查询 */
		/*内连接 list里是数组形式*/
		/*迫切内连接  list里是对象*/
		/*外连接*/
		/*迫切外连接*/
		
//		List list = session.createCriteria(User.class)
//			    .setProjection( Property.forName("name"))
//			    .list();
//
//		for (Object l : list) {
//			System.out.println(l);
//		}

		
//		List<User> list =criteria.list();
//		for (User user : list) {
//			System.out.println(user.toString());
//		}
		tr.commit();
		session.close();
	}
	
	
	/**
	 *run2 run3 乐观锁 ：避免丢失更新 
	 *User持久类加version 配置文件加version
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		User user = session.get(User.class, 1);
		user.setName("run1");
		System.out.println(user.getName());
		
		tr.commit();
		//释放资源
		session.close();
	}
	@Test
	public void run3() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		User user = session.get(User.class, 1);
		user.setAge(30);
		System.out.println(user.getAge());
		tr.commit();
		//释放资源
		session.close();
	}
	
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
	
	/**
	 * 证明 一级缓存的存在  持久化对象自动更新数据库
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		User u1 = session.get(User.class, 1);
		System.out.println(u1.getName());
		u1.setName("大");
//		User u2 = session.get(User.class, 1);
//		System.out.println(u2.getName());
		tr.commit();
		session.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
