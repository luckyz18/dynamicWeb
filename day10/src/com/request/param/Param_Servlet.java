package com.request.param;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *   请求参数的中文乱码问题
 */
public class Param_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		username=new String(username.getBytes("iso-8859-1"), "utf-8"); 			
		System.out.println("username:"+username);
		
		System.out.println("---------------");
		String password = request.getParameter("password");
		password=new String(password.getBytes("iso-8859-1"), "utf-8");
		System.out.println("password:"+password);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//只有post 可以这么设
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username"); 			
		System.out.println("username:"+username);
		
		System.out.println("---------------");
		String password = request.getParameter("password");
		System.out.println("password:"+password);
	}

}
