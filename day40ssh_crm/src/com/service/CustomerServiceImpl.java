package com.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.domain.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/**
	 * 添加客户
	 */
	public void add(Customer customer) {
		customerDao.save(customer);
	}
	/**
	 * 分页展示客户
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode,pageSize,criteria);
	}
	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	/**
	 * 通过id获取客户
	 */
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	/**
	 * 修改客户
	 */
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
