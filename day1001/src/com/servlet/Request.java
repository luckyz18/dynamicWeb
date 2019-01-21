package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf8");
		String name = req.getParameter("username");
		System.out.println(name);
		//回写数据
//		resp.getWriter().print("data"+name);
		resp.getWriter().print("数据"+name);
	}
}
