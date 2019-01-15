package com.dom4j;

import java.io.Reader;
import java.util.List;

import javax.xml.soap.Text;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		System.out.println("sdsds");
		Document doc = reader.read("E:\\Program\\EclispeProEE\\day08\\xml\\web.xml");
		Element root = doc.getRootElement();
		//泛型
		List<Element> list = root.elements();    
		for (Element ele : list) {
			String text = ele.elementText("servlet-name");
			System.out.println(text);
		}
		
		String version = root.attributeValue("version");	
		System.out.println(version);
		
	}
}
