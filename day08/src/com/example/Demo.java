package com.example;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.web.HelloMyServlet;

public class Demo {
	@Test
	public void f1() throws Exception {
		//定义一个map
		Map<String,String> map = new HashMap<>();
		//将两个值放入map
		map.put("/hello", "com.web.HelloMyServlet");
		//通过key 获取 value
		String value = map.get("/hello");
		//通过全限定名创建一个实例调用空参的add方法
		Class<?> clazz = Class.forName(value);
		HelloMyServlet a = (HelloMyServlet) clazz.newInstance();
		a.add();
	}
	
	@Test
	public void f2() throws Exception {
		//解析
		Document doc = new SAXReader().read("E:\\Program\\EclispeProEE\\day08\\xml\\web.xml");
		Element urlPattern = (Element) doc.selectSingleNode("//url-pattern");
		Element servletClass = (Element) doc.selectSingleNode("//servlet-class");
		String UrlText = urlPattern.getText();
		String servletText = servletClass.getText();
		//定义一个map
		Map<String,String> map = new HashMap<>();
		//将两个值放入map
		map.put(UrlText, servletText);
		//通过key 获取 value
		String value = map.get(UrlText);
		//通过全限定名创建一个实例调用空参的add方法
		Class<?> clazz = Class.forName(value);
		HelloMyServlet a = (HelloMyServlet) clazz.newInstance();
		a.add();
	}
}
