package com.anotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//获取字节码对象
		Class clazz = MytestTest.class;
		
		//获取所有方法
		Method[] arr = clazz.getMethods();
		
		//让带有注解的方法执行
		for (Method m : arr) {
			//是否有注解
			boolean flag = m.isAnnotationPresent(MyTest.class);
			if (flag) {
//				System.out.println(m.getName());
				m.invoke(clazz.newInstance());   //不能有参数
			}
		}
	}
	
}
