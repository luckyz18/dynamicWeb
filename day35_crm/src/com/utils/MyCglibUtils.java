package com.utils;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import demo.BookDaoImpl;

public class MyCglibUtils {
	public static BookDaoImpl getProxy() {
		Enhancer enhancer  =new Enhancer();
		enhancer.setSuperclass(BookDaoImpl.class);
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				if ("update".equals(method.getName())) {
					System.out.println("记录日志");
				}
				return methodProxy.invokeSuper(obj, args);
			}
		});
		//生成代理对象
		BookDaoImpl proxy = (BookDaoImpl) enhancer.create();
		return proxy;
		
	}
}
