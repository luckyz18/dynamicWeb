package com.servlet;

import java.io.IOException;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示登录次数
 */
public class ShowServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0.设置编码
		response.setContentType("text/html;charset=utf-8");
		// 1.获取servContext
		ServletContext context = this.getServletContext();
		// 2.获取登录次数
		Integer count_now = (Integer) context.getAttribute("count");
		// 3.显示到页面
		response.getWriter().println("总的登录次数为"+count_now);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
