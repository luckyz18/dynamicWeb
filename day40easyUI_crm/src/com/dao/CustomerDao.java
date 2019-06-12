package com.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.domain.Customer;

public interface CustomerDao {


	List<Customer> findByPage(DetachedCriteria criteria, int i, Integer rows);

	Integer findTotalCount(DetachedCriteria criteria);

	void save(Customer customer);

}
