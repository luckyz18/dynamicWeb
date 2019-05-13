package com.dao;

import java.util.List;

import org.hibernate.Session;
import com.domain.Customer;
import com.utils.HibernateUtils;

public class CustomerDao {

	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(customer);
		
	}

	/**
	 * 客户列表
	 * @return
	 */
	public List<Customer> list() {
		Session session = HibernateUtils.getCurrentSession();
	    return session.createCriteria(Customer.class).list();
	}

}
