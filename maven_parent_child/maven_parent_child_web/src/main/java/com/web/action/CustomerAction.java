package com.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.CustomerService;

public class CustomerAction extends ActionSupport {

	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public String list(){
		customerService.findAll();
		
		return NONE;
	}

}
