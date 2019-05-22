package com.web.action;

import com.domain.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Customer customer = new Customer();
	//封装数据
	@Override
	public Customer getModel() {
		return customer;
	} 
	
	/**
	 * 添加客户
	 * @return
	 */
	public String add() {
		customerService.add(customer);
		return NONE;
	}
	//延迟加载  会有问题
	public String load() {
		Customer customer = customerService.load(1L);
		System.out.println(customer.getCust_name());
		return NONE;
	}
	

	
}
