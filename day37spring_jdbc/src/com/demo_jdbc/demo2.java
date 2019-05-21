package com.demo_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.Account;

//IOC 方式
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo2 {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	public void run1() {
		jdbcTemplate.update("insert into taccount values(null,?,?)", "小花",100);
	}
	
	/**
	 * 查询操作
	 */
	@Test
	public void run2() {
		Account ac = jdbcTemplate.queryForObject("select * from taccount where id=? ", new BeanMapper(), 3);
		System.out.println(ac);
	}
	
	//查询 结果是集合
	@Test
	public void run3() {
		List<Account> list = jdbcTemplate.query("select * from taccount",  new BeanMapper());
		System.out.println(list);
	}
	
}

//需要自己封装数据
class BeanMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int rownum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setName(rs.getString("name"));
		account.setMoney(rs.getDouble("money"));
		return account;
	}
	
}
