package com.dao;

import java.util.List;

import com.domain.Customer;

public interface CustomerDao {
	public void save(Customer customer);

	public Customer getByID(long id);

	public List<Customer> findAll();

	public List<Customer> findAllByQBC();

	public void update(Customer customer);

	public Customer load(long id);
}
