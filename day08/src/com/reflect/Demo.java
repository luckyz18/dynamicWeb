package com.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

import com.web.HelloMyServlet;

public class Demo {
	@Test
	public void f1() {
		//调用HelloMyServlet 方法
		HelloMyServlet a =new HelloMyServlet();
		a.add();
		a.add(10, 20);
	}
	@Test
	public void f2() throws Exception {
		//通过字节码对象创建一个实例对象  == 调用空参的构造器
		Class clazz=Class.forName("com.web.HelloMyServlet");   //类对象
		HelloMyServlet a = (HelloMyServlet) clazz.newInstance();  //实例对象
		a.add();
	}
	
	@Test
	public void f3() throws Exception {
		//通过字节码对象创建一个实例对象  == 调用空参的构造器
		Class clazz=Class.forName("com.web.HelloMyServlet");   //类对象
		HelloMyServlet a = (HelloMyServlet) clazz.newInstance();  //实例对象
		Method method = clazz.getMethod("add");
		method.invoke(a);
	}
	
	@Test
	public void f4() throws Exception {
		//通过字节码对象创建一个实例对象  == 调用空参的构造器
		Class clazz=Class.forName("com.web.HelloMyServlet");   //类对象
		HelloMyServlet a = (HelloMyServlet) clazz.newInstance();  //实例对象
		Method method = clazz.getMethod("add",int.class,int.class);    
		method.invoke(a,20,10);  //相当于 a.add(20,30)
	}
	
}
