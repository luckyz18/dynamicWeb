package com.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.Customer;
import com.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo1 {
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	//测试 查询  get
	@Test
	public void run1() {
		Customer customer  = customerService.getById(1L);
		System.out.println(customer);
	}
	//测试 查询所有 hql
	@Test
	public void run2() {
		List<Customer> list= customerService.findAll();
		System.out.println(list);
	}
	//测试 查询所有 qbc
	@Test
	public void run3() {
		List<Customer> list = customerService.findAllByQBC();
		System.out.println(list);
	}
	//测试 改
	@Test
	public void run4() {
		Customer customer = customerService.getById(2L);
		customer.setCust_name("哈哈哈");
		customerService.update(customer);
	}
	
}	
