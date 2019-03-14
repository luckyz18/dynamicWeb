package com.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constant.Constant;
import com.domain.User;
import com.service.UserService;

/**
 * 登录
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username:"+username);
		
		//service  返回user
		User user = null;
		try {
			user = new UserService().getUserByUsernameAndPwd(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		if (user == null) {
			request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			return;
			
		}else {
			//放入session
			request.getSession().setAttribute("user", user);
			 
			//是否自动登录   cookie   中文名字的问题
			if("ok".equals(request.getParameter("autoLogin"))) {
				//创建cookie 写回浏览器 id来代替该user
				Cookie c = new Cookie("autologin",username+"-"+password);				
				c.setMaxAge(3600);
				c.setPath(request.getContextPath()+"/");
				response.addCookie(c);				
			}
			
			//是否记住用户名  中文名字的问题
			if ("ok".equals(request.getParameter("savename"))) {
				Cookie c = new Cookie("savename",URLEncoder.encode(username, "utf-8"));				
				c.setMaxAge(3600);
				c.setPath(request.getContextPath()+"/");
				response.addCookie(c);
				
			}

		}
		response.sendRedirect(request.getContextPath()+"/success.jsp");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
