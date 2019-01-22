package com.a_cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Cookie a = new Cookie("akey", "avalue");
		Cookie b = new Cookie("bkey", "bvalue");
		Cookie c = new Cookie("ckey", "cvalue");
		
		response.addCookie(a);
		response.addCookie(b);
		response.addCookie(c);
		
		response.getWriter().println("cookie成功");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
