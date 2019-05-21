package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.utils.HibernateUtils;

public class CustomerDaoImpl implements CustomerDao{

	/**
	 * 保存客户
	 */
	@Override
	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(customer);
	}

	/**
	 * 客户列表
	 */
	@Override
	public List<Customer> list(String custName) {
		Session session = HibernateUtils.getCurrentSession();
		Criteria criteria = session.createCriteria(Customer.class);
		if (custName!=null) {
			criteria.add(Restrictions.like("cust_name", "%"+custName+"%"));
		}
		return criteria.list();
	}

	
	



	
}
