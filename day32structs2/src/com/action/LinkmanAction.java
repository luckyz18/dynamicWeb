package com.action;

import com.opensymphony.xwork2.ActionSupport;

public class LinkmanAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public String save() {
		System.out.println("保存联系人");
		return SUCCESS;
	}
	public String delete() {
		System.out.println("删除联系人");
		return NONE;
	}
	
	
	
	
}
