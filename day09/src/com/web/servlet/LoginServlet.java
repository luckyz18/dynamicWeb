package com.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.LoginService;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//接受参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//		System.out.println(username+"+ "+password);
		
		User user = null;
		try {
			user = new LoginService().login(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("网络异常，重新加载巴拉巴拉");
		}
		
		if (user == null) {
			response.getWriter().print("用户名密码不匹配");
		}
		else {
			response.getWriter().print(user.getUsername()+"欢迎回来");
		}
		
	}
	
}
