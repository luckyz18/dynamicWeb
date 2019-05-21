package com.dao;

import java.util.List;

import com.domain.Customer;

public interface CustomerDao {
	public void save(Customer customer);
	public List<Customer> list(String custName);
	
}
