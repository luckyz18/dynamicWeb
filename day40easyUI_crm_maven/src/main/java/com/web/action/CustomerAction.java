package com.web.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.domain.Customer;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CustomerService;
import com.sun.javafx.collections.MappingChange.Map;
import com.utils.FastJsonUtil;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 1L;

	private Customer customer = new Customer();
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	/*注入service*/
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//接收分页数据
	private Integer page = 1;
	private Integer rows = 3;
	public void setPage(Integer page) {
		if (page!=null) {
			this.page = page;
		}
	}
	public void setRows(Integer rows) {
		if (rows!=null) {
			this.rows = rows;
		}
	}

	public String findAll() throws IOException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		PageBean<Customer> pagebean = customerService.findByPage(criteria,page,rows);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", pagebean.getTotalCount());
		map.put("rows", pagebean.getBeanList());
		
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
//		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		String jsonString = FastJsonUtil.toJSONString(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		
		return NONE;
	}
	
	public String save() {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			customerService.save(customer);
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("msg","保存失败");
		}
		
		String jsonString = FastJsonUtil.toJSONString(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);

		return NONE;
	}
	
	
	
}
