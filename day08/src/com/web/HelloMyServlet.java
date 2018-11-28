package com.web;

public class HelloMyServlet {

	public void add() {
		System.out.println("空参");
	}
	public void add(int i, int j) {
		System.out.println("两个参数");
		System.out.println(i+j);
	}
	public int add(int i) {
		return i+10;
	}

}
