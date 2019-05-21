package com.service;

import java.util.List;

import com.domain.Customer;

public interface CustomerService {
	/*保存客户*/
	public void save(Customer customer);
	/*客户列表 
	 * custName：筛选条件
	 * */
	public  List<Customer> list(String custName);
}
