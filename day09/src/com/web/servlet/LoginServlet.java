package com.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.PrimitiveIterator;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.LoginService;

public class LoginServlet extends HttpServlet {
		
	//初始化次数
	@Override
	public void init() throws ServletException {

		ServletContext context = this.getServletContext();
		context.setAttribute("count", 0);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//接受参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		try {
			user = new LoginService().login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("网络异常，重新加载巴拉巴拉");
		}
		
		if (user == null) {
			response.getWriter().print("用户名密码不匹配，3秒之后跳转");
			response.setHeader("refresh", "3;url=/day09/login.htm");
		}
		else {
			response.getWriter().print(user.getUsername()+"欢迎回来");
			/**
			 * 获取次数
			 * 次数+1
			 * 回存ServletContext
			 */
			
			ServletContext context = this.getServletContext();
			Integer count_now = (Integer) context.getAttribute("count"); //注意: 尽量用Integer 因为null不能赋给int 可以赋给Integer
			count_now += 1;
			context.setAttribute("count", count_now);
			System.out.println("登录成功");
		}
		
	}
	
}
