package com.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.utils.HibernateUtils;

public class CustomerService {

	/**
	 * 添加一个用户
	 * @param c
	 */
	public void add(Customer c) {
		CustomerDao dao = new CustomerDao();
		dao.add(c);
		
	}

	/**
	 * 客户列表
	 * @param custName 
	 * @return
	 */
	public List<Customer> getAll(String custName) {
		CustomerDao dao = new CustomerDao();
		return dao.getAll(custName);
	}

	/**
	 * 根据id 获取一个客户
	 * @param id
	 * @return
	 */
	public Customer getById(Long id) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			CustomerDao dao = new CustomerDao();
			return dao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	/**
	 * 更新一个客户
	 * @param cid
	 */
	public void update(Customer customer,String cid) {
		CustomerDao dao = new CustomerDao();
		dao.update(customer,cid);
		
	}

}
