package com.utils;

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
			
			//反射
			return Class.forName(value).newInstance();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getbean("ProductDao"));
	}
}
