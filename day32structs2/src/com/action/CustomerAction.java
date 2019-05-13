package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	/**
	 * 保存客户
	 * @return
	 */
	public String save() {
		new CustomerService().save(customer);
		return null;
	}
	
	/**
	 * 客户列表
	 * @return
	 */
	public String list() {
		List<Customer> list;
		list = new CustomerService().list();
//		ServletActionContext.getRequest().setAttribute("clist", list);
//		ActionContext.getContext().getSession().put("clist", list);
		//用值栈存数据
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("clist", list);
		
		return SUCCESS;
		
	}

}
