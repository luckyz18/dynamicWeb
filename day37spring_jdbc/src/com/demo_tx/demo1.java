package com.demo_tx;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class demo1 {
	@Resource(name="accountService")
	private AccountService as;
	
	@Test
	public void run1() {
		as.pay("测试1", "测试2", 10.0);
		
		
		
	}
}
