package com.demo_tx;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao{
	
	/* 
	 * private JdbcTemplate jdbctemplate;   // 继承jdbcsupport类
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}*/

	@Override
	public void out(String out,Double money) { 
		this.getJdbcTemplate().update("update taccount set money = money - ? where name = ? ", money,out);
		
	}


	@Override
	public void in(String in, Double money) {
		this.getJdbcTemplate().update("update taccount set money = money + ? where name = ? ", money,in);
	}
	

}
