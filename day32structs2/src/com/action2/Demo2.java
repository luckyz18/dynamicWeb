package com.action2;

import com.opensymphony.xwork2.Action;

/**
 * 实现 action接口
 * @author 祝丽华
 *
 */
public class Demo2 implements Action{

	@Override
	public String execute() throws Exception {
		System.out.println("实现action接口。。。");
		return NONE;
	}
	
}
