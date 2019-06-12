package com.intercepter;

import org.apache.struts2.ServletActionContext;

import com.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 用户登录功能的拦截器
 */
public class UserInterceptor extends MethodFilterInterceptor
{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
//		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
//		if (user == null) {
//			return "login";
//		}
		return invocation.invoke();
	}

}
