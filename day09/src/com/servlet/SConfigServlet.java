package com.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Namespace;

import com.domain.User;

/**
 * ServletConfig 对象
 */
public class SConfigServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config = this.getServletConfig();
		
		//servletName
		String servletName = config.getServletName();
		System.out.println("名称："+servletName);
		
		//获取初始化参数
		String user = getInitParameter("user");
		System.out.println("user:"+user);
		
		//获取所有初始化参数名称
		System.out.println("========================");
		Enumeration<String> names = getInitParameterNames();
		while(names.hasMoreElements()) {
			String name= names.nextElement();
			System.out.println("name:"+name);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
