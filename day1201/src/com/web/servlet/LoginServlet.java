package com.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.service.UserService;

/**
 *登录
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");  //post请求
		
		//1 处理验证码	
		String checkcode = request.getParameter("checkcode");		
		String sCode= (String) request.getSession().getAttribute("sessionCode");
		request.getSession().removeAttribute("sessionCode");
		
		
		//equals  不为空才能调用equals方法
		//防止输入空格
		if(checkcode == null || checkcode.trim().length()==0 || sCode==null) {  
			//验证码有问题 提示信息
			request.setAttribute("msg", "请重新填写验证码");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;  //下面代码不执行了
		}		
		if (!checkcode.equalsIgnoreCase(sCode)) {
			request.setAttribute("msg", "验证码输入错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
		
		//2 获取用户名 密码 是否正确
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		if (username==null || password==null) {
//			request.setAttribute("msg", "用户名密码不匹配");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//		}
		
		User user = null;
		try {
			user = new UserService().getUserByUsernameAndPwd(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (user == null) {
			request.setAttribute("msg", "用户名密码不匹配");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else {
			//是否记住密码
			if ("ok".equals(request.getParameter("saveName"))) {
				//创建cookie
				Cookie u = new Cookie("saveName", username);
				u.setMaxAge(3600);
				u.setPath(request.getContextPath()+"/");
				response.addCookie(u);
			
			}
			request.getSession().setAttribute("user", user);
		
		}
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
