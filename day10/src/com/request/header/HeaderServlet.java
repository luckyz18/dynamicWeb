package com.request.header;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求头
 */
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String agent = request.getHeader("user-agent");
		System.out.println(agent);
		
		String refer = request.getHeader("referer");
		if (refer==null) {
			System.out.println("地址栏输入的地址");
		}else if(refer.contains("localhost")) {
			System.out.println("本机点的");
		}else if (refer.contains("192.168")) {
			System.out.println("本机点的");
		}else {
			System.out.println("盗链者");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
