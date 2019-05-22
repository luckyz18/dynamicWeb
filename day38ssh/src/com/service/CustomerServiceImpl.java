package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDao;
import com.domain.Customer;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void add(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public Customer getById(long id) {
		return customerDao.getByID(id);
		
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> findAllByQBC() {
		return customerDao.findAllByQBC();
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}

	@Override
	public Customer load(long id) {
		return customerDao.load(id);
	}

}
