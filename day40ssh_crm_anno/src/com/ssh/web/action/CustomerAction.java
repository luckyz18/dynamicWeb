package com.ssh.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;

/**
 * spring管理action，开启组件扫描
 * <bean id="customerAction" class="com...customerAction" scope="prototype">
 * </bean>
 * 
 * struts 中配置action action 负责处理请求和页面跳转
 * <package name="demo"  extends="struts-default" namespace="/"> 
 * 		<action name="user_save" class="userAction" method="save"></action>
 * </package>
 */

@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	
	@Action(value="customer_save",results= {@Result(name="success",location="/login.jsp")})
	public String save() {
		System.out.println("action 的save方法执行");
		customerService.save(customer);
		return SUCCESS;
	}

}
