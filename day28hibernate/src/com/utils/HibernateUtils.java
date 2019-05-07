package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final Configuration CONFIG;
	private static final SessionFactory FACTORY;
	
	
	static {
		//加载配置文件
		CONFIG = new Configuration().configure();
		//生成factory对象
		FACTORY = CONFIG.buildSessionFactory();
	}
	
	/**
	 * 获取session对象
	 * @return
	 */
	public static Session getSession() {
		return FACTORY.openSession();
	}
	
	
	public static Session getCurrentSession() {
		 return FACTORY.getCurrentSession();
	}
	
	public static void main(String[] args) {
		getSession();
	}
	
	
}
