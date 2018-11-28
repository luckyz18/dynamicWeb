package com.xpath;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XpathDemo {
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read("E:\\Program\\EclispeProEE\\day08\\xml\\web.xml");    //链式加载到内存中 成dom树
		List<Element> list = doc.selectNodes("/web-app/servlet/servlet-name");   
		Element ele = list.get(0);
		System.out.println(ele.getText());	
		
		Element eleSing = (Element) doc.selectSingleNode("//servlet/servlet-name"); //Element 元素节点  是node的子类  
		System.out.println(eleSing.getText());
	}
}
