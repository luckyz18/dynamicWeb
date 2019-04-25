package com.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {
	public static Object getbean(String id) throws Exception {
		// TODO 通过id获取一个指定的实现类
		
		try {
			//获取document对象
			Document doc = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			
			//获取指定的bean对象  xpath
			Element ele = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			
			//获取bean的class属性
			String value = ele.attributeValue("class");
			
			//反射 之前的方法 直接返回一个实例
//			return Class.forName(value).newInstance();
			
			//现在 对service中的add方法进行加强，返回的是代理对象 动态代理实现
			Object obj = Class.forName(value).newInstance();
			//如果是service的实现类  
			if (id.endsWith("Service")) {
				Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//继续判断是否是add或者 regist操作
						if ("add".equals(method.getName()) || "register".equals(method.getName())) {
							System.out.println("添加操作");
							return method.invoke(obj, args);
						}
						return method.invoke(obj, args);
					}
				});
				//若是service 返回的是代理对象
				return proxyObj;
			}
			return obj;
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getbean("ProductDao"));
	}
}
