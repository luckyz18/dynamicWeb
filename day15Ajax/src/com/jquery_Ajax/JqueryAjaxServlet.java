package com.jquery_Ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jquery ajax
 */
public class JqueryAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getMethod());
		
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		System.out.println(username);
		
		
		response.setContentType("text/html;charset=utf-8");		
		//  {"result":"success","msg":"成功"}
		String s = "{\"result\":\"success\",\"msg\":\"成功\"}";
		response.getWriter().print(s);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getMethod());
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		System.out.println(username);
		
		
		response.setContentType("text/html;charset=utf-8");		
		String s = "{\"result\":\"success\",\"msg\":\"成功\"}";
		response.getWriter().print(s);
	}

}
