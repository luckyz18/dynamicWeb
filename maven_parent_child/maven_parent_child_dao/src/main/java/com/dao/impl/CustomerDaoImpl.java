package com.dao.impl;


import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dao.CustomerDao;
import com.domain.Customer;

import redis.clients.jedis.Jedis;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	
	public void findAll() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		System.out.println(list.size());
	}
	
	
}
