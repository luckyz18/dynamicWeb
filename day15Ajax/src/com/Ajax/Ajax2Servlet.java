package com.Ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * get 请求
 */
public class Ajax2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"),"utf-8");
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(username);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("post:"+username);	
	}
		
}

