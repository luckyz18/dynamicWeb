package com.servlet;

import java.io.InputStream;

public class PathDemo {
	public static void main(String []args) {
		String path_ = PathServlet.class.getClassLoader().getResource("2.txt").getPath();
		
		InputStream path_2 = PathServlet.class.getClassLoader().getResourceAsStream("2.txt");
		System.out.println(path_);
		System.out.println(path_2);
	}
	
}
