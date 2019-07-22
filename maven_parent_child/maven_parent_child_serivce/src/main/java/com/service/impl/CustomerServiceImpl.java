package com.service.impl;

import com.dao.CustomerDao;
import com.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public void findAll() {
		customerDao.findAll();
	}
	
}
