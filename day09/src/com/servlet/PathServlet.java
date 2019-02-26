package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *     通过类加载器获取文件
 */
public class PathServlet extends HttpServlet {	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path  = PathServlet.class.getClassLoader().getResource("/2.txt").getPath();
//		String path1 = PathServlet.class.getClassLoader().getResourceAsStream("2.txt");  
		System.out.println(path);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
