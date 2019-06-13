package com.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.domain.PageBean;
import com.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		Integer totalCount = customerDao.findTotalCount(criteria);
		pageBean.setTotalCount(totalCount);
		
		List<Customer> list = customerDao.findByPage(criteria,pageCode,pageSize);
		pageBean.setBeanList(list);
		
		return pageBean;
	}

	public void save(Customer customer) {
		customerDao.save(customer);
		
	}

}
