package com.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import demo.CustomerDao;

public class MyProxyUtils {
	public static CustomerDao getProxy(final CustomerDao dao) {
		CustomerDao proxy = (CustomerDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(), dao.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if ("save".equals(method.getName())) {
					System.out.println("记录日志////");
				}
				return method.invoke(dao, args);
			}
		});
		
		//返回代理对象
		return proxy;
		
	}
}
