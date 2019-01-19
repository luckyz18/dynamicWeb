package com.response.location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *      重定向
 */
public class Loc1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//方式1
		System.out.println("没有钱");
		System.out.println("又说：找loc2借钱");
//		
//		//设置状态码  重定向到loc2
//		response.setStatus(302);
//		response.setHeader("location", "/day10/loc2");
		
		//方式2
		response.sendRedirect("/day10/loc2");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
