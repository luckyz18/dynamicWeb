package com.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.impl.CustomerDaoImpl;
import com.domain.Customer;
import com.service.CustomerService;
import com.utils.HibernateUtils;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDaoImpl dao;
	public void setDao(CustomerDaoImpl dao) {
		this.dao = dao;
	}


	@Override
	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			dao.save(customer);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}

	/**
	 * 客户列表
	 */
	@Override
	public List<Customer> list(String custName) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		try {
			List<Customer> list= dao.list(custName);
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
		
	}

}
