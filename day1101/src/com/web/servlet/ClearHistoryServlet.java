package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *     清空浏览记录
 */
public class ClearHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建cookie 写回浏览器
		Cookie c = new Cookie("ids", "");
		c.setPath(request.getContextPath()+"/");
		c.setMaxAge(0);
		response.addCookie(c);
		
		System.out.println("clear");
		response.sendRedirect(request.getContextPath()+"/product_list.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

} 
