package com.anotation;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.catalina.User;

public class JDBCUtils {
	
	@JDBCInfo(password = "123456", user = "root")
	public static Connection getConnection() throws Exception{
		
		//获取字节码对象
		Class clazz = JDBCUtils.class;
		
		//从注解上获取4个参数
		Method m = clazz.getMethod("getConnection");
		
		if (m.isAnnotationPresent(JDBCInfo.class)) {
			JDBCInfo info = m.getAnnotation(JDBCInfo.class);
			
			String driverClass = info.driverClass();
			String url = info.url();
			String user = info.user();
			String password = info.password();
			
			//注册驱动
			Class.forName(driverClass);
			
			//获取连接
			return DriverManager.getConnection(url, user, password);
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getConnection());
	}
}
