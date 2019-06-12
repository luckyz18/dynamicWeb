package com.ssh.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao{

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public void save(Customer customer) {
		this.hibernateTemplate.save(customer);
	}

}
