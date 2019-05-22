package com.service;

import java.util.List;

import com.domain.Customer;

public interface CustomerService {
	public void add(Customer customer);

	public Customer getById(long id);

	public List<Customer> findAll();

	public List<Customer> findAllByQBC();

	public void update(Customer customer);

	public Customer load(long id);
}
