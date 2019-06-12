package com.service;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.Customer;
import com.domain.PageBean;

public interface CustomerService {

	PageBean<Customer> findByPage(DetachedCriteria criteria, Integer page, Integer rows);

	void save(Customer customer);

}
