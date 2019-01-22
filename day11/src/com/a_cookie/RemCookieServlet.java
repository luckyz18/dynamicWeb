package com.a_cookie;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
public class RemCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		/**
		 * 获取Cookie
		 * c代表cookie lastTime=,,,
		 */
		Cookie c=getCookieByName("lastTime",request.getCookies());
		if (c == null) {
			response.getWriter().println("第一次访问");
		}else {
			String value = c.getValue();
			long time = Long.parseLong(value);
			Date date = new Date(time);
			
//			System.out.println(value);    //value=123452312
//			System.out.println(time);
//			System.out.println(date);
			response.getWriter().println("上次访问时间是"+date.toLocaleString());
		}
		
		
		//将当前访问时间  
		// 创建cookie 写回浏览器
		c = new Cookie("lastTime", new Date().getTime()+"");
		//持久化cookie
		c.setMaxAge(3600);
		//设置路径
		c.setPath(request.getContextPath()+"/");       //    /day11/
		
		response.addCookie(c);
		
		
	}

	
	private Cookie getCookieByName(String name, Cookie[] cookies) {
		if (cookies!=null) {
			for (Cookie c : cookies) {
				if (name.equals(c.getName())) {
					return c;
				}
			}
		}
		return null;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
