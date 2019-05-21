package com.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private static final long serialVersionUID = 1L;
	
	private String custName;  //列表页 筛选条件
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	//模型驱动
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}

	/**
	 * 插入一个新的客户
	 * @return
	 */
	public String save() {
		/* 发送每访问一次都会加载一次配置文件
		 * 将工厂创建好了以后放入到ServletContext域中.使用工厂的时候,从ServletContext中获得.
		 */
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService cs = (CustomerService) ac.getBean("customerService");*/ 
		
		//1.获取参数   模型驱动
		
		//2. service
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		CustomerService cs = (CustomerService) context.getBean("customerService");
		cs.save(customer);
		return NONE;
	}
	/**
	 * 客户列表
	 */
	public String list() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		CustomerService cs = (CustomerService) context.getBean("customerService");
		List<Customer> list = cs.list(custName);
		//放入值栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("clist", list);
		return "list";
		
	}

	
	
}
