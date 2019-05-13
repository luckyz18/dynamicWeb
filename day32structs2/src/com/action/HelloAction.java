package com.action;


public class HelloAction {
	/**
	 * 方法签名是固定的
	 * 必须 public 返回值String 方法名任意 在方法中不能传递参数
	 * @return
	 */
	public String helloTest() {
		System.out.println("hello structs2 hhh");
		return "success";
	}
	public String execute() {
		System.out.println("execute 默认执行");
		return "success";
	}
	
}
