package com.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;

@Transactional
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	
	public void save(Customer customer) {
		System.out.println("service 的save方法");
		customerDao.save(customer);
	}

}
