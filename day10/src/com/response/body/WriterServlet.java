package com.response.body;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应体
 */
public class WriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter w = response.getWriter();
//		w.print("ddss");

		w.print("<table border='1px'><tr>");
		w.print("<td>姓名</td>");
		w.print("<td>tom</td></tr>");
		w.print("<tr><td>密码</td>");
		w.print("<td>123</td>");
		w.print("</tr></table>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
