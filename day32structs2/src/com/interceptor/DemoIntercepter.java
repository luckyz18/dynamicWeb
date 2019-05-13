package com.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class DemoIntercepter extends AbstractInterceptor{

	/*
	 * 执行拦截
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("执行action之前");
		/*执行下一个拦截器*/
		String result = invocation.invoke();
		
		System.out.println("执行action之后");
		return result;
	}

}
