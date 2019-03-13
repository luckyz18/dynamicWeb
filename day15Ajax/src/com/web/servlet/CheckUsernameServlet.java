package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.CheckUserName4Ajax;

/**
 * 	检测用户名是否被占用
 */
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(111);
		
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		
		//service
		User user = null;
		try {
			user = new CheckUserName4Ajax().checkUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//写回信息
		if (user == null) {
			System.out.println("null---");
			response.getWriter().println(1);
		}else {
			System.out.println("不空---");
			response.getWriter().println(0);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
