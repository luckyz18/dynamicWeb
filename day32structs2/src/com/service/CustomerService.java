package com.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dao.CustomerDao;
import com.domain.Customer;
import com.utils.HibernateUtils;

public class CustomerService {
	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		new CustomerDao().save(customer);
		
		tr.commit();
	}
	
	
	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.setCust_name("测试");
		CustomerService service = new CustomerService();
		service.save(customer);
	}

	/**
	 * 客户列表
	 * @return
	 */
	public List<Customer> list() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = null;
		try {
			list = new CustomerDao().list();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return null;
	}
}
