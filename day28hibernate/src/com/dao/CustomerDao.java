package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.domain.Customer;
import com.utils.HibernateUtils;

public class CustomerDao {

	/**
	 * 添加一个用户
	 * @param c
	 */
	public void add(Customer c) {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		
		session.save(c);
		tr.commit();
		session.close();
	}

	/**
	 * 客户列表
	 * @param custName 
	 * @return
	 */
	public List<Customer> getAll(String custName) {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		/*String hql = "from Customer where 1=1 ";
		if (custName != null && !custName.trim().isEmpty()) {
			hql += " and cust_name like '%"+custName+"%' " ;
		}
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();*/
		
		/*用criteria 条件查询更好*/
		Criteria criteria = session.createCriteria(Customer.class);
		if (custName!=null && !custName.trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+custName+"%"));
		}
		List<Customer> list = criteria.list();
		
		tr.commit();
		session.close();
		return list;
	}

	/**
	 * 	根据id 获取一个用户
	 * @param id
	 * @return
	 */
	public Customer getById(Long id) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
//		tr.commit();
//		session.close();
		return customer;
	}

	/**
	 * 更新一个客户信息
	 * @param customer 
	 * @param cid
	 */
	public void update(Customer customer, String cid) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		session.update(cid, customer);
		
		tr.commit();
		session.close();
		
	}

}
