package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import com.dao.UserDao;
import com.domain.User;
import com.service.RegisterService;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("utf-8"); 不往页面上写 所以不用response
		request.setCharacterEncoding("utf-8");
		
		//封装数据 一个一个封装太麻烦 直接
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e1) {		
			e1.printStackTrace();
		} 
		
		
		try {
			int ifsuccess = new RegisterService().register(user);
			if(ifsuccess == 0) {
				request.setAttribute("msg", "注册失败");
				request.getRequestDispatcher("/msg").forward(request, response);
				
			}else {
				request.setAttribute("msg", "注册成功");
				request.getRequestDispatcher("/msg").forward(request, response);
				;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		
	}

}
