package com.demo_jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class demo {
	@Test
	public void run1() {
		/*spring提供了内置的连接池*/
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring");
		dataSource.setUsername("root"); 
		dataSource.setPassword("123456");
		
		 JdbcTemplate template = new JdbcTemplate();
		 
		 template.setDataSource(dataSource);
		 template.update("insert into taccount values(null,?,?)", "美美",100);
		 
	}
}
