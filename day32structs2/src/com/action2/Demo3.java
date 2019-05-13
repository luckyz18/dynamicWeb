package com.action2;

import com.opensymphony.xwork2.ActionSupport;

public class Demo3 extends ActionSupport{
	@Override
	public String execute() throws Exception {
		System.out.println("继承 actionsupport 实现类");
		return NONE;
	}
}
