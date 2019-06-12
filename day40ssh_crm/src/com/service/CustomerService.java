package com.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.Customer;
import com.domain.PageBean;

public interface CustomerService {
	public void add(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public void delete(Customer customer);

	public Customer findById(Long cust_id);

	public void update(Customer customer);

	public List<Customer> findAll();

}
