package com.request.row;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 获取请求方式
		 * 获取请求资源
		 * 获取请求参数字符串
		 * 获取请求ip
		 * 获取项目名称
		 */
		
		String m = request.getMethod();
		StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();
		String remoteAddr = request.getRemoteAddr();
//		String remoteHost = request.getRemoteHost();
		String contextPath = request.getContextPath();
		
		
		System.out.println(m+","+requestURL+","+requestURI+","+queryString+","+remoteAddr+","+contextPath);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
