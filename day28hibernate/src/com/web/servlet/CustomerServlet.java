package com.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import com.domain.Customer;
import com.service.CustomerService;


public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

		Map<String, String[]> map = request.getParameterMap();
		Customer c = new Customer();
		BeanUtils.populate(c, map);
		//service
		CustomerService cs = new CustomerService();
		cs.add(c);
		
		//返回值
		System.out.println("添加用户成功了····");
		response.sendRedirect(request.getContextPath()+"/customer?method=list");
		
	}
	
	/**
	 * 	客户列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String custName = request.getParameter("custName");
		
		CustomerService cs = new CustomerService();
		List<Customer> list = cs.getAll(custName);
		request.setAttribute("clist", list);
		
		request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		return null;				
	}
	
	//getById
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cid = request.getParameter("cust_id");
		Long id = Long.parseLong(cid);
		CustomerService cs = new CustomerService();
		Customer customer = cs.getById(id);
		request.setAttribute("customer", customer);
		
		return "/jsp/customer/edit.jsp";
	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void  editsubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, String[]> map = request.getParameterMap();
		Customer c = new Customer();
		BeanUtils.populate(c, map);
		
		String cid = request.getParameter("cust_id");
		CustomerService cs = new CustomerService();
		cs.update(c, cid);
		
		response.sendRedirect(request.getContextPath()+"/customer?method=list");
		
	}

}
